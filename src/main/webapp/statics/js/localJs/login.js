$("#loginButton").click(function () {
    var user=new Object();
    user.loginCode=$.trim($("#loginCode").val());//获得用户名
    user.password=$.trim($("#password").val());//获得密码
    user.isStart=1;//是否启用
    //非空验证
    if(null==user.loginCode || ""==user.loginCode){
        $("#loginCode").focus;
        $("#formValidate").css("color","red");
        $("#formValidate").html("对不起，用户名不能为空！！！！！！");
    }else if(null==user.password || ""==user.password){
        $("#password").focus;
        $("#formValidate").css("color","red");
        $("#formValidate").html("对不起，密码不能为空！！！！！！");
    }else{
        $("#formValidate").html("");//清空提示，进行ajax判断
        $.ajax({
            type:"POST",
            url:"/login.html",
            data:{user:JSON.stringify(user)},
            dataType:"html",
            success:function (data) {
                if(""!=data && "success"==data){
                    window.location.href="/main.html";
                }else if(data=="failed"){
                    $("#formValidate").css("color","red");
                    $("#formValidate").html("对不起，登录失败！！！！！！");
                    $("#loginCode").val('');
                    $("#password").val('');
                }else if (data=="notExistLoginCode"){
                    $("#formValidate").css("color","red");
                    $("#formValidate").html("对不起，用户不存在，请重新输入！！！！！！");
                }else if(data=="passwordError"){
                    $("#formValidate").css("color","red");
                    $("#formValidate").html("对不起，密码错误，请重新输入！！！！！！");
                }else if(data=="noData"){
                    $("#formValidate").css("color","red");
                    $("#formValidate").html("对不起，您没有任何数据需要处理，请重试！！！！！！");
                }
            }
        });
    }

});