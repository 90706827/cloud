package com.zgcenv.organization.dao;

import com.zgcenv.entity.organization.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Roles, Long> {

    List<Roles> findAllRolesById(Long userId);
}
