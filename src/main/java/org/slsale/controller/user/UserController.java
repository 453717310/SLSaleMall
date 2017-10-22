package org.slsale.controller.user;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.slsale.controller.BaseCotroller;
import org.slsale.pojo.Dictionary;
import org.slsale.pojo.User;
import org.slsale.service.dictionary.DictionaryService;
import org.slsale.service.user.UserService;
import org.slsale.util.Constans;
import org.slsale.util.StringUtils;
import org.slsale.util.UtilPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dll on 2017/9/29.
 */
@Controller
public class UserController extends BaseCotroller{
    private Logger logger=Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private DictionaryService dictionaryService;

    /**
     * 修改密码
     * @param userJson
     * @return
     */
    @PostMapping(value = "/backend/passwordModify.html",produces = {"text/html; charset=utf-8"})
    @ResponseBody
    public Object modifyPassword(@RequestParam String userJson){
        //获取当前登录用户
        User currentUser = this.getCurrentUser();
        if (null==userJson || "".equals(userJson)) {
            return "noData";
        }
        //解析前台传来的数据
        JSONObject jsonObject = JSONObject.fromObject(userJson);
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        user.setId(currentUser.getId());
        user.setLoginCode(currentUser.getLoginCode());
        try {
            User loginUser = userService.getLoginUser(user);
            if (loginUser==null) {
                return "oldPwdWrong";
            }
            user.setPassword(user.getPassword2());
            user.setPassword2(null);
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    /**
     * 获取session里的菜单列表 转发到用户列表页
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/backend/userlist.html")
    public String userList(Model model,HttpSession session){
        Map<String,Object> map= (Map<String, Object>) session.getAttribute(Constans.SESSION_BASE_MODEL);
        model.addAllAttributes(map);
        return "/backend/userList";
    }

    /**
     * 获取用户列表  加分页
     * @param user
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/backend/userList",method = RequestMethod.GET)
    public void userList(String user, HttpServletResponse response) throws IOException {
        logger.debug(user);
        //把前台传过来的json串，转换对象
        JSONObject jsonObject = JSONObject.fromObject(user);
        User user1 = (User) JSONObject.toBean(jsonObject, User.class);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("loginCode", user1.getLoginCode());
        map.put("referCode",user1.getReferCode());
        map.put("roleId", user1.getRoleId());
        map.put("isStart", user1.getIsStart());
        String flag="{\"flag\":\"false\"}";
        UtilPage utilPage=new UtilPage();
        utilPage.setPageIndex(user1.getPageIndex());
        String s=null;
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        try {
            //获取总记录数
            int count = userService.getCount(map);
            logger.debug(count);
            utilPage.setTotalCount(count);
            map.put("pageIndex",(user1.getPageIndex()-1)*utilPage.getPageSize());
            map.put("pageSize",utilPage.getPageSize());
            //获取列表
            List<User> userList = userService.getUserList(map);
            if (userList!=null){
                s = com.alibaba.fastjson.JSON.toJSONString(userList);
                String s1 = com.alibaba.fastjson.JSON.toJSONString(utilPage);
                if (s.equals("[]")){
                    s=s.replace("]",s1+","+flag+"]");
                }else {
                    s=s.replace("]",","+s1+"]");

                }
                logger.debug(s);
                logger.debug(s1);
                writer.print(s);//发送到前台
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取证件类型列表
     * @return
     */
    @PostMapping(value = "/backend/dictionary.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object dictionaryTypeName(){
        List<Dictionary> dictionarieList=null;
        Dictionary dictionary=new Dictionary();
        dictionary.setTypeCode("CARD_TYPE");
        String s=null;
        try {
            dictionarieList=dictionaryService.getListByTypeCode(dictionary);
            JSONArray jsonArray = JSONArray.fromObject(dictionarieList);
             s = jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 获取会员类型列表
     * @param s_roleId
     * @return
     */
    @RequestMapping(value = "/backend/loadUserTypeList.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object getUserTypeList(@RequestParam(value="s_roleId",required = false) Integer s_roleId){
       logger.debug("getUserTypeList=======s_roleId==="+s_roleId);
        List<Dictionary> list=null;
        Dictionary dictionary=new Dictionary();
        dictionary.setTypeCode("USER_TYPE");
        String s =null;
        try {
            List<Dictionary> listByTypeCode = dictionaryService.getListByTypeCode(dictionary);
            JSONArray jsonArray = JSONArray.fromObject(listByTypeCode);
            s = jsonArray.toString();
            logger.debug("getUserTypeList==========="+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 添加的时候验证用户名是否存在
     * @param a_loginCode
     * @param a_id
     * @return
     */
    @RequestMapping(value = "/backend/loginCodeIsExist.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object loginCodeIsExist(@RequestParam(value = "a_loginCode",required = false) String a_loginCode,
                                    @RequestParam(value = "a_id",required = false) Integer a_id){
        String result="failed";
        logger.debug("loginCodeIsExist=========="+a_loginCode);
        logger.debug("loginCodeIsExist=========="+a_id);
        User user=new User();
        user.setLoginCode(a_loginCode);
        if(a_id!=-1) {
            user.setId(a_id);
        }
        try {
            if(userService.loginCodeIsExist(user)==0){
                result="only";
            }else {
                result="repeat";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     *新增用户
     * @param addUser
     * @param session
     * @return
     */
    @PostMapping("/backend/addUser.html")
    public ModelAndView addUser(@ModelAttribute("addUser") User addUser, HttpSession session){
        logger.debug("addUser=============="+addUser);
        //判断session用户的菜单列表是否为空
        if(session.getAttribute(Constans.SESSION_BASE_MODEL)==null) {
            return new ModelAndView("redirect:/");
        }
        try {
            String idCard = addUser.getIdCard();//密码是身份证后六位
            String s = idCard.substring(idCard.length() - 6);
            addUser.setPassword(s);
            addUser.setPassword2(s);
            addUser.setLastUpdateTime(new Date());
            addUser.setCreateTime(new Date());
            addUser.setReferId(this.getCurrentUser().getId());
            addUser.setReferCode(this.getCurrentUser().getLoginCode());
            userService.addUser(addUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/backend/userlist.html");
    }

    /**
     * 修改用户
     * @param modifyUser
     * @param session
     * @return
     */
    @PostMapping("/backend/modifyUser.html")
    public ModelAndView modifyUser(@ModelAttribute("modifyUser") User modifyUser, HttpSession session){
        logger.debug("modifyUser=============="+modifyUser);
        //判断session用户的菜单列表是否为空
        if(session.getAttribute(Constans.SESSION_BASE_MODEL)==null) {
            return new ModelAndView("redirect:/");
        }
        try {
            modifyUser.setLastUpdateTime(new Date());
            userService.updateUser(modifyUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/backend/userlist.html");
    }
    @RequestMapping(value="/backend/upload.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object uploadFile(@RequestParam(value="a_fileInputID",required = false) MultipartFile cardFile,
                             @RequestParam(value="a_fileInputBank",required = false) MultipartFile bankFile,
                             @RequestParam(value="m_fileInputID",required = false) MultipartFile mCardFile,
                             @RequestParam(value="m_fileInputBank",required = false) MultipartFile mBankFile,
                             @RequestParam(value="loginCode",required = false) String  loginCode,
                             HttpSession session, HttpServletRequest request){
        //根据服务器的操作系统，自动获取物理路径，自动适应各个操作系统的路径
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadFiles");
        logger.debug("path=================="+path);
        //查询字典表  获取文件上传的大小
        List<Dictionary> list=null;
        Dictionary dictionary=new Dictionary();
        dictionary.setTypeCode("PERSONALFILE_SIZE");
        try {
            list=dictionaryService.getListByTypeCode(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int fileSize=50000;// 文件大小 默认50k
        if(null!=list){
            if(list.size()==1){
                //获取数据字典表存放的上传文件的大小
                fileSize=Integer.valueOf(list.get(0).getValueName());
            }
        }
        //开始进行修改身份证上传的处理
        if (null!=mCardFile){
            //获取原始的文件名称
            String originalFilename = mCardFile.getOriginalFilename();
            //获取文件上传的后缀名
            String prefix = FilenameUtils.getExtension(originalFilename);
            logger.debug("prefix==============="+prefix);
            if (mCardFile.getSize()>fileSize){//上传的文件大小不得超过50k
                return "1";
            }else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                //给上传的文件统一命名：系统毫秒数+100w以内的随机数
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_mIDcard.jpg";
                File file=new File(path,fileName);
                if(!file.exists()){
                    file.mkdirs();//如果没有就创建
                }
                //保存文件
                try {
                    mCardFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String url = request.getContextPath()+"/statics/uploadFiles/"+fileName;
                logger.debug("request.getContextPath()====="+request.getContextPath());
                logger.debug("url===================="+url);
                return url;
            }else {
                return "2";
            }
        }
        //开始进行身份证上传的处理
        if (null!=cardFile){
            //获取原始的文件名称
            String originalFilename = cardFile.getOriginalFilename();
            //获取文件上传的后缀名
            String prefix = FilenameUtils.getExtension(originalFilename);
            logger.debug("prefix==============="+prefix);
            if (cardFile.getSize()>fileSize){//上传的文件大小不得超过50k
                return "1";
            }else if (prefix.equalsIgnoreCase("jpg")  || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                //给上传的文件统一命名：系统毫秒数+100w以内的随机数
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_IDcard.jpg";
                File file=new File(path,fileName);
                if(!file.exists()){
                    file.mkdirs();//如果没有就创建
                }
                //保存文件
                try {
                    cardFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String url = request.getContextPath()+"/statics/uploadFiles/"+fileName;
                logger.debug("request.getContextPath()====="+request.getContextPath());
                logger.debug("url===================="+url);
                return url;
            }else {
                return "2";
            }

        }
        //开始进行银行卡上传的处理
        if (null!=mBankFile){
            //获取原始的文件名称
            String originalFilename = mBankFile.getOriginalFilename();
            //获取文件上传的后缀名
            String prefix = FilenameUtils.getExtension(originalFilename);
            logger.debug("prefix==============="+prefix);
            if (mBankFile.getSize()>fileSize){//上传的文件大小不得超过50k
                return "1";
            }else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                //给上传的文件统一命名：系统毫秒数+100w以内的随机数
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_mBankCard.jpg";
                File file=new File(path,fileName);
                if(!file.exists()){
                    file.mkdirs();//如果没有就创建
                }
                //保存文件
                try {
                    mBankFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String url = request.getContextPath()+"/statics/uploadFiles/"+fileName;
                logger.debug("request.getContextPath()====="+request.getContextPath());
                logger.debug("url===================="+url);
                return url;
            }else {
                return "2";
            }

        }
        //开始进行修改银行卡上传的处理
        if (null!=bankFile){
            //获取原始的文件名称
            String originalFilename = bankFile.getOriginalFilename();
            //获取文件上传的后缀名
            String prefix = FilenameUtils.getExtension(originalFilename);
            logger.debug("prefix==============="+prefix);
            if (bankFile.getSize()>fileSize){//上传的文件大小不得超过50k
                return "1";
            }else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
                //给上传的文件统一命名：系统毫秒数+100w以内的随机数
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_bankCard.jpg";
                File file=new File(path,fileName);
                if(!file.exists()){
                    file.mkdirs();//如果没有就创建
                }
                //保存文件
                try {
                    bankFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String url = request.getContextPath()+"/statics/uploadFiles/"+fileName;
                logger.debug("request.getContextPath()====="+request.getContextPath());
                logger.debug("url===================="+url);
                return url;
            }else {
                return "2";
            }

        }

        return null;
    }

    /**
     * 删除图片
     * @param picPath
     * @param id
     * @param request
     * @return
     */
    @PostMapping(value="/backend/delpic.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object delPic(@RequestParam(value = "picPath",required = false) String picPath,
                         @RequestParam(value = "id",required = false) String id,
                         HttpServletRequest request){
        String result="failed";
        if(picPath==null || picPath.equals("")) {
            result = "success";
        }
        //传过来的网络路径，需要解析成物理路径
        String[] paths = picPath.split("/");
        String realPath = request.getSession().getServletContext().getRealPath(paths[1] + File.separator + paths[2] + File.separator + paths[3] + File.separator);
        File file = new File(realPath);

        if(file.exists()) {
            if (file.delete()) {
                if (id.equals("0")) {//添加用户时，删除上传的图片
                    result = "success";
                } else {//修改用户时，删除上传的图片
                    User _user = new User();
                    _user.setId(Integer.valueOf(id));
                    if (picPath.indexOf("_IDcard.jpg") != -1)
                        _user.setIdCardPicPath(picPath);
                    else if (picPath.indexOf("_bankCard.jpg") != -1)
                        _user.setBankPicPath(picPath);
                    try {
                        if (userService.delUserPic(_user) > 0) {
                            logger.debug("userService.delUserPic======== ");
                            result = "success";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return result;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 查看用户
     * @param id
     * @return
     */
    @PostMapping(value = "/backend/getUser.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object getUserById(@RequestParam(value = "v_id",required = false) String id){
        logger.debug("id============"+id);
        String result=null;
        if(StringUtils.isEmpty(id))
            return "noData";
        else{
            try {
                User user = userService.getUser(Integer.parseInt(id));
                //转换json
                result = JSON.toJSONString(user);
                logger.debug(result);
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
            return result;
        }

    }

    /**
     * 删除用户
     * @param delId
     * @param userType
     * @param idCardPicPath
     * @param bankPicPath
     * @param request
     * @return
     */
    @PostMapping(value = "/backend/delUser.html",produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public Object delUser(@RequestParam(value = "delId",required = false) String delId,
                          @RequestParam(value = "userType",required = false) String userType,
                          @RequestParam(value = "idCardPicPath",required = false) String idCardPicPath,
                          @RequestParam(value = "bankPicPath",required = false) String bankPicPath,
                          HttpServletRequest request){
        String result="failed";
        if (userType.equals("2") || userType.equals("3") || userType.equals("4")){
            return "noDelete";
        }else {
            User user=new User();
            user.setId(Integer.parseInt(delId));
            if(this.delPic(idCardPicPath,delId,request).equals("success") && this.delPic(bankPicPath,delId,request).equals("success")){
                try {
                    userService.deleteUser(user);
                    result="success";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
