<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/22
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Categories</title>
</head>
<body>
<table border="1" cellspacing="0" align="center">
    <tr>
        <td>Category Id</td>
        <td>Category Name</td>
        <td>Edit Category</td>
        <td>Delete Category</td>
    </tr>

    <c:forEach items="${categories}" var="category" varStatus="str">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td><a href="JSPPages/CategoryProduct/editCategory.jsp?category.id=${category.id}">Edit</a></td>
            <td><a href="deleteCategory?category.id=${category.id}">Delete</a></td>
        </tr>
    </c:forEach>

    <%--
        判断是否有元素要显示或者元素是否只够显示一页
        是的话就不需要这些页面跳转标签了
    --%>
    <c:if test="${!(pageHelper.noTag)}">
        <tr>
            <td colspan="4" align="center">

                    <%-- 判断是否有[首页]标签 --%>
                <c:if test="${pageHelper.homeTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listCategories?start=0">[首页]</a>
                </c:if>

                    <%-- 判断是否有[上一页]标签 --%>
                <c:if test="${pageHelper.preTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listCategories?start=${pageHelper.pre}">[上一页]</a>
                </c:if>

                    <%-- 判断是否有页面数值标签 --%>
                <c:if test="${pageHelper.showPageMap!=null}">
                    <c:forEach items="${pageHelper.showPageMap}" var="pageEntry">
                        <%-- 超链接传参传入起始元素的角标 --%>
                        <a href="listCategories?start=${pageEntry.value}">[${pageEntry.key}]</a>
                    </c:forEach>
                </c:if>

                    <%-- 判断是否有[下一页]标签 --%>
                <c:if test="${pageHelper.nextTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listCategories?start=${pageHelper.next}">[下一页]</a>
                </c:if>

                    <%-- 判断是否有[尾页]标签 --%>
                <c:if test="${pageHelper.lastTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listCategories?start=${pageHelper.last}">[尾页]</a>
                </c:if>
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="2">
            <a href="JSPPages/index.jsp">Back To Index</a>
        </td>
        <td colspan="2">
            <a href="listProducts">Check Products</a>
        </td>
    </tr>
</table>
<br>
<form action="addCategory" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Category Name</td>
            <td>
                <input type="text" name="category.name"/>
            </td>
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
