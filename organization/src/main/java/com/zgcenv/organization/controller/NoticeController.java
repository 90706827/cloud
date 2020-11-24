package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.organization.service.NoticeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("Notices")
@RestController
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Resource
    private NoticeService noticeService;

    @GetMapping(value = "/notices")
    public Resp notices() {
        return Resp.success(noticeService.findAll());
    }
}
