package com.newbee.net.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newbee.net.dto.UserRoleInfoDTO;
import com.newbee.net.entity.User;
import com.newbee.net.mapper.UserMapper;
import com.newbee.net.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransaction() throws Exception {
        User user = new User();
        user.setUserName(RandomStringUtils.randomAlphabetic(3));
        user.setPassword(RandomStringUtils.randomAlphanumeric(4));
        user.setSecret(RandomStringUtils.randomAlphanumeric(3));
        user.setUserNo(RandomUtils.nextInt(1,2000));
        int a = 10;
        int b= 0;
        this.insert(user);
        int i = a / b;
    }

}
