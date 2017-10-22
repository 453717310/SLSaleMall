function format(time){
    var datetime=new Date(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth()+1;//js从0开始取
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minutes = datetime.getMinutes();
    var second = datetime.getSeconds();

    if(month<10){
        month = "0" + month;
    }
    if(date<10){
        date = "0" + date;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minutes <10){
        minutes = "0" + minutes;
    }
    if(second <10){
        second = "0" + second ;
    }

    year = year.toString();
    year = year.substring(2);
    var time1 = year+"年"+month+"月"+date+"日";
    var time2 = hour+"时"+minutes+"分";//09年06月12日 17时18分
    var time={time1:time1,time2:time2}    //json格式
// alert(time);
    return time1;
}