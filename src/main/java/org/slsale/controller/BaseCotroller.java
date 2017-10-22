package org.slsale.controller;

import org.apache.log4j.Logger;
import org.slsale.pojo.User;
import org.slsale.util.Constans;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dll on 2017/9/23.
 * 公共的Cotroller
 * @author dll
 */
public class BaseCotroller {
    /**
     * 日志对象
     */
    private Logger logger = Logger.getLogger(BaseCotroller.class);
    /**
     *  创建user对象  存放user
     */
    private User currentUser;

    public User getCurrentUser() {
            //获取当前的session用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //获取session 如果为空  不进行创建 直接返回
            HttpSession session = request.getSession(false);
            if (session != null) {
                currentUser = (User) session.getAttribute(Constans.SESSION_USER);
            }else {
                currentUser=null;
            }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * 国际化日期
     *
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
            @Override
            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd").format((Date)getValue());
            }
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
                } catch (ParseException e) {
                    e.printStackTrace();
                    setValue(null);
                }
            }
        });
    }
}
