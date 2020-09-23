package com.zgcenv.organization.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zgcenv.entity.organization.Resource;
import com.zgcenv.organization.dao.ResourceDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ResourceService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
@Service
public class ResourceService {
    @javax.annotation.Resource
    private ResourceDao resourceDao;

    @Cached(name = "resource4user::", key = "#username", cacheType = CacheType.BOTH)
    public List<Resource> findResourceByUsername(String username) {
        return resourceDao.findResourceByUsername(username);
    }

    public List<Resource> findAll() {
        return resourceDao.findAll();
    }
}
