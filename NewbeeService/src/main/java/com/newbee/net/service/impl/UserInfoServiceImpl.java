package com.newbee.net.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newbee.net.entity.UserInfo;
import com.newbee.net.mapper.UserInfoMapper;
import com.newbee.net.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录人员表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-11-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
