<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/27
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"
         errorPage="CatchDemo.jsp" %>
<html>
<head>
    <title>Try Demo</title>
</head>
<body>
<%
    /***
     * 此处会发生异常,从而跳转到CatchDemo.jsp
     * 需要在上面设置 errorPage="CatchDemo.jsp"
     */
    float a = 1 / 0;
%>
</body>
</html>
