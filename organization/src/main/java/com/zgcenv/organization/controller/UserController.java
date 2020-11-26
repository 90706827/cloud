package com.zgcenv.organization.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Users;
import com.zgcenv.entity.vo.UserVo;
import com.zgcenv.organization.service.UserService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Api("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "获取用户", notes = "根据用户名称获取用户信息")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true, dataType = "string")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Resp.class))
    @GetMapping("/user/findUserByUsername")
    public Resp<Users> findUserByUsername(@RequestParam String username) {
        logger.info("query with username or mobile:{}", username);
        return Resp.success(userService.findUserByUsername(username));
    }


    @GetMapping(value = "/user/findUserByMobile")
    Resp<Users> findUserByMobile(@RequestParam("mobile") String mobile) {
        logger.info("query with username or mobile:{}", mobile);
        return Resp.success(userService.findUserByUsername(mobile));
    }


    @GetMapping("/user/findUserInfoByUsername")
    public Resp<UserVo> findUserInfoByUsername(@RequestParam String username){
        Users users = userService.findUserByUsername(username);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(users,userVo);
        return Resp.success(userVo);
    }

    @GetMapping("/currentUser")
    public Resp<String> findCurrentUser(){
        return Resp.success("{name: 'Serati Ma',\n" +
                "    avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',\n" +
                "    userid: '00000001',\n" +
                "    email: 'antdesign@alipay.com',\n" +
                "    signature: '海纳百川，有容乃大',\n" +
                "    title: '交互专家',\n" +
                "    group: '蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED',\n" +
                "    tags: [\n" +
                "      {\n" +
                "        key: '0',\n" +
                "        label: '很有想法的',\n" +
                "      },\n" +
                "      {\n" +
                "        key: '1',\n" +
                "        label: '专注设计',\n" +
                "      },\n" +
                "      {\n" +
                "        key: '2',\n" +
                "        label: '辣~',\n" +
                "      },\n" +
                "      {\n" +
                "        key: '3',\n" +
                "        label: '大长腿',\n" +
                "      },\n" +
                "      {\n" +
                "        key: '4',\n" +
                "        label: '川妹子',\n" +
                "      },\n" +
                "      {\n" +
                "        key: '5',\n" +
                "        label: '海纳百川',\n" +
                "      },\n" +
                "    ],\n" +
                "    notifyCount: 12,\n" +
                "    unreadCount: 11,\n" +
                "    country: 'China',\n" +
                "    geographic: {\n" +
                "      province: {\n" +
                "        label: '浙江省',\n" +
                "        key: '330000',\n" +
                "      },\n" +
                "      city: {\n" +
                "        label: '杭州市',\n" +
                "        key: '330100',\n" +
                "      },\n" +
                "    },\n" +
                "    address: '西湖区工专路 77 号',\n" +
                "    phone: '0752-268888888'}");
    }

}