package com.zgcenv.organization.service;

import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.organization.dao.GatewayRouteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GatewayRouteService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/
@Service
public class GatewayRouteService {
    private static final Logger log = LoggerFactory.getLogger(GatewayRouteService.class);

    @Resource
    private GatewayRouteDao gatewayRouteDao;

    public List<GatewayRoute> all() {
        List<GatewayRoute> list = gatewayRouteDao.findAll();
        log.info("list size:{}", list.size());
        return list;
    }
}
