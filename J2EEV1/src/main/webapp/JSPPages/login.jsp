<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/16
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%--
    当登录失败时servlet发起 /项目名/JSPPages/login.jsp?fail=true 的重定向(301)
    需要通过 ${param.faile}来获取url中的参数(或者请求体中的参数)
--%>
<c:if test="${true==param.faile}">
    <h5 style="color:red">Login failed</h5>
    <h5 style="color:red">Wrong userName or passWord</h5>
</c:if>
<form action="${pageContext.request.contextPath}/servletLogin" method="post">

    <table border="1" cellspacing="0">
        <tr>
            <td>UserName</td>
            <td>
                <input type="text" name="userName"/>
            </td>
        </tr>
        <tr>
            <td>PassWord</td>
            <td>
                <input type="password" name="passWord"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login">
            </td>
        </tr>
    </table>
</form>
</body>
</html>