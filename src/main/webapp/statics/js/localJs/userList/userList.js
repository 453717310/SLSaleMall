/*邮箱验证的正则表达式*/
function checkEmail(str){
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(str == null || str == "" || reg.test(str))
        return true;
    else
        return false;
}
/*用户添加的js*/
$(".addUser").click(function (e) {
    /*清空验证列表*/
    $("#add_formtip").html("");
    e.preventDefault();
    /*显示增加的模态窗口*/
    $("#addUserDiv").modal("show");
    $("#uniform-a_fileInputID span:first").html('无文件');
    $("#uniform-a_fileInputBank span:first").html('无文件');
});
/*当点击取消的时候  清除表单域的值*/
$(".addUserCancel").click(function () {
    $("#add_formtip").html('');
    $("#selectUserType").html('<option value="" selected="selected">--请选择--</option>');
    $("#a_loginCode").val('');
    $("#selectRole").val('');
    $("#a_userName").val('');
    $("[name='sex']").val('');
    $("#selectCardType").val('');
    $("#a_idCard").val('');
    $("#a_birthday").val('');
    $("#a_mobile").val('');
    $("#a_email").val('');
    $("#a_postCode").val('');
    $("#a_bankName").val('');
    $("#a_bankAccount").val('');
    $("#a_accountHolder").val('');
    $("#isStart").val('');
    $("#a_userAddress").val('');
    $("#a_idPic").html('');
    $("#a_bankPic").html('');

});
/*通过选择角色，获取会员类型*/
$("#selectRole").change(function () {
    $("#selectUserType").empty();//清空上一次列表
    $("#selectUserType").html("<option value='' selected='selected'>--请选择--</option>");
    var s_roleId=$("#selectRole").val();
    if(s_roleId==2){
        $.post("/backend/loadUserTypeList.html",{"s_roleId":s_roleId},function (data) {
            if(data!=""){
                for(var i=0;i<data.length;i++){
                    $("#selectUserType").append("<option value='"+data[i].valueId+"' >"+data[i].valueName+"</option>");
                }
            }else {
                alert("用户加载失败！");
            }
        },'json');
    }
});

/*给角色名称赋值*/
$("#selectRole").change(function () {
    $("#selectRoleName").val($("#selectRole").find("option:selected").text());
});

/*给会员名称赋值*/
$("#selectUserType").change(function () {
    $("#selectUserTypeName").val($("#selectUserType").find("option:selected").text());
});

/*给证件类型名称赋值*/
$("#selectCardType").change(function () {
    $("#selectCardTypeName").val($("#selectCardType").find("option:selected").text());
});

/*判断用户名是否存在*/
$("#a_loginCode").blur(function () {
    var a_loginCode=$("#a_loginCode").val();
    if(a_loginCode!=null){
        $.post("/backend/loginCodeIsExist.html",{"a_loginCode":a_loginCode,"a_id":-1},function (data) {
            if(data=="repeat"){
                $("#add_formtip").css("color","red");
                $("#add_formtip").html("<li>对不起，该用户名已存在！</li>");
                $("#add_formtip").attr("key","1");
            }else if(data=="failed"){
                alert("对不起，操作超时！");
            }else if(data=="only"){
                $("#add_formtip").css("color","green");
                $("#add_formtip").html("<li>该用户名可以使用！</li>");
                $("#add_formtip").attr("key","0");
            }
        },'html');
    }
});
/*修改 start*/
/*通过选择角色，获取会员类型*/
$("#updateRole").change(function () {
    $("#updateUserType").empty();//清空上一次列表
    $("#updateUserType").html("<option value='' selected='selected'>--请选择--</option>");
    var m_roleId=$("#updateRole").val();
    if(m_roleId==2){
        $.post("/backend/loadUserTypeList.html",{"s_roleId":m_roleId},function (data) {
            if(data!=""){
                for(var i=0;i<data.length;i++){
                    $("#updateUserType").append("<option value='"+data[i].valueId+"' >"+data[i].valueName+"</option>");
                }
            }else {
                alert("用户加载失败！");
            }
        },'json');
    }
});

/*给角色名称赋值*/
$("#updateRole").change(function () {
    $("#updateRoleName").val($("#updateRole").find("option:selected").text());
});

