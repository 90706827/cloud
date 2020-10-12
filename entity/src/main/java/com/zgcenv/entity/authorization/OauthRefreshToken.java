package com.zgcenv.entity.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName OauthRefreshToken
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Basic
    @Column(name = "token")
    private byte[] token;

    @Basic
    @Column(name = "authentication")
    private byte[] authentication;

}
