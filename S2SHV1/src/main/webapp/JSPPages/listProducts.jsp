<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/14
  Time: 15:26
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
<a href="listCategories">CheckOutCategory</a>
<br/>
<table border="1" cellspacing="0" align="center">
    <tr>
        <td>Product Category</td>
        <td>Product Name</td>
        <td>Product Price</td>
        <td>Edit Product</td>
        <td>Delete Price</td>
    </tr>
    <s:iterator value="productList" var="p">
        <tr>
            <td>${p.category.name}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td><a href="editProduct?product.id=${p.id}">Edit</a></td>
            <td><a href="deleteProduct?product.id=${p.id}">Delete</a></td>
        </tr>
    </s:iterator>
</table>
<br/>
<form action="addProduct" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Product Category</td>
            <td>
                <select name="category.id">
                    <s:iterator value="categoryList" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </s:iterator>
                </select>
            </td>

        </tr>
        <tr>
            <td>Product Name</td>
            <td>
                <input type="text" name="product.name"/>
            </td>
        </tr>
        <tr>
            <td>Product Price</td>
            <td>
                <input type="text" name="product.price"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add Product"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
