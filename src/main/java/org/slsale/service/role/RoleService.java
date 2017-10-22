package org.slsale.service.role;

import org.slsale.pojo.Role;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dll on 2017/9/30.
 */
public interface RoleService{
    /**
     *获取角色列表
     * @return
     */
    List<Role> getRoleList() throws Exception;
    /**
     *获取所有的角色列表
     * @return
     * @throws Exception
     */
    List<Role> getAllRoleList() throws Exception;
    /**
     * 新增角色
     * @param role
     * @return
     * @throws Exception
     */
    int addRole(Role role) throws Exception;
    /**
     * 根据角色
     * @param id
     * @return
     * @throws Exception
     */
    int deleteRole(Serializable id) throws Exception;
    /**
     * 根据id修改角色
     * @param role
     * @return
     * @throws Exception
     */
    boolean updateRole(Role role) throws Exception;
}
