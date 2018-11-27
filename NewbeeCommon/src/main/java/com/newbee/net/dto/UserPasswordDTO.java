package com.newbee.net.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 13:48
 */
@ApiModel(description="用户密码信息")
public class UserPasswordDTO {
    @ApiModelProperty(notes = "登录名称")
    private String userName;
    @ApiModelProperty(notes = "登录密码")
    private String password;
    @ApiModelProperty(notes = "新的登录密码,修改密码接口使用")
    private String newPassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
