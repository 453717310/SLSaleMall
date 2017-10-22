<%--
  Created by IntelliJ IDEA.
  User: dll
  Date: 2017/9/30
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入头部-->
<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>
<!--用户列表  start-->
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">后台管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">用户管理</a>
        </li>
    </ul>
</div>
<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> 用户列表</h2>
            <div class="box-icon">
                <span class="icon32 icon-color icon-add custom-setting addUser"/>
            </div>
        </div>
        <div class="box-content">
            <div class="searcharea">
                用户名称:
                <input type="text" name="loginCode" value="" />
                推荐人：
                <input type="text" name="referCode" value="" />
                角色：
                <select name="roleId" id="queryRoleName" style="width:100px;">
                    <option value="0">请选择</option>
                </select>
                是否启用：
                <select name="isStart" style="width:100px;">
                    <option value="0">请选择</option>
                    <option value="1" >启用</option>
                    <option value="2">未启用</option>　　
                </select>
                <button type="submit" id="seachUserList" class="btn btn-primary"><i class="icon-search icon-white"></i> 查询 </button>
            </div>
        </div>
        <div>
            <table class="table table-striped table-bordered ">
                <thead>
                    <tr>
                        <th>用户名</th>
                        <th>角色</th>
                        <th>会员类型</th>
                        <th>推荐人</th>
                        <th>启用状态(启用/未启用)</th>
                        <th>注册时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="tBodyUserList">
                    
                </tbody>
            </table>
            <div class="pagination pagination-centered pages">

            </div>
        </div>
    </div><!--/span-->
</div><!--/row-->
<!--用户列表  end-->
<!--新增用户  start-->
<div class="modal hide fade" id="addUserDiv">
    <form action="/backend/addUser.html" enctype="multipart/form-data" method="post" onsubmit="return addUserFunction();">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>添加用户信息</h3>
        </div>
        <div class="modal-body">
            <!--提示信息-->
            <ul id="add_formtip"></ul>
            <ul class="topul">
                <li>
                    <label>角色：</label>
                    <input id="selectRoleName" type="hidden" name="roleName" value=""/>
                    <select id="selectRole" name="roleId" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>会员类型：</label>
                    <input id="selectUserTypeName" type="hidden" name="userTypeName" value=""/>
                    <select id="selectUserType" name="userType" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                </li>
                <li>
                    <label>用户名：</label>
                    <input type="text" id="a_loginCode" name="loginCode" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>姓名：</label>
                    <input type="text" id="a_userName" name="userName"/>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>性别：</label>
                    <select name="sex" style="width: 100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </li>
                <li>
                    <label>证件类型：</label>
                    <input id="selectCardTypeName" type="hidden" name="cardTypeName" value=""/>
                    <select id="selectCardType" name="cardType" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>证件号码：</label>
                    <input type="text" id="a_idCard" name="idCard" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>生日：</label>
                    <input class="Wdate" id="a_birthday" size="15" name="birthday" readonly="readonly"  type="text" onClick="WdatePicker();"/>
                </li>
                <li>
                    <label>收货国家：</label><input type="text" name="country" value="中国"/>
                </li>
                <li>
                    <label>联系电话：</label><input type="text" id="a_mobile" name="mobile" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>Email：</label><input type="text" id="a_email" name="email"/>
                </li>
                <li>
                    <label>邮政编码：</label><input type="text" id="a_postCode" name="postCode" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </li>
                <li>
                    <label>开户行：</label><input type="text" id="a_bankName" name="bankName"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>开户卡号：</label><input type="text" id="a_bankAccount" name="bankAccount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>开户人：</label><input type="text" id="a_accountHolder" name="accountHolder"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>推荐人：</label><input type="text" name="referCode" value="${user.loginCode}" readonly="readonly"/>
                </li>
                <li>
                    <label>注册时间：</label>
                    <input type="text" id="a_cDate"  value="" readonly="readonly"/>
                </li>
                <li>
                    <label>是否启用：</label>
                    <select name="isStart" style="width:100px;">
                        <option value="1" selected="selected">启用</option>
                        <option value="2">不启用</option>
                    </select> <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li class="lastli">
                    <label>收货地址：</label><textarea id="a_userAddress" name="userAddress"></textarea>
                </li>

            </ul>
            <div class="clear"></div>
            <ul class="downul">
                <li>
                    <label>上传身份证图片：</label>
                    <input type="hidden" id="a_fileInputIDPath" name="idCardPicPath" value=""/>
                    <input id="a_fileInputID" name="a_fileInputID" type="file"/>
                    <input type="button" id="a_uploadBtnID" value="上传"/>
                    <p><span style="color:red;font-weight: bold;">*注：1、正反面.2、大小不得超过50k.3、图片格式：jpg、png、jpeg、pneg</span></p>
                    <div id="a_idPic"></div>
                </li>
            </ul>
            <ul class="downul">
                <li>
                    <label>上传银行卡图片：</label>
                    <input type="hidden" id="a_fileInputBankPath" name="bankPicPath" value=""/>
                    <input id="a_fileInputBank" name="a_fileInputBank" type="file"/>
                    <input type="button" id="a_uploadBtnBank" value="上传"/>
                    <p><span style="color:red;font-weight: bold;">*注：1、大小不得超过50k.2、图片格式：jpg、png、jpeg、pneg</span></p>
                    <div id="a_bankPic"></div>
                </li>

            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn addUserCancel" data-dismiss="modal">取消</a>
            <input type="submit" class="btn btn-primary" value="保存"/>
        </div>
    </form>
