package com.zgcenv.organization.service;

import com.zgcenv.entity.organization.Notices;
import com.zgcenv.organization.dao.NoticeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class NoticeService {

    @Resource
    private NoticeDao noticeDao;

    public List<Notices> findAll() {
        return noticeDao.findAll();
    }
}
