package org.slsale.service.role.impl;

import org.slsale.dao.role.RoleMapper;
import org.slsale.dao.user.UserMapper;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dll on 2017/9/30.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    /**
     * 获取角色列表
     *
     * @return
     */
    @Override
    public List<Role> getRoleList() throws Exception{
        return roleMapper.getRoleList();
    }

    /**
     * 获取所有的角色列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> getAllRoleList() throws Exception {
        return roleMapper.getAllRoleList();
    }

    /**
     * 新增角色
     * @param role
     * @return
     * @throws Exception
     */
    @Override
    public int addRole(Role role) throws Exception {
        return roleMapper.addRole(role);
    }

    /**
     * 根据角色
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deleteRole(Serializable id) throws Exception {
        return roleMapper.deleteRole(id);
    }

    /**
     * 根据id修改角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateRole(Role role) throws Exception {
        roleMapper.updateRole(role);
        int roleId = role.getId();
        String roleName = role.getRoleName();
        User user = new User();
        user.setRoleId(roleId);
        user.setRoleName(roleName);
        if(null != roleName && !"".equals(roleName)){
            userMapper.modifyUserRole(user);
        }
        return true;
    }
}
