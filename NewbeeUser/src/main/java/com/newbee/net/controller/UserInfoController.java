package com.newbee.net.controller;


import com.newbee.net.entity.UserInfo;
import com.newbee.net.resp.CustomResponse;
import com.newbee.net.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.newbee.net.resp.CustomResponseBuilder.success;

/**
 * <p>
 * 登录人员表 前端控制器
 * </p>
 *
 * @author zheng.th
 * @since 2018-11-21
 */
@Api(tags = "swagger2-测试")
@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @ApiOperation(value = "测试swagger2", notes = "demo")
    @GetMapping("/list")
    @ResponseBody
    public CustomResponse list(HttpServletRequest request) {
        UserInfo userInfo = userInfoService.selectById(810);
        return success(userInfo.getStatus());
    }

}

