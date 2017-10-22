package org.slsale.controller.role;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.slsale.controller.BaseCotroller;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.role.RoleService;
import org.slsale.service.user.UserService;
import org.slsale.util.Constans;
import org.slsale.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.relation.RoleList;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dll on 2017/9/30.
 * @author dll
 */
@Controller
public class RoleController extends BaseCotroller {
    private Logger logger=Logger.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @PostMapping("/roleName")
    @ResponseBody
    public List<Role> roleName(){
        List<Role> roleList=null;
        try {
            roleList = roleService.getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }
    @GetMapping("/backend/rolelist.html")
    public String getRoleList(Model model, HttpSession session){
        Map<String,Object> map= (Map<String, Object>) session.getAttribute(Constans.SESSION_BASE_MODEL);
        model.addAllAttributes(map);
        List<Role> list=null;
        try {
            list=roleService.getAllRoleList();
            model.addAttribute("roleList",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/backend/roleList";
    }

    /**
     * 新增角色
     * @param roleCode
     * @param roleName
     * @return
     */
    @PostMapping(value = "/backend/roleAdd.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object addRole(@RequestParam(value = "roleCode",required = false) String roleCode,
                          @RequestParam(value = "roleName",required = false) String roleName){
        if(StringUtils.isEmpty(roleCode)|| StringUtils.isEmpty(roleName)) {
            return "noData";
        } else{
            Role role=new Role();
            role.setCreateDate(new Date());
            role.setCreatedBy(this.getCurrentUser().getLoginCode());
            role.setIsStart(1);
            role.setRoleCode(roleCode);
            role.setRoleName(roleName);
            try {
                roleService.addRole(role);
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
        return "success";
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @PostMapping(value = "/backend/deleteRole.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object delRole(@RequestParam(value = "roleId") String roleId){
        if(StringUtils.isEmpty(roleId)) {
            return "noData";
        } else {
            try {
                User user = userService.getUser(Integer.valueOf(roleId));
                if(user!=null) {
                    return "haveData";
                }
                else {
                    int i = roleService.deleteRole(Integer.valueOf(roleId));
                    if (i>0) {
                        return "success";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
        return "failed";
    }

    /**
     * 修改角色状态
     * @param roleId
     * @param isStart
     * @return
     */
    @PostMapping(value = "/backend/isStart.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object isStart(@RequestParam(value = "roleId",required = false) String roleId,
                          @RequestParam(value = "isStart",required = false) String isStart){
        if(StringUtils.isEmpty(roleId) || StringUtils.isEmpty(isStart)) {
            return "noData";
        } else {
            Role role=new Role();
            Integer is = Integer.valueOf(isStart);
            if(is==0) {
                role.setIsStart(1);
            }
            else {
                role.setIsStart(0);
            }
            role.setId(Integer.valueOf(roleId));
            try {
                boolean b = roleService.updateRole(role);
                if(b) {
                    return "success";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
        return "failed";
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @RequestMapping("/backend/modifyRole.html")
    @ResponseBody
    public Object modifyRole(@RequestParam String role){
        if(null == role || "".equals(role)){
            return "nodata";
        }else{
            JSONObject roleObject = JSONObject.fromObject(role);
            Role roleObjRole =  (Role)JSONObject.toBean(roleObject, Role.class);
            //roleObjRole.setIsStart(1);
            try {
                roleService.updateRole(roleObjRole);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                return "failed";
            }
            return "success";
        }

    }
}
