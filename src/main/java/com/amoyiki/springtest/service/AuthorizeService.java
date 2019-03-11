package com.amoyiki.springtest.service;

/**
 *  OAUTH2 验证服务
 * @author amoyiki
 * @since 2019/3/11
 */
public interface AuthorizeService {
    /**
     *
     * 验证 Client Id 是否存在
     * @author amoyiki
     * @param clientId
     * @return boolean
     */
    boolean checkClientId(String clientId);
    /**
     *
     * 添加 auth code 
     * @author amoyiki
     * @param authCode
     * @param name
     * @return void
     */
    void addAuthCode(String authCode, String name);
    /**
     *
     * 验证 Client Secret 是否存在
     * @author amoyiki
     * @param clientSecret
     * @return boolean
     */
    boolean checkClientSecret(String clientSecret);
    /**
     *
     * 验证 auth code 是否可用
     * @author amoyiki
     * @param authCode
     * @return boolean
     */
    boolean checkAuthCode(String authCode);
    /**
     *
     * 根据 auth code 获取用户名
     * @author amoyiki
     * @param authCode
     * @return java.lang.String
     */
    String getUsernameByAuthCode(String authCode);
    /**
     *
     * 添加 access token
     * @author amoyiki
     * @param accessToken
     * @param username
     * @return void
     */
    void addAccessToken(String accessToken, String username);

    /**
     *
     * 获取 access token 过期时间
     * @author amoyiki
     * @param
     * @return long
     */
    long getExpireIn();

    /**
     *
     * 验证 access token 是否可用
     * @author amoyiki
     * @param accessToken
     * @return boolean
     */
    boolean checkAccessToken(String accessToken);

    /**
     *
     * 根据 access token 获取用户名
     * @author amoyiki
     * @param accessToken
     * @return java.lang.String
     */
    String getUsernameByAccessToken(String accessToken);
}
