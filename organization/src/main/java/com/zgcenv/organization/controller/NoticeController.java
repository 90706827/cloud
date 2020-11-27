package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.organization.service.NoticeService;
import io.swagger.annotations.Api;
import org.fusesource.jansi.internal.Kernel32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/currentUser")
    public Resp<?> findCurrentUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","张先生");
        map.put("avatar","https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        map.put("userid","00000001");
        map.put("email","90706827@163.com");
        map.put("signature","心宿老仙，法律无边！");
        map.put("title","心宿派创始人");
        map.put("group","武林盟主");
        map.put("address","昆仑山山808号");
        map.put("phone","010-88885988");
        map.put("country","宋代");
        map.put("notifyCount",12);
        map.put("unreadCount",11);
        List<KeyLabel> tags = new ArrayList<>();
        tags.add(new KeyLabel("1","最强无影脚"));
        tags.add(new KeyLabel("2","最毒八卦阵"));
        tags.add(new KeyLabel("3","来无影去无踪"));
        tags.add(new KeyLabel("4","采花大盗"));
        map.put("tags",tags);
        Map<String,Object> geographic = new HashMap<>();
        geographic.put("province",new KeyLabel("330100","东南疆域"));
        geographic.put("city",new KeyLabel("330102","大理寺"));
        map.put("geographic",geographic);
        return Resp.success(map);
    }
}
