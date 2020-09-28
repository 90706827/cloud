package com.zgcenv.entity.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName GatewayRoute
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gateway_route")
public class GatewayRoute implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idUtils")
    @GenericGenerator(name = "idUtils", strategy = "com.zgcenv.entity.utils.IdUtils")
    private Long id;

    @Basic
    @Column(name = "route_id")
    private String routeId;

    @Basic
    @Column(name = "uri")
    private String uri;

    @Basic
    @Column(name = "predicates")
    private String predicates;

    @Basic
    @Column(name = "filters")
    private String filters;

    @Basic
    @Column(name = "orders")
    private Integer orders;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "status")
    private String status;

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
