<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/14
  Time: 15:50
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
<form action="updateProduct" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Product Category</td>
            <td>
                <select name="category.id">
                    <s:iterator value="categoryList" var="c">
                        <!--
                        此处用到了JSTL标签
                        JSTL标签的set用于设置变量
                        -->
                        <c:set var="categoryP" value="${product.category}"/>
                        <c:set var="categoryC" value="${c}"/>
                        <c:if test="${product.category.id==c.id}">
                            <option value="${c.id}" selected>${c.name}</option>
                        </c:if>
                        <c:if test="${!(categoryP.id==categoryC.id)}">
                            <option value="${c.id}">${c.name}</option>
                        </c:if>
                    </s:iterator>
                </select>
            </td>
        </tr>
        <tr>
            <td>Product Name</td>
            <td>
                <input type="text" name="product.name" value="${product.name}"/>
            </td>
        </tr>
        <tr>
            <td>Product Price</td>
            <td>
                <input type="text" name="product.price" value="${product.price}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update Product"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="listProducts">Go Back</a>
            </td>
        </tr>
    </table>
    <input type="hidden" name="product.id" value="${product.id}">
</form>
</body>
</html>
