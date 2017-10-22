/*点击添加的时候 显示模态窗口*/
$(".addrole").click(function (e) {
    e.preventDefault();
    $("#formtip").html();
    $("#addRoleDiv").modal("show");
});

$("#addRoleCancel").click(function () {
    $("#roleCode").val("");
    $("#roleName").val("");
});
$("#addRoleClose").click(function () {
    $("#roleCode").val("");
    $("#roleName").val("");
});

/*提交*/
$("#addRoleBtn").click(function () {
    if($("#roleCode").val()==null || $("#roleCode").val()=="" || $("#roleName").val()==null || $("#roleName").val()=="" ){
        alert("角色名和角色编码不能为空");
    }else {
        var roleCode=$("#roleCode").val();
        var roleName=$("#roleName").val();
        $.post("/backend/roleAdd.html",{"roleCode":roleCode,"roleName":roleName},function (data) {
            if(data=="success"){
                $("#formtip").html("新增成功，您还继续吗？");
                window.location.href="/backend/rolelist.html";
            }else if(data=="noData"){
                alert("没有数据需要处理");
            } else{
                alert("新增失败！！");
            }
        },'html');
    }
});
/*删除角色*/
$(".delrole").click(function () {
    var obj=$(this);
    var roleName=obj.attr("rolename");
    var roleId=obj.attr("roleid");
    if("您确定要删除<<"+roleName+">>吗？"){
        $.post("/backend/deleteRole.html",{"roleId":roleId},function (data) {
            if(data=="noData"){
                alert("没有数据需要处理！！！");
            }else if(data=="haveData"){
                alert("该角色下有用户，您不能删除！！！");
            }else if(data=="success"){
                alert("删除成功！！！");
                window.location.href="/backend/rolelist.html";
            }else{
                alert("删除失败！！！");
            }
        },'html');
    }
});

/*修改是否启用状态*/
$(".modifyIsStart").click(function () {
    var obj=$(this);
    var isStart=obj.attr("isstart");
    var roleId=obj.attr("roleid");
    $.post("/backend/isStart.html",{"isStart":isStart,"roleId":roleId},function (data) {
        if(data=="noData"){
            alert("没有数据需要处理");
        }else if(data=="success"){
            alert("修改成功");
            window.location.href="/backend/rolelist.html";
        }else {
            alert("修改失败");
        }
    },"html");
});
/*修改角色*/
$(".modifyrole").click(function(){
    var modify = $(this);
    var id= modify.attr("roleid");
    var oldCode= modify.attr("rolecode");
    var oldName= modify.attr("rolename");
    var roleCode = $.trim($("#roleCode"+id).val());
    var roleName = $.trim($("#roleName"+id).val());
    if(roleCode == "" || roleCode == null){
        alert("对不起，角色代码不能为空。");
    }else if(roleName == "" || roleName == null){
        alert("对不起，角色名称不能为空。");
    }else{
        var tip = "您确定要将原来的\n角色代码："+oldCode + "和角色名称："+oldName + "\n,修改为\n角色代码：" + roleCode + "和角色名称：" + roleName + "\n吗？";
        if(confirm(tip)){
            var role = new Object();
            role.id = id;
            role.roleCode = roleCode;
            role.roleName = roleName;
            $.ajax({
                url: '/backend/modifyRole.html',
                type: 'POST',
                data:{role:JSON.stringify(role)},
                dataType: 'html',
                timeout: 1000,
                error: function(){
                    alert("角色修改失败！请重试。");
                },
                success: function(result){
                    if(result != "" && "success" == result){
                        alert("角色修改成功 ^_^");
                    }else if("failed" == result){
                        alert("角色修改失败！请重试。");
                    }else if("nodata" == result){
                        alert("对不起，没有任何数据需要处理！请重试。");
                    }
                }
            });
        }
    }
});