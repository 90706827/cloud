package com.zgcenv.organization.dao;

import com.zgcenv.entity.organization.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeDao extends JpaRepository<Notices,Long> {

}