</div>
<!--新增用户  end-->

<!--查看用户  start-->
<div class="modal hide fade" id="viewUserDiv">
        <div class="modal-header">
            <button type="button" class="close viewUserCancel" data-dismiss="modal">×</button>
            <h3>查看用户信息</h3>
        </div>
        <div class="modal-body">
            <input type="hidden" id="v_id" value=""/>
            <ul class="topul">
                <li>
                    <label>角色：</label>
                    <input type="text" id="v_roleName" value=""/>
                </li>
                <li>
                    <label>会员类型：</label>
                    <input type="text" id="v_userTypeName" value=""/>
                </li>
                <li>
                    <label>用户名：</label>
                    <input type="text" id="v_loginCode"  value=""/>
                </li>
                <li>
                    <label>姓名：</label>
                    <input type="text" id="v_userName"value=""/>
                </li>
                <li>
                    <label>性别：</label>
                    <input type="text" id="v_sex" value=""/>
                </li>
                <li>
                    <label>证件类型：</label>
                    <input type="text" id="v_cardTypeName" value=""/>
                </li>
                <li>
                    <label>证件号码：</label>
                    <input type="text" id="v_idCard"value=""/>
                </li>
                <li>
                    <label>生日：</label>
                    <input type="text"  id="v_birthday" value=""/>
                </li>
                <li>
                    <label>收货国家：</label><input type="text" id="v_country" value="中国"/>
                </li>
                <li>
                    <label>联系电话：</label><input type="text" id="v_mobile" value=""/>
                </li>
                <li>
                    <label>Email：</label><input type="text" id="v_email" value=""/>
                </li>
                <li>
                    <label>邮政编码：</label><input type="text" id="v_postCode" value=""/>
                </li>
                <li>
                    <label>开户行：</label><input type="text" id="v_bankName" value=""/>
                </li>
                <li>
                    <label>开户卡号：</label><input type="text" id="v_bankAccount" value=""/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>开户人：</label><input type="text" id="v_accountHolder" value=""/>
                </li>
                <li>
                    <label>推荐人：</label><input type="text" id="v_referCode" value=""/>
                </li>
                <li>
                    <label>注册时间：</label>
                    <input type="text" id="v_cDate"  value=""/>
                </li>
                <li>
                    <label>是否启用：</label>
                    <select name="isStart" id="v_isStart" style="width:100px;" disabled="disabled">
                    </select>
                </li>
                <li class="lastli">
                    <label>收货地址：</label><textarea id="v_userAddress"></textarea>
                </li>

            </ul>
            <div class="clear"></div>
            <ul class="downul">
                <li>
                    <label>上传身份证图片：</label>
                    <input type="hidden" id="v_fileInputIDPath" name="idCardPicPath" value=""/>
                    <div id="v_idPic"></div>
                </li>
            </ul>
            <ul class="downul">
                <li>
                    <label>上传银行卡图片：</label>
                    <input type="hidden" id="v_fileInputBankPath" name="bankPicPath" value=""/>
                    <div id="v_bankPic"></div>
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn viewUserCancel" data-dismiss="modal">关闭</a>
        </div>
