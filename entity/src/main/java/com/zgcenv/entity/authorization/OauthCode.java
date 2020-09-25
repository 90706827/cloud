package com.zgcenv.entity.authorization;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @ClassName OauthCode
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Entity
@Table(name = "oauth_code", schema = "cloud", catalog = "")
public class OauthCode implements Serializable {
    private String code;
    private byte[] authentication;

    @Id
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "authentication")
    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OauthCode oauthCode = (OauthCode) o;

        if (code != null ? !code.equals(oauthCode.code) : oauthCode.code != null) return false;
        if (!Arrays.equals(authentication, oauthCode.authentication)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(authentication);
        return result;
    }
}