/*给会员名称赋值*/
$("#updateUserType").change(function () {
    $("#updateUserTypeName").val($("#updateUserType").find("option:selected").text());
});

/*给证件类型名称赋值*/
$("#updateCardType").change(function () {
    $("#updateCardTypeName").val($("#updateCardType").find("option:selected").text());
});

/*判断用户名是否存在*/
$("#m_loginCode").blur(function () {
    var m_loginCode=$("#m_loginCode").val();
    var m_id=$("#m_id").val();
    if(m_loginCode!=null){
        $.post("/backend/loginCodeIsExist.html",{"a_loginCode":m_loginCode,"a_id":m_id},function (data) {
            if(data=="repeat"){
                $("#modify_formtip").css("color","red");
                $("#modify_formtip").html("<li>对不起，该用户名已存在！</li>");
                $("#modify_formtip").attr("key","1");
            }else if(data=="failed"){
                alert("对不起，操作超时！");
            }else if(data=="only"){
                $("#modify_formtip").css("color","green");
                $("#modify_formtip").html("<li>该用户名可以使用！</li>");
                $("#modify_formtip").attr("key","0");
            }
        },'html');
    }
});
/*修改 end*/
/*增加验证邮箱*/
$("#a_email").blur(function () {
    var flag=checkEmail($("#a_email").val());
    if(flag==false){
        $("#add_formtip").css("color","red");
        $("#add_formtip").html("<li>邮箱格式不正确</li>");
        $("#add_formtip").attr("key","1");
    }else{
        $("#add_formtip").html("");
        $("#add_formtip").attr("key","0");
    }

});
/*增加验证邮箱*/
$("#m_email").blur(function () {
    var flag=checkEmail($("#m_email").val());
    if(flag==false){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").html("<li>邮箱格式不正确</li>");
        $("#modify_formtip").attr("key","1");
    }else{
        $("#modify_formtip").html("");
        $("#modify_formtip").attr("key","0");
    }

});
/*添加用户验证*/
function addUserFunction() {
    $("#add_formtip").html("");
    var result=true;
    /*验证角色*/
    if($("#selectRole").val()==""){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，用户角色不能为空！</li>");
        result=false;
    }
    /*验证用户名*/
    if($.trim($("#a_loginCode").val())=="" || $("#a_loginCode").val()==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，用户名不能为空！</li>");
        result=false;
    }else{
        if($("#add_formtip").attr("key")=="1"){
            $("#add_formtip").css("color","red");
            $("#add_formtip").append("<li>对不起，该用户名已存在！</li>");
            result=false;
        }
    }
    /*验证真实姓名*/
    if($.trim($("#a_userName").val())=="" || $("#a_userName").val()==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，真实姓名不能为空！</li>");
        result=false;
    }
    /*证件类型验证*/
    if($("#selectCardType").val()==""){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，证件类型不能为空！</li>");
        result=false;
    }
    /*证件号码的验证*/
    if($.trim($("#a_idCard").val())=="" || $("#a_idCard")==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，证件号码不能为空！</li>");
        result=false;
    }else if($("#a_idCard").val().length<6){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，证件号码的长度不能小于6位！</li>");
        result=false;
    }
    /*验证手机号*/
    if($.trim($("#a_mobile").val())=="" || $("#a_mobile")==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，手机号码不能为空！</li>");
    }
    /*验证邮箱*/
    if($.trim($("#a_email").val())=="" || $("#a_email").val()==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，邮箱不能为空！</li>");
        result=false;
    }else{
        if($("#add_formtip").attr("key")=="1"){
            $("#add_formtip").css("color","red");
            $("#add_formtip").append("<li>对不起，邮箱格式不正确！</li>");
            result=false;
        }
    }
    /*验证开户行*/
    if($.trim($("#a_bankName").val())=="" || $("#a_bankName")==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，开户行不能为空！</li>");
        result=false;
    }
    /*验证开户卡号*/
    if($.trim($("#a_bankAccount").val())=="" || $("#a_bankAccount")==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，开户卡号不能为空！</li>");
        result=false;
    }
    /*验证开户人*/
    if($.trim($("#a_accountHolder").val())=="" || $("#a_accountHolder")==null){
        $("#add_formtip").css("color","red");
        $("#add_formtip").append("<li>对不起，开户人不能为空！</li>");
        result=false;
    }
    if(result==true)
        alert("新增成功！！！！！！");
        return result;

}
/*修改用户验证*/
function modifyUserFunction() {
    $("#modify_formtip").html("");
    var result=true;
    /*验证角色*/
    if($("#updateRole").val()==""){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，用户角色不能为空！</li>");
        result=false;
    }
    /*验证用户名*/
    if($.trim($("#m_loginCode").val())=="" || $("#m_loginCode").val()==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，用户名不能为空！</li>");
        result=false;
    }else{
        if($("#modify_formtip").attr("key")=="1"){
            $("#modify_formtip").css("color","red");
            $("#modify_formtip").append("<li>对不起，该用户名已存在！</li>");
            result=false;
        }
    }
    /*验证真实姓名*/
    if($.trim($("#m_userName").val())=="" || $("#m_userName").val()==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，真实姓名不能为空！</li>");
        result=false;
    }
    /*证件类型验证*/
    if($("#updateCardType").val()==""){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，证件类型不能为空！</li>");
        result=false;
    }
    /*证件号码的验证*/
    if($.trim($("#m_idCard").val())=="" || $("#m_idCard")==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，证件号码不能为空！</li>");
        result=false;
    }else if($("#m_idCard").val().length<6){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，证件号码的长度不能小于6位！</li>");
        result=false;
    }
    /*验证手机号*/
    if($.trim($("#m_mobile").val())=="" || $("#m_mobile")==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，手机号码不能为空！</li>");
    }
    /*验证邮箱*/
    if($.trim($("#m_email").val())=="" || $("#m_email").val()==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，邮箱不能为空！</li>");
        result=false;
    }else{
        if($("#modify_formtip").attr("key")=="1"){
            $("#modify_formtip").css("color","red");
            $("#modify_formtip").append("<li>对不起，邮箱格式不正确！</li>");
            result=false;
        }
    }
    /*验证开户行*/
    if($.trim($("#m_bankName").val())=="" || $("#m_bankName")==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，开户行不能为空！</li>");
        result=false;
    }
    /*验证开户卡号*/
    if($.trim($("#m_bankAccount").val())=="" || $("#m_bankAccount")==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，开户卡号不能为空！</li>");
        result=false;
    }
    /*验证开户人*/
    if($.trim($("#m_accountHolder").val())=="" || $("#m_accountHolder")==null){
        $("#modify_formtip").css("color","red");
        $("#modify_formtip").append("<li>对不起，开户人不能为空！</li>");
        result=false;
    }
    if(result==true)
        alert("修改成功！！！！！！");
    return result;

}
/*上传身份证文件*/
$("#a_uploadBtnID").click(function () {
    TajaxFileUpload('0','a_fileInputID','a_uploadBtnID','a_idPic','a_fileInputIDPath');
});
/*上传银行卡图片*/
$("#a_uploadBtnBank").click(function () {
    TajaxFileUpload('0','a_fileInputBank','a_uploadBtnBank','a_bankPic','a_fileInputBankPath');
});
/*修改上传身份证文件*/
$("#m_uploadBtnID").click(function () {
    TajaxFileUpload('0','a_fileInputID','a_uploadBtnID','a_idPic','a_fileInputIDPath');
});
/*修改上传银行卡图片*/
$("#m_uploadBtnBank").click(function () {
    TajaxFileUpload('0','a_fileInputBank','a_uploadBtnBank','a_bankPic','a_fileInputBankPath');
});
//'0','a_fileInputID','a_uploadBtnID','a_idPic','a_fileInputIDPath'
/*处理上传文件的ajax*/
function TajaxFileUpload(flag,t1,t2,t3,t4) {
    if($("#"+t1+"").val()=='' || $("#"+t1+"").val()==null){
        alert("请选择上传文件！");
    }else{
       $.ajaxFileUpload({
           url:'/backend/upload.html',//处理上传文件的服务端
           secureuri:false,//是否启用安全提交,默认为false
           fileElementId:t1,////文件选择框的id属性
           dataType:'json',
           success:function (data) {
               data = data.replace(/(^\s*)|(\s*$)/g, "");
               if(data=="1"){
                   alert("上传图片大小不得超过50k!");
                   $("#uniform-"+t1+" span:first").html('无文件');
                   $("input[name='"+t1+"']").change(function () {
                       //取出要上传的文件
                       var fn=$("input[name='"+t1+"']").val();
                       //判断浏览器（因为不同的浏览器，取出来内容不一样）
                       if($.browser.msie){
                           fn.substring(fn.lastIndexOf("\\")+1);//取出文件名称
                       }
                       $("#uniform-"+t1+" span:first").html(fn);
                   });
               }else if(data=="2"){
                   alert("上传图片格式不正确！");
                   $("#uniform-"+t1+" span:first").html('无文件');
                   $("input[name='"+t1+"']").change(function () {
                       //取出要上传的文件
                       var fn=$("input[name='"+t1+"']").val();
                       //判断浏览器（因为不同的浏览器，取出来内容不一样）
                       if($.browser.msie){
                           fn.substring(fn.lastIndexOf("\\")+1);//取出文件名称
                       }
                       $("#uniform-"+t1+" span:first").html(fn);
                   });
               }else {
                   //?m="+Math.random() 是为了解决浏览器缓存
                   $("#"+t3+"").append("<p><span onclick=\"delpic('"+flag+"','"+t3+"','"+t2+"',this,'"+data+"','"+t4+"','"+t1+"');\">x</span><img src=\""+data+"?m="+Math.random()+"\" /></p>");
                   $("#"+t2+"").hide();
                   $("#"+t4+"").val(data);
                   $("input[name='"+t1+"']").change(function(){
                       var fn = $("input[name='"+t1+"']").val();
                       if($.browser.msie){
                           fn = fn.substring(fn.lastIndexOf("\\")+1);
                       }
                       $("#uniform-"+t1+" span:first").html(fn);
                   });
               }
           },
           error:function () {
               alert("上传失败！");
           }
       });
    }
}
//'0','a_fileInputID','a_uploadBtnID','a_idPic','a_fileInputIDPath'
//删除图片
function delpic(id,closeSpan,uploadBtn,obj,picpath,picText,fileInputId) {
    $.post(
        "/backend/delpic.html",
        {'id':id,'picPath':picpath},
        function (data) {
            if("success"==data){
                alert("删除成功");
                $('#'+picText).val('');
                $("#uniform-"+fileInputId+" span:first").html('无文件');
               document.getElementById(closeSpan).removeChild(obj.parentElement);
                //$('#'+closeSpan).html('');
                //显示按钮
                $('#'+uploadBtn).show();
            }else if(data=="failed"){
                alert("删除失败！");
            }
        },
        'html'
    );
}