</div>
<!--查看用户  end-->
<!--修改用户  start-->
<div class="modal hide fade" id="modifyUserDiv">
    <form action="/backend/modifyUser.html" enctype="multipart/form-data" method="post" onsubmit="return modifyUserFunction();">
        <div class="modal-header">
            <button type="button" class="close modifyUserCancel" data-dismiss="modal">×</button>
            <h3>添加用户信息</h3>
        </div>
        <div class="modal-body">
            <input type="hidden" id="m_id" name="id" value=""/>
            <!--提示信息-->
            <ul id="modify_formtip"></ul>
            <ul class="topul">
                <li>
                    <label>角色：</label>
                    <input id="updateRoleName" type="hidden" name="roleName" value=""/>
                    <select id="updateRole" name="roleId" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>会员类型：</label>
                    <input id="updateUserTypeName" type="hidden" name="userTypeName" value=""/>
                    <select id="updateUserType" name="userType" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                </li>
                <li>
                    <label>用户名：</label>
                    <input type="text" id="m_loginCode" name="loginCode" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>姓名：</label>
                    <input type="text" id="m_userName" name="userName"/>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>性别：</label>
                    <select id="m_sex" name="sex" style="width: 100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </li>
                <li>
                    <label>证件类型：</label>
                    <input id="updateCardTypeName" type="hidden" name="cardTypeName" value=""/>
                    <select id="updateCardType" name="cardType" style="width: 100px">
                        <option value="" selected="selected">--请选择--</option>
                    </select>
                    <span style="color: red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>证件号码：</label>
                    <input type="text" id="m_idCard" name="idCard" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>生日：</label>
                    <input class="Wdate" id="m_birthday" size="15" name="birthday" readonly="readonly"  type="text" onClick="WdatePicker();"/>
                </li>
                <li>
                    <label>收货国家：</label><input type="text" name="country" value="中国"/>
                </li>
                <li>
                    <label>联系电话：</label><input type="text" id="m_mobile" name="mobile" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>Email：</label><input type="text" id="m_email" name="email"/>
                </li>
                <li>
                    <label>邮政编码：</label><input type="text" id="m_postCode" name="postCode" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </li>
                <li>
                    <label>开户行：</label><input type="text" id="m_bankName" name="bankName"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>开户卡号：</label><input type="text" id="m_bankAccount" name="bankAccount" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>开户人：</label><input type="text" id="m_accountHolder" name="accountHolder"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li>
                    <label>推荐人：</label><input type="text" name="referCode" value="${user.loginCode}" readonly="readonly"/>
                </li>
                <li>
                    <label>注册时间：</label>
                    <input type="text" id="m_createTime"  value="" readonly="readonly"/>
                </li>
                <li>
                    <label>是否启用：</label>
                    <select name="isStart" style="width:100px;">
                        <option value="1" selected="selected">启用</option>
                        <option value="2">不启用</option>
                    </select> <span style="color:red;font-weight: bold;">*</span>
                </li>
                <li class="lastli">
                    <label>收货地址：</label><textarea id="m_userAddress" name="userAddress"></textarea>
                </li>

            </ul>
            <div class="clear"></div>
            <ul class="downul">
                <li>
                    <label>上传身份证图片：</label>
                    <input type="hidden" id="m_fileInputIDPath" name="idCardPicPath" value=""/>
                    <input id="m_fileInputID" name="a_fileInputID" type="file"/>
                    <input type="button" id="m_uploadBtnID" value="上传"/>
                    <p><span style="color:red;font-weight: bold;">*注：1、正反面.2、大小不得超过50k.3、图片格式：jpg、png、jpeg、pneg</span></p>
                    <div id="m_idPic"></div>
                </li>
            </ul>
            <ul class="downul">
                <li>
                    <label>上传银行卡图片：</label>
                    <input type="hidden" id="m_fileInputBankPath" name="bankPicPath" value=""/>
                    <input id="m_fileInputBank" name="a_fileInputBank" type="file"/>
                    <input type="button" id="m_uploadBtnBank" value="上传"/>
                    <p><span style="color:red;font-weight: bold;">*注：1、大小不得超过50k.2、图片格式：jpg、png、jpeg、pneg</span></p>
                    <div id="m_bankPic"></div>
                </li>

            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn modifyUserCancel" data-dismiss="modal">取消</a>
            <input type="submit" class="btn btn-primary" value="保存"/>
        </div>
    </form>
</div>
<!--  修改用户 end-->

<script src="/statics/js/jquery-1.8.3.min.js"></script>
<script src="/statics/js/localJs/userList/roleList.js"></script>
<script type="text/javascript" src="/statics/js/localJs/userList/userPage.js"></script>
<script type="text/javascript" src="/statics/js/localJs/userList/userList.js"></script>
<script src="/statics/js/localJs/userList/dictionary.js"></script>
<!--引入底部-->
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"/>



