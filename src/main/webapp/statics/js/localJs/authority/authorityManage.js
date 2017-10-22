//获取1级  2级菜单列表
$(".roleNameAuthority").click(function () {
    var authority=$(this);
    var roleId=authority.attr("roleid");
    $('#selectrole').html("您当前的角色为："+authority.attr("rolename"));
    //给隐藏域赋值
    $("#roleidhide").val(roleId);
    $.ajax({
        type:"POST",
        url:"/backend/functionList.html",
        data:{},
        dataType:'json',
        success:function (data) {
            if(data=="noData"){
                alert("您没有数据需要处理！！！");
            }else{
                var listr = "";
                for(var i=0;i<data.length;i++){
                    listr += "<li>";
                    listr += "<ul id=\"subfuncul"+data[i].mainFunction.id+"\" class=\"subfuncul\">";
                    listr += "<li  class=\"functiontitle\" ><input id='functiontitle"+data[i].mainFunction.id+"' onchange='mainFunctionSelectChange(this,"+data[i].mainFunction.id+");' funcid=\""+data[i].mainFunction.id+"\" type='checkbox' />"+data[i].mainFunction.functionName+"</li>";
                    for(var j=0;j<data[i].subFunctionList.length;j++){
                        listr += "<li><input onchange='subFunctionSelectChange(this,"+data[i].mainFunction.id+");' funcid=\""+data[i].subFunctionList[j].id+"\" type='checkbox' /> "+data[i].subFunctionList[j].functionName+"</li>";
                    }
                    listr += "</ul></li>";
                }
                $("#functionList").html(listr);
                //获取默认的功能列表
                $("#functionList :checkbox").each(function () {
                    var checkBox=$(this);
                    $.ajax({
                        url: '/backend/getAuthorityDefault.html',
                        type: 'POST',
                        data:{rid:$("#roleidhide").val(),fid:$(this).attr("funcid")},
                        dataType: 'html',
                        success: function(result){
                            if(result == "success"){
                                //alert("ok");
                                checkBox.attr("checked", true);
                            }else{
                                //alert("no");
                                checkBox.attr("checked", false);
                            }
                        }
                    });
                });
            }
        }
    });
});
function subFunctionSelectChange(obj,id){
    if(obj.checked){
        $("#functiontitle"+id).attr("checked", true);
    }
}

function mainFunctionSelectChange(obj,id){
    if(obj.checked){
        $("#subfuncul"+id+" :checkbox").attr("checked", true);
    }else{
        $("#subfuncul"+id+" :checkbox").attr("checked", false);
    }

    //alert($(this) +　id);
}
$("#selectAll").click(function () {//全选
    $("#functionList :checkbox").attr("checked", true);
});

$("#unSelect").click(function () {//全不选
    $("#functionList :checkbox").attr("checked", false);
});

$("#reverse").click(function () {//反选
    $("#functionList :checkbox").each(function () {
        $(this).attr("checked", !$(this).attr("checked"));
    });
});