/*查看用户*/
$('#tBodyUserList').on('click','.viewUser',function (e) {
    var v_id=$(this).attr("v_id");
    $.ajax({
        type:"POST",
        url:"/backend/getUser.html",
        data:{"v_id":v_id},
        dataType:'json',
        success:function (data) {
            if("failed"==data){
                alert("操作超时！");
            }else if("noData"==data){
                alert("没有数据需要处理");
            }else{
                $("#v_id").val(data.id);
                $("#v_roleName").val(data.roleName);
                $("#v_userTypeName").val(data.userTypeName);
                $("#v_loginCode").val(data.loginCode);
                $("#v_userName").val(data.userName);
                $("#v_sex").val(data.sex);
                $("#v_cardTypeName").val(data.cardTypeName);
                $("#v_idCard").val(data.idCard);
                $("#v_birthday").val(format(data.birthday));
                $("#v_country").val(data.country);
                $("#v_mobile").val(data.mobile);
                $("#v_email").val(data.email);
                $("#v_postCode").val(data.postCode);
                $("#v_bankName").val(data.bankName);
                $("#v_bankAccount").val(data.bankAccount);
                $("#v_accountHolder").val(data.accountHolder);
                $("#v_referCode").val(data.referCode);
                $("#v_cDate").val(format(data.createTime));
                var isStart=data.isStart;
                if(isStart=="1"){
                    $("#v_isStart").append("<option value='1' selected='selected'>启用</option><option value='2'>不启用</option>");
                }else{
                    $("#v_isStart").append("<option value='1'>启用</option><option value='2' selected='selected'>不启用</option>");
                }
                $("#v_idPic").html('');
                $("#v_userAddress").html(data.userAddress);
                $("#v_fileInputIDPath").val(data.idCardPicPath);
                var idCardPicPath=data.idCardPicPath;
                if(idCardPicPath==null || idCardPicPath==""){
                    $("#v_idPic").append("暂无");
                }else {
                    $("#v_idPic").append("<p></p><img src='"+idCardPicPath+"?m="+Math.random()+"'</img></p>")
                }
                $("#v_fileInputBankPath").val(data.bankPicPath);
                $("#v_bankPic").html('');
                var bankPicPath=data.bankPicPath;
                if(bankPicPath==null || bankPicPath==""){
                    $("#v_bankPic").append("暂无");
                }else {
                    $("#v_bankPic").append("<p></p><img src='"+bankPicPath+"?m="+Math.random()+"'</img></p>")
                }
                e.preventDefault();
                $("#viewUserDiv").modal("show");
            }
        }
    });
});

