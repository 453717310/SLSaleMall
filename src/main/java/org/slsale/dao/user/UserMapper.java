package org.slsale.dao.user;

import org.slsale.pojo.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dll on 2017/9/20.
 * 用户类接口
 */
@Repository
public interface UserMapper {
    /**
     * 获取登录用户
     * @param user
     * @return
     * @throws Exception
     */
    User getLoginUser(User user) throws Exception;

    /**
     * 判断登录用户名是否存在
     * @param user
     * @return
     * @throws Exception
     */
    int loginCodeIsExist(User user) throws Exception;
    /**
     * 根据id 更新用户
     * @param user
     * @return
     * @throws Exception
     */
    int updateUser(User user) throws Exception;

    /**
     * 模糊查询加分页
     * @param map
     * @return
     * @throws Exception
     */
    List<User> getUserList(Map<String,Object> map) throws Exception;

    /**
     * 获取总记录数
     * @return
     * @throws Exception
     */
    int getCount(Map<String,Object> map) throws Exception;

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    int addUser(User user) throws Exception;

    /**
     * 删除图片
     * @param user
     * @return
     * @throws Exception
     */
    int delUserPic(User user) throws Exception;

    /**
     * 通过id获取用户
     * @param id
     * @return
     * @throws Exception
     */
    User getUser(Serializable id)throws Exception;

    /**
     * 删除用户
     * @param user
     * @return
     * @throws Exception
     */
    int deleteUser(User user) throws Exception;

    /**
     * 根据角色id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    User getUserByRoleId(Serializable id) throws Exception;

    /**
     * 修改roleName
     * @param user
     */
    int modifyUserRole(User user);
}
