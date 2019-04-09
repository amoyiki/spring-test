package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.form.LoginForm;
import com.amoyiki.springtest.service.AuthorizeService;
import com.amoyiki.springtest.service.ClientService;
import com.amoyiki.springtest.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * OAUTH2 授权控制器
 *
 * @author amoyiki
 * @date 2019/3/11
 */
@Controller
@Slf4j
@RequestMapping("/oauth")
public class AuthorizeController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthorizeService authorizeService;

    /**
     * 请求 OAUTH2 登录框
     * /oauth/authorize?response_type=code
     * &client_id=wcXPkCAixnWlDPBDa9GtVriZvntYz3XCHqYB0BeX
     * &redirect_uri=http://localhost:9080/xxx
     * &scope=email
     *
     * @param model
     * @param request
     * @return Object
     * @author amoyiki
     */
    @RequestMapping("/authorize")
    public Object authorize(Model model,
                            HttpServletRequest request) throws OAuthSystemException,
            URISyntaxException, BindException {
        log.info("├ [OAUTH2 登录框]: 进入 OAUTH2 登录框");
        try {
            OAuthAuthzRequest oAuthAuthzRequest = new OAuthAuthzRequest(request);
            // 判断 ClientId 是否存在
            if (!authorizeService.checkClientId(oAuthAuthzRequest.getClientId())) {
                // 不存在生成错误信息告知
                OAuthResponse response = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription("客户端验证失败！")
                        .buildJSONMessage();
                return new ResponseEntity<String>(
                        response.getBody(), HttpStatus.valueOf(response.getResponseStatus())
                );
            }
            // 判断用户是否登录
            Subject subject = SecurityUtils.getSubject();
            // 未登录跳转至登录界面
            if (!subject.isAuthenticated()) {
                if (!login(subject, request)) {
                    // 登录失败跳转到登录页面
                    model.addAttribute("client", clientService.findByClientId(oAuthAuthzRequest.getClientId()));
                    return "login";
                }
            }
            UserInfo user = (UserInfo) subject.getPrincipal();
            if (user == null) throw new AuthenticationException();
            String username = user.getUsername();
            // 授权码
            String authorizationCode = null;
            String responseType = oAuthAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            // response_type=code
            if (responseType.equals(ResponseType.CODE.toString())) {
                // 生成授权码放入缓存
                OAuthIssuerImpl oAuthIssuer = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oAuthIssuer.authorizationCode();
                log.info("├ [code]: {}", authorizationCode);
                authorizeService.addAuthCode(authorizationCode, username);
            }
            // 构建 OAUTH2 响应
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder = new
                    OAuthASResponse.OAuthAuthorizationResponseBuilder(request, HttpServletResponse.SC_FOUND);
            // 设置授权码
            builder.setCode(authorizationCode);
            // 重定向到 redirect_uri
            String redirectURI = oAuthAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            // 构建响应 加 final 防止被修改
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
            // 返回 ResponseEntity 响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));

        } catch (OAuthProblemException e) {
            e.printStackTrace();
            String redirectURI = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectURI)) {
                // 返回无重定向地址
                return new ResponseEntity("需要传入 redirect_uri", HttpStatus.NOT_FOUND);
            }
            // 返回错误信息
            final OAuthResponse response = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                    .error(e)
                    .location(redirectURI)
                    .buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        }

    }

    /**
     * 登录判断
     *
     * @param subject
     * @param request
     * @return boolean
     * @author amoyiki
     */
    private boolean login(Subject subject, HttpServletRequest request) {
        // 过滤 GET 请求
        if ("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return true;
    }
}
