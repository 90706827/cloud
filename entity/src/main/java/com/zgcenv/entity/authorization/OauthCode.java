package com.zgcenv.entity.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName OauthCode
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_code")
public class OauthCode implements Serializable {

    @Id
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "authentication")
    private byte[] authentication;

}
