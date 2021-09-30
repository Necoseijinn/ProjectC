<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/30
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show Current Online User Count</title>
</head>
<body>
<%--
    原理请参考ProjectC.Gold.Note.JWeb.J2EEV1.listener.SessionListener
--%>
<table border="1" cellspacing="1" align="center">
    <tr>
        <td align="center">当前在线人数</td>
    </tr>
    <tr>
        <td align="center">${onlineUserCount}</td>
    </tr>
    <tr>
        <td align="center">
            <a href="${pageContext.request.contextPath}/JSPPages/index.jsp">Back To Index</a>
        </td>
    </tr>
</table>
</body>
</html>
