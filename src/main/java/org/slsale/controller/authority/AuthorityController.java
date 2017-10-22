package org.slsale.controller.authority;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.FunctionRoles;
import org.slsale.pojo.Role;
import org.slsale.service.authority.AuthorityService;
import org.slsale.service.menu.FunctionService;
import org.slsale.service.role.RoleService;
import org.slsale.util.Constans;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.SunHints;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dll on 2017/10/21.
 * @author dll
 */
@Controller
public class AuthorityController {
    /**
     * 日志对象
     */
    private Logger logger=Logger.getLogger(AuthorityController.class);
    /**
     * 角色
     */
    @Resource
    private RoleService roleService;
    /**
     * 功能
     */
    @Resource
    private FunctionService functionService;

    /**
     * 权限
     * @param session
     * @param model
     * @return
     */
    @Resource
    private AuthorityService authorityService;
    @GetMapping("/backend/authoritymanage.html")
    public String authoritymanage(HttpSession session, Model model){
        /**
         * 获取session里面的列表
         */
        Map<String,Object> baseMenu= (Map<String, Object>) session.getAttribute(Constans.SESSION_BASE_MODEL);
        if (baseMenu==null){
            return "redirect:/";
        }
        List<Role> roleList=null;
        try {
            roleList= roleService.getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAllAttributes(baseMenu);
        model.addAttribute("roleList",roleList);
        return "/backend/authorityManage";
    }

    /**
     * 获取功能列表
     */
    @PostMapping(value="/backend/functionList.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object getFunctionList(){
        Function function=new Function();
        function.setId(0);
        String result="";
        try {
            /**
             * 获取一级功能
             */
            List<Function> firstFunction = functionService.getFirstFunction(function);
            List<FunctionRoles> rolesList=new ArrayList<>();
            /**
             * 存放一级  二级功能列表
             */
            FunctionRoles functionRoles=null;
            if (firstFunction!=null) {
                for (Function function1 : firstFunction) {
                    functionRoles = new FunctionRoles();
                    functionRoles.setMainFunction(function1);
                    functionRoles.setSubFunctionList(functionService.getFirstFunction(function1));
                    rolesList.add(functionRoles);
                }
                result = JSONArray.fromObject(rolesList).toString();
                logger.debug("getFunctionList============" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result="noData";
        }
        return result;
    }

    /**
     * 获取权限对象
     * @param rid
     * @param fid
     * @return
     */
    @PostMapping(value = "/backend/getAuthorityDefault.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object getAuthorityDefault(@RequestParam(value="rid",required = false) Integer rid,
                                      @RequestParam(value = "fid",required = false) Integer fid){
        String result="noData";
        Authority authority=new Authority();
        authority.setRoleId(rid);
        authority.setFunctionId(fid);
        try {
            authority = authorityService.getAuthority(authority);
            if(authority!=null){
                result="success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result="noData";
        }
        return result;
    }
}