/*查看需要取消清空*/
$('.viewUserCancel').click(function(e){
    $("#v_idPic").html('');
    $("#v_bankPic").html('');
    $("#v_isStart").html('');
});
/*修改需要取消清空*/
$('.modifyUserCancel').click(function(e){
    $("#v_idPic").html('');
    $("#v_bankPic").html('');
    $("#v_isStart").html('');
    $("#modify_formtip").html('');
});
/*修改用户进行数据回显*/
/*获取角色用户*/
function selectRole(roleName,roleId) {
    //异步查询角色，返回页面
    $.ajax({
        type:"POST",//请求类型
        url:"/roleName",//请求的url
        data:{},//请求的参数
        dataType:"json",
        success:function(data){
            $("#updateRole").html("");
            if(roleId==null || roleId=="")
                $("#updateRole").append("<option value=\"\" selected='selected'>请选择</option>");
            for(var i=0;i<data.length;i++){
                if(roleId==data[i].id)
                    $("#updateRole").append("<option  selected='selected'  value=\""+roleId+"\">"+roleName+"</option>");
                else
                    $("#updateRole").append("<option  value=\""+data[i].id+"\">"+data[i].roleName+"</option>");
            }
        },
    });
}
/*查看用户*/
$('#tBodyUserList').on('click','.modifyUser',function (e) {
    var m_id=$(this).attr("m_id");
    $.ajax({
        type:"POST",
        url:"/backend/getUser.html",
        data:{"v_id":m_id},
        dataType:'json',
        success:function (data) {
            if("failed"==data){
                alert("操作超时！");
            }else if("noData"==data){
                alert("没有数据需要处理");
            }else{
                $("#m_id").val(data.id);
                $("#updateRoleName").val(data.roleName);
                $("#updateRole").val(data.roleId);
                var m_roleId=data.roleId;
                var roleName=data.roleName;
                selectRole(roleName,m_roleId);
                $("#updateUserTypeName").val(data.userTypeName);
                $("#updateUserType").val(data.userType);
                if(m_roleId==2){
                    var userTypeName=data.userTypeName;
                    var userType=data.userType;
                    $("#updateUserType").empty();
                    if(userType==null || userType=="")
                        $("#updateUserType").append("<option value=\"\" selected='selected'>请选择</option>");
                    else {
                            $.post("/backend/loadUserTypeList.html",{"s_roleId":m_roleId},function (data) {
                                if(data!=""){
                                    for(var i=0;i<data.length;i++){
                                        if(userType==data[i].valueId)
                                            $("#updateUserType").append("<option value='"+userType+"' selected='selected' >"+userTypeName+"</option>");
                                        else
                                            $("#updateUserType").append("<option value='"+data[i].valueId+"' >"+data[i].valueName+"</option>");
                                    }
                                }else {
                                    alert("用户加载失败！");
                                }
                            },'json');
                    }
                }else {
                    $("#updateUserType").append("<option value=\"\" selected='selected'>请选择</option>");
                }
                $("#m_loginCode").val(data.loginCode);
                $("#m_userName").val(data.userName);
                $("#m_sex").val(data.sex);
                var sex=data.sex;
                $("#m_sex").html("");
                if(sex=="")
                    $("#m_sex").append("<option value=\"\" selected='selected'>请选择</option>" +
                        "<option value=\"男\" >男</option>" +
                        "<option value=\"女\" >女</option>");
                else if(sex=="男"){
                    $("#m_sex").append("<option value=\"男\" selected='selected'>男</option>" +
                        "<option value=\"女\" >女</option>");
                }else if (sex=="nv") {
                    $("#m_sex").append("<option value=\"男\" >男</option>" +
                        "<option value=\"女\" selected='selected'>女</option>");
                }
                $("#updateCardTypeName").val(data.cardTypeName);
                $("#updateCardType").val(data.cardType);
                var cardTypeName=data.cardTypeName;
                var cardType=data.cardType;
                /*获取证件类型*/
                $.post(
                    "/backend/dictionary.html",
                    {},
                    function (data) {
                        $("#updateCardType").html("");
                        if(cardType==null || cardType=="")
                            $("#updateCardType").append("<option value=\"\" selected='selected'>请选择</option>");
                        for(var i=0;i<data.length;i++){
                            if(cardType==data[i].valueId)
                                $("#updateCardType").append("<option  selected='selected'  value=\""+cardType+"\">"+cardTypeName+"</option>");
                            else
                                $("#updateCardType").append("<option  value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>");
                        }
                    },'json');
                $("#m_idCard").val(data.idCard);
                $("#m_birthday").val(format(data.birthday));
                $("#m_country").val(data.country);
                $("#m_mobile").val(data.mobile);
                $("#m_email").val(data.email);
                $("#m_postCode").val(data.postCode);
                $("#m_bankName").val(data.bankName);
                $("#m_bankAccount").val(data.bankAccount);
                $("#m_accountHolder").val(data.accountHolder);
                $("#m_referCode").val(data.referCode);
                $("#m_cDate").val(format(data.createTime));
                var isStart=data.isStart;
                if(isStart=="1"){
                    $("m_isStart").append("<option value='1' selected='selected'>启用</option><option value='2'>不启用</option>");
                }else{
                    $("#m_isStart").append("<option value='1'>启用</option><option value='2' selected='selected'>不启用</option>");
                }
                $("#m_userAddress").html(data.userAddress);
                $("#m_idPic").html('');
                $("#m_fileInputIDPath").val(data.idCardPicPath);
                var idCardPicPath=data.idCardPicPath;
                if(idCardPicPath==null || idCardPicPath==""){
                    $("#m_uploadBtnID").show();
                }else {
                    $("#m_idPic").append("<p><span onclick=\"delpic('"+data.id+"','m_idPic','m_uploadBtnID',this,'"+idCardPicPath+"','m_fileInputIDPath','m_fileInputID');\">x</span><img src=\""+idCardPicPath+"?m="+Math.random()+"\" /></p>");
                    $("#m_uploadBtnID").hide();
                }
                $("#m_bankPic").html('');
                $("#m_fileInputBankPath").val(data.bankPicPath);
                var bankPicPath=data.bankPicPath;
                if(bankPicPath==null || bankPicPath==""){
                    $("#m_uploadBtnBank").show();
                }else {
                    $("#m_bankPic").append("<p><span onclick=\"delpic('"+data.id+"','m_bankPic','m_uploadBtnBank',this,'"+bankPicPath+"','m_fileInputBankPath','m_fileInputBank');\">x</span><img src=\""+bankPicPath+"?m="+Math.random()+"\" /></p>");
                    $("#m_uploadBtnID").hide();
                }
                e.preventDefault();
                $("#modifyUserDiv").modal("show");
            }
        }
    });
});
/*删除用户*/
$('#tBodyUserList').on('click','.deleteUser',function () {
    /*获取当前对象*/
    var obj=$(this);
    var delId=obj.attr("d_id");
    var loginCode=obj.attr("d_loginCode");
    var userType=obj.attr("d_userType");
    var idCardPicPath=obj.attr("d_idCardPicPath");
    var bankPicPath=obj.attr("d_bankPicPath");
    var userTypeName=obj.attr("d_userTypeName");
    if(confirm("您确定要删除<<"+loginCode+">>吗？")){
        $.post("/backend/delUser.html",{"delId":delId,"idCardPicPath":idCardPicPath,"userType":userType,"bankPicPath":bankPicPath},function (data) {
            if(data=="noDelete"){
                alert("该用户类型为<<"+userTypeName+">>您不能删除！");
            }else if(data=="success"){
                alert("删除成功！！！");
                window.location.href="/backend/userList";
            }else {
                alert("删除失败");
            }
        },'html');
    }
});