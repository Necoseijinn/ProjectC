<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/17
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Input Encode Demo</title>
</head>
<body>
<%--
    此处跟saySomethind.jsp不同的是用的post方法去提交数据
--%>
<form action="${pageContext.request.contextPath}/sendInput" method="post">
    <table border="1" cellspacing="0">
        <tr>
            <td>My Input</td>
            <td>
                <input type="text" name="word"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Send">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
