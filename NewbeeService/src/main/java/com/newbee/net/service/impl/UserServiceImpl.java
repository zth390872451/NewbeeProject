package com.newbee.net.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newbee.net.dto.UserRoleInfoDTO;
import com.newbee.net.entity.User;
import com.newbee.net.mapper.UserMapper;
import com.newbee.net.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zheng.th
 * @since 2018-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserRoleInfoDTO getLoginUserDTOByUserName(String userName) {
        return  this.baseMapper.selectLoginUserDTOByUserName(userName);
    }
}
