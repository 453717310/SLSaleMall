package org.slsale.controller.user;

import net.sf.json.JSONObject;
import org.slsale.controller.BaseCotroller;
import org.slsale.pojo.User;
import org.slsale.service.user.UserService;
import org.slsale.util.Constans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by dll on 2017/9/29.
 */
@Controller
public class UserController extends BaseCotroller{
    @Resource
    private UserService userService;
    @PostMapping("/backend/passwordModify.html")
    @ResponseBody
    public Object modifyPassword(@RequestParam String userJson){
        //获取当前登录用户
        User currentUser = this.getCurrentUser();
        if (null==userJson || "".equals(userJson))
            return "noData";
        //解析前台传来的数据
        JSONObject jsonObject = JSONObject.fromObject(userJson);
        User user = (User) jsonObject.toBean(jsonObject, User.class);
        user.setId(currentUser.getId());
        user.setLoginCode(currentUser.getLoginCode());
        try {
            User loginUser = userService.getLoginUser(user);
            if (loginUser==null)
                return "oldPwdWrong";
            user.setPassword(user.getPassword2());
            user.setPassword2(null);
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

}
