package com.amoyiki.springtest.entry;

import javax.persistence.*;

@Table(name = "`client`")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    /**
     * 客戶端名稱
     */
    @Column(name = "`client_name`")
    private String clientName;

    /**
     * 客戶端ID
     */
    @Column(name = "`client_id`")
    private String clientId;

    /**
     * 客户端安全key
     */
    @Column(name = "`client_secret`")
    private String clientSecret;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客戶端名稱
     *
     * @return client_name - 客戶端名稱
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * 设置客戶端名稱
     *
     * @param clientName 客戶端名稱
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * 获取客戶端ID
     *
     * @return client_id - 客戶端ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客戶端ID
     *
     * @param clientId 客戶端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取客户端安全key
     *
     * @return client_secret - 客户端安全key
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 设置客户端安全key
     *
     * @param clientSecret 客户端安全key
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}