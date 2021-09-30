<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/22
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category" %>
<html>
<head>
    <title>List Products</title>
</head>
<body>
<table border="1" cellspacing="0" align="center">
    <tr>
        <td>Product Id</td>
        <td>Product Category</td>
        <td>Product Name</td>
        <td>Product Price</td>
        <td>Edit Product</td>
        <td>Delete Prodcut</td>
    </tr>
    <%
        List<Product> products = (List) request.getAttribute("products");
        if (products != null) {
            for (Product product : products) {
                out.println("<tr>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("<td>" + product.getCategory().getName() + "</td>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("<td>" + product.getPrice() + "</td>");
                out.println("<td><a href=\"JSPPages/CategoryProduct/editProduct.jsp?product.id=" + product.getId() + "\">Edit</a></td>");
                out.println("<td><a href=\"deleteProduct?product.id=" + product.getId() + "\">Delete</a></td>");
                out.println("</tr>");
            }
        }
    %>

    <%--
        判断是否有元素要显示或者元素是否只够显示一页
        是的话就不需要这些页面跳转标签了
    --%>
    <c:if test="${!(pageHelper.noTag)}">
        <tr>
            <td colspan="6" align="center">

                    <%-- 判断是否有[首页]标签 --%>
                <c:if test="${pageHelper.homeTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listProducts?start=0">[首页]</a>
                </c:if>

                    <%-- 判断是否有[上一页]标签 --%>
                <c:if test="${pageHelper.preTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listProducts?start=${pageHelper.pre}">[上一页]</a>
                </c:if>

                    <%-- 判断是否有页面数值标签 --%>
                <c:if test="${pageHelper.showPageMap!=null}">
                    <c:forEach items="${pageHelper.showPageMap}" var="pageEntry">
                        <%-- 超链接传参传入起始元素的角标 --%>
                        <a href="listProducts?start=${pageEntry.value}">[${pageEntry.key}]</a>
                    </c:forEach>
                </c:if>

                    <%-- 判断是否有[下一页]标签 --%>
                <c:if test="${pageHelper.nextTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listProducts?start=${pageHelper.next}">[下一页]</a>
                </c:if>

                    <%-- 判断是否有[尾页]标签 --%>
                <c:if test="${pageHelper.lastTag}">
                    <%-- 超链接传参传入起始元素的角标 --%>
                    <a href="listProducts?start=${pageHelper.last}">[尾页]</a>
                </c:if>
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="3">
            <a href="JSPPages/index.jsp">Back To Index</a>
        </td>
        <td colspan="3">
            <a href="listCategories">Check Categories</a>
        </td>
    </tr>
</table>
<br>
<form action="addProduct" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Category</td>
            <td>
                <select name="category.id">
                    <%
                        List<Category> categories = (List) request.getAttribute("categories");
                        if (categories != null) {
                            for (Category category : categories) {
                                out.println("<option value=\"" + category.getId() + "\">" + category.getName() + "</option>");
                            }
                        }
                    %>
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
                <input type="submit" value="Add Product">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
