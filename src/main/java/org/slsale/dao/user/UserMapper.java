package org.slsale.dao.user;

import org.slsale.pojo.User;
import org.springframework.stereotype.Repository;

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
}
