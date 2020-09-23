package com.zgcenv.organization.dao;

import com.zgcenv.entity.organization.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UsersDao
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
