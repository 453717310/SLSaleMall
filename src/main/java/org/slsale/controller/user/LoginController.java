package org.slsale.controller.user;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.slsale.controller.BaseCotroller;
import org.slsale.pojo.Authority;
import org.slsale.pojo.Function;
import org.slsale.pojo.Menu;
import org.slsale.pojo.User;
import org.slsale.service.menu.FunctionService;
import org.slsale.service.user.UserService;
import org.slsale.util.Constans;
import org.slsale.util.redis.impl.RedisAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dll on 2017/9/20.
 * 关于用户的前台数据请求处理的controller
 * @author dll
 */
@Controller
public class LoginController extends BaseCotroller{
    private Logger logger=Logger.getLogger(LoginController.class);
    @Resource
    private UserService userService;
    @Resource
    private FunctionService functionService;
    @Resource
    private RedisAPI redisAPI;

    /**
     * 登录验证
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/login.html")
    @ResponseBody
    public Object login(HttpSession session, @RequestParam String user){
        if (user==null || "".equals(user)) {
            return "noData";
        }
        //解析前台传过来的json数据
        JSONObject jsonObject = JSONObject.fromObject(user);
        User userObj = (User) JSONObject.toBean(jsonObject, User.class);
        try {
            if (userService.loginCodeIsExist(userObj)==0)//用户不存在
            {
                return "notExistLoginCode";
            }
            User loginUser = userService.getLoginUser(userObj);
            logger.debug("当前用户"+loginUser);
            if (loginUser==null) {
                return "passwordError";
            }
            //登录成功 把用户放到session中
            session.setAttribute(Constans.SESSION_USER,loginUser);
            //更新用户的登录时间
            User _user=new User();
            _user.setId(loginUser.getId());
            _user.setLastUpdateTime(new Date());
            userService.updateUser(_user);
            _user=null;
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

    }

    /**
     * 把数据返回到前台
     * @param session
     * @return
     */
    @RequestMapping("/main.html")
    public ModelAndView main(HttpSession session){
        logger.debug("进入了main页面！！！！");
        User currentUser = this.getCurrentUser();
        //菜单列表
        List<Menu> menuList=null;
        if (null==currentUser) {
            return new ModelAndView("redirect:/");
        }
        Map<String,Object> model=new HashMap<>();
        model.put("user",currentUser);
        /**
         * key:menuList+roleId value:MenuList
         */
        if (redisAPI.exist("menuList"+currentUser.getRoleId())){
            //获取数据
            menuList = (List<Menu>) redisAPI.get("menuList" + currentUser.getRoleId());
            logger.debug("jedisList========="+menuList);
            if (menuList==null || "".equals(menuList)) {
                return new ModelAndView("redirect:/");
            }
            JSONArray jsonArray = JSONArray.fromObject(menuList);
            String redisList = jsonArray.toString();
            model.put("mList",redisList);
        }else {
            menuList=getMenuListByRoleId(currentUser.getRoleId());
            if (null==menuList) {
                return new ModelAndView("main", model);
            }
            //转换成json数据
            JSONArray jsonArray = JSONArray.fromObject(menuList);
            String s = jsonArray.toString();
            logger.debug("获得一级二级菜单的数据"+s);
            model.put("mList",s);//存放到model中
            redisAPI.set("menuList" + currentUser.getRoleId(),menuList);
        }
        session.setAttribute(Constans.SESSION_BASE_MODEL,model);
        return new ModelAndView("main",model);
    }

    /**
     * 获取主菜单 与子菜单列表
     * @param roleId
     * @return
     */
    private List<Menu> getMenuListByRoleId(Integer roleId) {
        List<Menu> list=new ArrayList<Menu>();
        //创建权限对象
        Authority authority=new Authority();
        authority.setRoleId(roleId);
        //调用后台方法查询一级菜单
        try {
            List<Function> firstFunctionList = functionService.getFirstFunctionList(authority);
            if (firstFunctionList==null) {
                return null;
            }
            for (Function function : firstFunctionList) {
                Menu menu=new Menu();
                menu.setMainMenu(function);
                //根据一级菜单 查询下面的二级菜单
                function.setRoleId(roleId);
                List<Function> twoFunctionList = functionService.getTwoFunctionList(function);
                if (twoFunctionList==null) {
                    continue;
                }
                menu.setSubMenus(twoFunctionList);
                list.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 注销页面
     */
    @RequestMapping("/loginOut.html")
    public String loginOut(HttpSession session){
        //session里面的用户失效
        session.invalidate();
        this.setCurrentUser(null);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
}
