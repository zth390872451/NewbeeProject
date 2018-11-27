package com.newbee.net.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newbee.net.dto.UserRoleInfoDTO;
import com.newbee.net.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zheng.th
 * @since 2018-11-22
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取用户身份权限信息
     * @param userName 登录用户名
     * @return 用户身份信息
     */
    UserRoleInfoDTO selectLoginUserDTOByUserName(String userName);
}
