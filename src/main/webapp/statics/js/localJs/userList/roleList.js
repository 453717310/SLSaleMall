$(function () {
    //异步查询角色，返回页面
    $.ajax({
        type:"POST",//请求类型
        url:"/roleName",//请求的url
        data:{},//请求的参数
        dataType:"json",
        success:function(data){
            $("[name='roleId']").html("");
            var results="<option value=\"\" selected='selected'>请选择</option>";
            for(var i=0;i<data.length;i++){
                results+="<option value=\""+data[i].id+"\">"+data[i].roleName+"</option>";
            }
            $("[name='roleId']").html(results);
        },
    });
});