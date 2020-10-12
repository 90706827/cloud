package com.zgcenv.entity.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName OauthClientDetails
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails implements Serializable {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Basic
    @Column(name = "resource_ids")
    private String resourceIds;

    @Basic
    @Column(name = "client_secret")
    private String clientSecret;

    @Basic
    @Column(name = "scope")
    private String scope;

    @Basic
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Basic
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @Basic
    @Column(name = "authorities")
    private String authorities;

    @Basic
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Basic
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Basic
    @Column(name = "additional_information")
    private String additionalInformation;

    @Basic
    @Column(name = "autoapprove")
    private String autoapprove;

}
