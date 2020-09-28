package com.zgcenv.organization.dao;

import com.zgcenv.entity.gateway.GatewayRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName GatewayRouteDao
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/
@Repository
public interface GatewayRouteDao extends JpaRepository<GatewayRoute, Long> {

    List<GatewayRoute> findAllByUriLike(String uri);
}
