package com.zgcenv.organization.dao;

import com.zgcenv.entity.organization.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName ResourceDao
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
@Repository
public interface ResourceDao extends JpaRepository<Resources, Long> {

    @Modifying
    @Query(value = "SELECT r.* FROM resources r left join role_resource_relation rr ON r.id = rr.resource_id left join roles ro on rr.role_id = ro.id \tleft join user_role_relation ur on ro.id = ur.role_id left join users u on ur.user_id = u.id where u.username = ?1", nativeQuery = true)
    List<Resources> findResourceByUsername(String username);

}
