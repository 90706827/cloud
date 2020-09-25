package com.zgcenv.entity.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName OauthAccessToken
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken implements Serializable {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.oauth.utils.IdUtils")
    private String tokenId;

    @Basic
    @Column(name = "token")
    private byte[] token;

    @Basic
    @Column(name = "authentication_id")
    private String authenticationId;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "client_id")
    private String clientId;

    @Basic
    @Column(name = "authentication")
    private byte[] authentication;

    @Basic
    @Column(name = "refresh_token")
    private String refreshToken;

}
