package com.newbee.net.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.newbee.net.dto.LoginDTO;
import com.newbee.net.dto.UserPasswordDTO;
import com.newbee.net.dto.UserRoleInfoDTO;
import com.newbee.net.entity.User;
import com.newbee.net.jwt.JwtUtil;
import com.newbee.net.resp.CustomResponse;
import com.newbee.net.service.IUserService;
import com.newbee.net.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

import static com.newbee.net.resp.CustomResponseBuilder.fail;
import static com.newbee.net.resp.CustomResponseBuilder.success;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 10:35
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;



    @ApiOperation(value = "登录获取Token", notes = "SSO登录,将原有Token失效")
    @PostMapping("/login")
    @ResponseBody
    public CustomResponse login(@RequestBody LoginDTO loginUserDTO) {
        UserRoleInfoDTO userPasswordDTO = userService.getLoginUserDTOByUserName(loginUserDTO.getUserName());
        Assert.notNull(userPasswordDTO, "用户名不存在!");
        if (userPasswordDTO.getPassword().equals(loginUserDTO.getPassword())) {
            User user = new User();
            user.setSecret(RandomStringUtils.randomAlphanumeric(6));
            EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            userEntityWrapper.eq("user_name",userPasswordDTO.getUserName());
            userService.update(user,userEntityWrapper);
            String token = JwtUtil.sign(userPasswordDTO.getUserName(), user.getSecret());
            Assert.notNull(token, "Token生成失败，请重试!");
            return success(token);
        } else {
            return fail("密码错误,请重新输入!");
        }
    }


    @PostMapping("/update/password")
    @ApiOperation(value = "修改密码", notes = "修改密码和token密钥")
    @ResponseBody
    public CustomResponse register(@RequestBody UserPasswordDTO userPasswordDTO) {
        Assert.notNull(userPasswordDTO.getNewPassword(),"新密码不能为空！");
        UserRoleInfoDTO loginUser = userService.getLoginUserDTOByUserName(userPasswordDTO.getUserName());
        Assert.notNull(loginUser, "用户名不存在!");
        if (loginUser.getPassword().equals(userPasswordDTO.getPassword())) {
            User user = new User();
            user.setPassword(userPasswordDTO.getNewPassword());
            user.setSecret(RandomStringUtils.randomAlphanumeric(6));
            EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            userEntityWrapper.eq("user_name",loginUser.getUserName());
            userService.update(user,userEntityWrapper);
            return success();
        } else {
            return fail("密码错误,请重新输入!");
        }
    }

    @ApiOperation(value = "add", notes = "")
    @PostMapping("/add")
    @RequiresRoles("admin")
    @ResponseBody
    public CustomResponse add() {
        return success();
    }

    @ApiOperation(value = "delete", notes = "")
    @RequiresRoles("admin")
    @PostMapping("/delete")
    @ResponseBody
    public CustomResponse delete() {
        return success();
    }

    @ApiOperation(value = "update", notes = "")
    @RequiresRoles("admin")
    @PostMapping("/update")
    @ResponseBody
    public CustomResponse update() {
        return success();
    }
    @ApiOperation(value = "get", notes = "")
    @RequiresRoles("common")
    @PostMapping("/get")
    @ResponseBody
    public CustomResponse get() {
        return success();
    }

    @ApiOperation(value = "设置键值对", notes = "设置键值对")
    @PostMapping("/setRedis")
    @ResponseBody
    public CustomResponse setRedis(@RequestParam String key, @RequestParam String value) {
        redisUtil.sAdd(key,value);
        return success();
    }
}
