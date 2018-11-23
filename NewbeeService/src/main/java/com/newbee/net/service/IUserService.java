package com.newbee.net.service;

import com.baomidou.mybatisplus.service.IService;
import com.newbee.net.dto.LoginUserDTO;
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

    LoginUserDTO getLoginUserDTOByUserName(String userName);

}
