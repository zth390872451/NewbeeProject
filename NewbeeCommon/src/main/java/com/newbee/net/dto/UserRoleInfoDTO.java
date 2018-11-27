package com.newbee.net.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 13:48
 */
@ApiModel(description="用户信息")
public class UserRoleInfoDTO {
    @ApiModelProperty(notes = "登录名称")
    private String userName;
    @ApiModelProperty(notes = "登录密码")
    private String password;
    @ApiModelProperty(notes = "用户拥有的角色,多个角色用逗号隔开")
    private String role;
    @ApiModelProperty(notes = "权限，逗号隔开")
    private String permission;
    @ApiModelProperty(notes = "token生成使用的密钥")
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
