<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/12
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isThreadSafe="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>

    <link rel="stylesheet" type="text/css" href="/static/local-commons/css/login.css"/>
    <script type="text/javascript" src="/static/local-commons/jquery-3.3.1.js"></script>
</head>

<body>
<div align="center" ><a href="${pageContext.request.contextPath}/page/login">登录</a></div>
<div id="login_frame">

    <p id="image_logo"><img src="/static/local-commons/fly.png"></p>

    <form method="post" <%--action="login.js"--%>>

        <p><label class="label_input">用户名</label><input type="text" id="username" class="text_field"/></p>
        <p><label class="label_input">密码</label><input type="password" id="password" class="text_field"/></p>
        <p><label class="label_input">电话</label><input type="text" id="tel" class="text_field"/></p>
        <label class="label_input">验证码</label><input type="text" id="submit" width="50px"/><input id="init"
                                                                                                  type="button"
                                                                                                  value="加载中"
                                                                                                  onclick="exchanges()"/>
        <div id="login_control">
            <input type="button" id="btn_login" value="注册" onclick="regist()"/>
            <a id="forget_pwd" href="forget_pwd.html">忘记密码？</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        //exchanges();
    })

    function exchanges(s) {

        var r = <%=(int) (Math.random()*1000)%>
            $("#init").val(r);
        return r;
    }

    function regist() {
        var result = check();
        if (result == false) {
            alert("验证码错误");
            exchanges(result);
            return;
        }

        var data = getInputValue();
        $.ajax({
            url: "/user/register",
            type: "post",
            data: data,
            dataType:"json",
            success: function (result) {
              /*  var json=evel("("+result+")");
                var message=result.desc;*/
                alert(result.desc);
            }
        })
    }

    function check() {
        var input = $("#submit").val();
        var init = $("#init").val();
        if (input == init) {
            return true;
        } else {
            return false;
        }
    }

    function getInputValue() {
        var data = {
            "userName": $("#username").val(),
            "passWord": $("#password").val(),
            "tel": $("#tel").val()
        }
        console.log(data);
        return data;
    }
</script>
</body>
</html>
