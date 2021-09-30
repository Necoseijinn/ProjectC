<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/14
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="listProducts">CheckOutProduct</a>
<br/>
<table border="1" cellspacing="0" align="center">
    <tr>
        <td>Category Id</td>
        <td>Category Name</td>
        <td>Edit Category</td>
        <td>Delete Category</td>
    </tr>
    <s:iterator value="categoryList" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td><a href="editCategory?category.id=${c.id}">Edit</a></td>
            <td><a href="deleteCategory?category.id=${c.id}">Delete</a></td>
        </tr>
    </s:iterator>
</table>
<br/>
<form action="addCategory" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Category Name</td>
            <td><input type="text" name="category.name"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add Category"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
