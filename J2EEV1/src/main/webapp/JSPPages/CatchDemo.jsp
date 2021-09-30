<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/27
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"
         isErrorPage="true" %>
<html>
<head>
    <title>Catch Demo</title>
</head>
<body>
<%="An Exception has occured,Exception Info:"%>
<%--
    exception是JSP的隐式对象
    当别的画面指定了errorPage为此画面并且在画面中发生了异常时
    会把该异常抛给此异常画面，并把异常对象赋值给该页面的exception对象，里面包含了异常信息可以直接打印。

    原理也很简单
    其实就是JSP翻译成的Servlet里面本身就被一个try/catch包裹着,只不过通过设定了异常处理画面让原本出异常的画面把异常抛给了此画面。
--%>
<%=exception%>
</body>
</html>
