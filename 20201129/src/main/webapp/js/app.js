$(function () {//页面加载完成之后执行function代码

    $("#login_form").submit(function () {
        //ajax自己发送请求
        $.ajax({
            url:"../login",
            type:"post",//请求的方法
            //contentType："",//请求的数据类型，请求头Content-type，默认为表单提交方式
            //dataType:"",//响应的数据类型不使用默认为表单提交,json需要手动指定
            data:$("#login_form").serialize(),//请求的数据，使用序列化表单的数据
            dataType:"json",
            success:function (r) {//响应体json字符串，会解析为方法参数
                if(r.success){
                    //前端页面url直接跳转某个路径
                    window.location.href="../jsp/articleList.jsp";
                }else{
                    alert("错误码："+r.code+"\n消息体:"+r.message);

                }
            }


        })

        return false;
     //统一不执行默认的表单提交
    })//jquery,使用$("#id"),通过元素id获取某个页面元素

})