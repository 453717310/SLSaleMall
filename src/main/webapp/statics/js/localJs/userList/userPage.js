$(function () {
    var pageIndex1 =1;    //当前页码
    var pageSize="";     //页大小
    var pageCount="";    //总页数
    var totalCount="";   //总记录数
    var pageBtns="";    //分页按钮总个数
    var loginCode="";  //用户名称
    var referCode="";//推荐人
    var roleId="0";//用户角色
    var isStart="0";//是否启用

    var $seachUserList=$("#seachUserList");
    $seachUserList.on("click",function(){
        pageInit(pageIndex1);
    });
    //用户选择页码的按钮动态绑定click事件
    $("div.pages").on("click","a:[name=prev]",function(){
        if(pageIndex1 > 1)
            pageInit(Number(pageIndex1) - 1);
    });
    $("div.pages").on("click","a[name!=prev][name!=next]",function(){
        //获取分页按钮的文本
        var pageBtnTxt = $(this).html();
        pageInit(pageBtnTxt);
    });
    $("div.pages").on("click","a:[name=next]",function(){
        if(pageIndex1 < pageCount)
            pageInit(Number(pageIndex1) + 1);
    });
    //初始化分页数据
    pageInit(pageIndex1);
    //分页AJAX请求
    function pageInit(pageIndex) {
        loginCode=$("[name='loginCode']").val();
        referCode=$("[name='referCode']").val();
        roleId=$("#queryRoleName").val();
        isStart=$("[name='isStart']").val();
        var user=new Object();
        user.loginCode=loginCode;
        user.referCode=referCode;
        user.roleId=roleId;
        user.isStart=isStart;
        user.pageIndex=pageIndex;
        $.ajax({
            type: "GET",
            url: "/backend/userList",
            data: {
                user:JSON.stringify(user),
            },
            dataType: "json",
            success:function (data) {
                console.log(data);
                //清楚上一页的数据
                $("#tBodyUserList").html("");
                $(data).each(function(){

                    if(this.totalCount!=undefined) {
                        pageIndex1 = this.pageIndex;
                        pageSize = this.pageSize;
                        pageCount = this.pageCount;
                        totalCount = this.totalCount;
                    }else if(this.flag=="false"){
                        $("#tBodyUserList").html("<tr style='color: red'><td colspan='7' style='text-align: center'>没有找到相关用户信息</td></tr>");
                    }else{
                        $("#tBodyUserList").append(
                            "     <tr>"
                            +"    <td>"+this.loginCode+"</td>"
                            +"    <td class='center'>"+this.roleName+"</td>"
                            +"        <td class='center'>"+this.userTypeName+"</td>"
                            +"        <td class='center'>"+this.referCode+"</td>"
                            +"        <td class='center'>"+(this.isStart==1?"<input type='checkbox' checked aria-disabled='true' disabled='disabled'/>":
                                "<input type='checkbox' disabled='disabled'/>")
                            +"        </td>"
                            +"        <td class='center'>"
                            +"        <span class='label label-success'>"+format(this.createTime)+"</span>"
                            +"        </td>"
                            +"        <td class='center'>"
                            +"        <a class='btn btn-success viewUser' href='javascript:;' v_id='"+this.id+"' >"
                            +"        <i class='icon-zoom-in icon-white'></i>"
                            +"        查看"
                            +"        </a>"
                            +"        <a class='btn btn-info modifyUser' href='javascript:;' m_id='"+this.id+"'>"
                            +"        <i class='icon-edit icon-white'></i>"
                            +"        修改"
                            +"        </a>"
                            +"        <a class='btn btn-danger deleteUser' href='javascript:;' d_id='"+this.id+"' d_loginCode='"+this.loginCode+"' d_userType='"+this.userType+"'" +
                            "d_idCardPicPath='"+this.idCardPicPath+"' d_bankPicPath='"+this.bankPicPath+"' d_userTypeName='"+this.userTypeName+"' >"
                            +"        <i class='icon-trash icon-white'></i>"
                            +"        删除"
                            +"        </a>"
                            +"        </td>"
                            +"        </tr>"
                        );
                    }



                    //总页数不足5页
                    if(pageCount <= 5 && pageCount > 0) {
                        pageBtns = new Array(pageCount);
                        for(var i = 0; i < pageCount; i++) {
                            pageBtns[i] = i + 1;
                        }
                    }else{
                        //多余五页
                        pageBtns = new Array(5);
                        if(pageIndex1 >= 1 && pageIndex1 <= 3) {
                            for(var i = 0; i < 5; i++) {
                                pageBtns[i] = i + 1;
                            }
                        }else if(pageIndex1 >= pageCount - 2 &&
                            pageIndex1 <= pageCount)	{
                            pageBtns[0] = pageCount - 4;
                            pageBtns[1] = pageCount - 3;
                            pageBtns[2] = pageCount - 2;
                            pageBtns[3] = pageCount - 1;
                            pageBtns[4] = pageCount;
                        }else{
                            pageBtns[0] = pageIndex1 - 2;
                            pageBtns[1] = pageIndex1 - 1;
                            pageBtns[2]	= pageIndex1;
                            pageBtns[3] = pageIndex1 + 1;
                            pageBtns[4] = pageIndex1 + 2;
                        }
                    }
                });
                //获取分页功能区
                var $pageDiv = $(".pages");
                //清空上一次生成的分页组件j['
                $pageDiv.html("");
                //拼接分页功能部件
                var pageComponent = "";
                //如果当前页是第一页不显示上一页按钮
                pageComponent += "<ul>"
                if(Number(pageIndex1) > 1)
                    pageComponent += "<li><a href='javascript:' name='prev' class='p_pre'>上一页</a></li>";
                for(var i = 0; i < pageBtns.length; i++) {
                    pageComponent +="<li><a href='javascript:'"
                    //设置当前页按钮样式
                    if(pageBtns[i]==Number(pageIndex1))
                        pageComponent +=" class='active' style='color:red;' "
                    pageComponent +=">"+  pageBtns[i] + "</a></li>";
                }
                //如果当前页是最后一页不显示下一页按钮
                if(Number(pageIndex1) < Number(pageCount))
                    pageComponent += " <li> <a href='javascript:' name='next' class='p_pre'>下一页</a></li>";
                pageComponent+="</ul>"
                $pageDiv.append(pageComponent);
            }

        });
    }
});