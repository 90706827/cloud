package com.zgcenv.gateway.admin.entity;

import com.zgcenv.core.entity.BaseForm;
import com.zgcenv.entity.gateway.GatewayRoute;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName GatewayRouteForm
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/
@ApiModel
@Data
public class GatewayRouteForm extends BaseForm<GatewayRoute> {

    private Long id;
    private String routeId;
    private String uri;
    private String predicates;
    private String filters;
    private Integer orders;
    private String description;
    private String status;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
}
