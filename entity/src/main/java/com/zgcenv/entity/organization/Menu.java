package com.zgcenv.entity.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Menu
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Menu implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.utils.IdUtils")
    private Long id;

    @Basic
    @Column(name = "parent_id")
    private Long parentId;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "href")
    private String href;

    @Basic
    @Column(name = "icon")
    private String icon;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "order_num")
    private Integer orderNum;
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
