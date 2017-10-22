package org.slsale.service.user.impl;

import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.User;
import org.slsale.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    /**
     * 模糊查询加分页
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getUserList(Map<String, Object> map) throws Exception {
        return userMapper.getUserList(map);
    }

    /**
     * 获取总记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public int getCount(Map<String, Object> map) throws Exception {
        return userMapper.getCount(map);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int addUser(User user) throws Exception {
        return userMapper.addUser(user);
    }

    /**
     * 删除图片
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int delUserPic(User user) throws Exception {
        return userMapper.delUserPic(user);
    }

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public User getUser(Serializable id) throws Exception {
        return userMapper.getUser(id);
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int deleteUser(User user) throws Exception {
        return userMapper.deleteUser(user);
    }

    /**
     * 根据角色id查询用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public User getUserByRoleId(Serializable id) throws Exception {
        return userMapper.getUserByRoleId(id);
    }

    /**
     * 修改roleName
     *
     * @param user
     */
    @Override
    public int modifyUserRole(User user) {
        return userMapper.modifyUserRole(user);
    }
}
