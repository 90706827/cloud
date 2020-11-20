package com.zgcenv.entity.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName Position
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "position")
public class Position implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.utils.IdUtils")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "deleted")
    private String deleted;

    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Basic
    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Basic
    @Column(name = "created_by")
    private String createdBy;

    @Basic
    @Column(name = "updated_by")
    private String updatedBy;

}
