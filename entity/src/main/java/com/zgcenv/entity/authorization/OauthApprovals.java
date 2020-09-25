package com.zgcenv.entity.authorization;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName OauthApprovals
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Entity
@Table(name = "oauth_approvals", schema = "cloud", catalog = "")
public class OauthApprovals implements Serializable {
    private String userid;
    private String clientid;
    private String scope;
    private String status;
    private Timestamp expiresat;
    private Timestamp lastmodifiedat;

    @Id
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "clientid")
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "expiresat")
    public Timestamp getExpiresat() {
        return expiresat;
    }

    public void setExpiresat(Timestamp expiresat) {
        this.expiresat = expiresat;
    }

    @Basic
    @Column(name = "lastmodifiedat")
    public Timestamp getLastmodifiedat() {
        return lastmodifiedat;
    }

    public void setLastmodifiedat(Timestamp lastmodifiedat) {
        this.lastmodifiedat = lastmodifiedat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OauthApprovals that = (OauthApprovals) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (clientid != null ? !clientid.equals(that.clientid) : that.clientid != null) return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (expiresat != null ? !expiresat.equals(that.expiresat) : that.expiresat != null) return false;
        if (lastmodifiedat != null ? !lastmodifiedat.equals(that.lastmodifiedat) : that.lastmodifiedat != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (clientid != null ? clientid.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (expiresat != null ? expiresat.hashCode() : 0);
        result = 31 * result + (lastmodifiedat != null ? lastmodifiedat.hashCode() : 0);
        return result;
    }
}
