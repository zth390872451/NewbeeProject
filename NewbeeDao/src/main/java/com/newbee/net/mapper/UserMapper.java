package com.newbee.net.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newbee.net.dto.LoginUserDTO;
import com.newbee.net.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zheng.th
 * @since 2018-11-22
 */
public interface UserMapper extends BaseMapper<User> {

    LoginUserDTO selectLoginUserDTOByUserName(String userName);
}
