package org.slsale.service.user;

import org.slsale.pojo.User;
/**
 * Created by dll on 2017/9/20.
 * 用户的业务类
 */
public interface UserService {
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
