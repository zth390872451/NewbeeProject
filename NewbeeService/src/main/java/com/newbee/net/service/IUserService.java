package com.newbee.net.service;

import com.baomidou.mybatisplus.service.IService;
import com.newbee.net.dto.UserRoleInfoDTO;
import com.newbee.net.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zheng.th
 * @since 2018-11-22
 */
public interface IUserService extends IService<User> {
    /**
     * 获取用户身份权限信息
     * @param userName 登录用户名
     * @return 用户身份信息
     */
    UserRoleInfoDTO getLoginUserDTOByUserName(String userName);

}
