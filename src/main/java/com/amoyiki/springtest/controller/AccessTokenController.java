package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 验证控制器
 *
 * @author amoyiki
 * @since 2019/3/11
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class AccessTokenController {
    @Autowired
    private AuthorizeService authorizeService;

    @RequestMapping("/token")
    public HttpEntity token(HttpServletRequest request) throws OAuthSystemException {
        try {
            OAuthTokenRequest oAuthTokenRequest = new OAuthTokenRequest(request);
            if (!authorizeService.checkClientId(oAuthTokenRequest.getClientId())) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription("客户端 ID 验证失败")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            // 验证 client secret 是否正确
            if (!authorizeService.checkClientSecret(oAuthTokenRequest.getClientSecret())) {
                OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                        .setErrorDescription("客户端 secret 验证失败")
                        .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            String authCode = oAuthTokenRequest.getParam(OAuth.OAUTH_CODE);
            // 检查验证类型，此处只检查AUTHORIZATION类型，其他的还有PASSWORD或者REFRESH_TOKEN
            if (oAuthTokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!authorizeService.checkAuthCode(authCode)) {
                    OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("auth_code错误！")
                            .buildJSONMessage();
                    return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
                }
            }
            OAuthIssuer issuer = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken  = issuer.accessToken();
            authorizeService.addAccessToken(accessToken, authorizeService.getUsernameByAuthCode(authCode));

            // 生成OAuth响应
            OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken).setExpiresIn(String.valueOf(authorizeService.getExpireIn()))
                    .buildJSONMessage();

            return new ResponseEntity(response.getBody(),HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {
            e.printStackTrace();
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e).buildBodyMessage();
            return new ResponseEntity(res.getBody(),HttpStatus.valueOf(res.getResponseStatus()));
        }
    }
}
