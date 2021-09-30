<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/21
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Request Method Demo</title>
</head>
<body>
<%--
    此处没有使用EL表达式获取contextPath,而是用原生Java.
    <%=%>等同于<%out.print()%>
    out是JSP的隐式对象用于输出字符串到生成的页面中，注意跟输出到控制台的System.out.println不同(输出目的地不同)
--%>
<form action="<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%>/requstMethodDemo" method="post">
    <table border="1" cellspacing="0">
        <tr>
            <td colspan="2">Info</td>
        </tr>
        <tr>
            <td>Your Name</td>
            <td>
                <input type="text" name="name"/>
            </td>
        </tr>
        <tr>
            <td>Your hobits</td>
            <td>
                Reading<input type="checkbox" name="hobits" value="Reading"/>
                Coding<input type="checkbox" name="hobits" value="Coding"/>
                Gaming<input type="checkbox" name="hobits" value="Gaming"/>
                Cooking<input type="checkbox" name="hobits" value="Cooking"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">After commit,check the console.</td>
        </tr>
    </table>
</form>
</body>
</html>
