package org.slsale.service.user.impl;

import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.User;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dll on 2017/9/20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 获取登录用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User getLoginUser(User user) throws Exception {
        return userMapper.getLoginUser(user);
    }

    /**
     * 判断登录用户名是否存在
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int loginCodeIsExist(User user) throws Exception {
        return userMapper.loginCodeIsExist(user);
    }

    /**
     * 根据id 更新用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int updateUser(User user) throws Exception {
        return userMapper.updateUser(user);
    }
}
