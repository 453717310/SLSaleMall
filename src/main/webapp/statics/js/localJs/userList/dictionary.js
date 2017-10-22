$(function () {
    /*获取证件类型*/
    $.post(
        "/backend/dictionary.html",
        {},
        function (data) {
            $("#selectCardType").html("");
            var results="<option value=\"0\">请选择</option>";
            for(var i=0;i<data.length;i++){
                results+="<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
            }
            $("#selectCardType").html(results);
    },'json');
});