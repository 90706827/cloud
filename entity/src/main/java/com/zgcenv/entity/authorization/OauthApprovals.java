package com.zgcenv.entity.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_approvals")
public class OauthApprovals implements Serializable {

    @Id
    @Column(name = "userid")
    private String userid;

    @Basic
    @Column(name = "clientid")
    private String clientid;

    @Basic
    @Column(name = "scope")
    private String scope;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "expiresat")
    private Timestamp expiresat;

    @Basic
    @Column(name = "lastmodifiedat")
    private Timestamp lastmodifiedat;

}
