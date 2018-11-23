package com.newbee.net.controller;

import com.newbee.net.dto.LoginUserDTO;
import com.newbee.net.resp.CustomResponse;
import com.newbee.net.service.IUserService;
import com.newbee.net.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.newbee.net.resp.CustomResponseBuilder.fail;
import static com.newbee.net.resp.CustomResponseBuilder.success;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 10:35
 */
@Api(tags = "swagger2-测试")
@RestController
@RequestMapping("/user-info")
public class LoginController {

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "登录获取JwtToken", notes = "登录")
    @PostMapping("/login")
    @ResponseBody
    public CustomResponse login(@RequestBody LoginUserDTO loginUserDTO) {
        LoginUserDTO loginUser = userService.getLoginUserDTOByUserName(loginUserDTO.getUserName());
        Assert.notNull(loginUser, "用户名不存在!");
        if (loginUser.getPassword().equals(loginUserDTO.getPassword())) {
            String token = JwtUtil.sign(loginUser.getUserName(), loginUser.getSecret());
            Assert.notNull(token, "JwtToken生成失败，请重试!");
            return success(token);
        } else {
            return fail("密码错误,请重新输入!");
        }
    }



    @GetMapping("/article")
    @ApiOperation(value = "article", notes = "")
    @ResponseBody
    public CustomResponse article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return success("you has login!");
        }else {
            return success("you are guest!");
        }

    }

    @ApiOperation(value = "add", notes = "")
    @GetMapping("/add")
    @RequiresRoles("admin")
    @ResponseBody
    public CustomResponse add() {
        return success();
    }

    @ApiOperation(value = "delete", notes = "")
    @RequiresRoles("admin")
    @GetMapping("/delete")
    @ResponseBody
    public CustomResponse delete() {
        return success();
    }

    @ApiOperation(value = "update", notes = "")
    @RequiresRoles("admin")
    @GetMapping("/update")
    @ResponseBody
    public CustomResponse update() {
        return success();
    }
    @ApiOperation(value = "get", notes = "")
    @RequiresRoles("common")
    @GetMapping("/get")
    @ResponseBody
    public CustomResponse get() {
        return success();
    }
}
