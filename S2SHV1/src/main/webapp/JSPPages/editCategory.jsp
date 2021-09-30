<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/14
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="updateCategory" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Category Name</td>
            <td>
                <input type="text" name="category.name" value="${category.name}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update Category"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="listCategories">Go Back</a>
            </td>
        </tr>
    </table>
    <input type="hidden" name="category.id" value="${category.id}"/>
</form>
</body>
</html>
