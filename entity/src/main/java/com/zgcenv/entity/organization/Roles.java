package com.zgcenv.entity.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Roles
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Roles implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.utils.IdUtils")
    private Long id;

    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "created_time")
    private Date createdTime;

    @Basic
    @Column(name = "updated_time")
    private Date updatedTime;

    @Basic
    @Column(name = "created_by")
    private String createdBy;

    @Basic
    @Column(name = "updated_by")
    private String updatedBy;
}
