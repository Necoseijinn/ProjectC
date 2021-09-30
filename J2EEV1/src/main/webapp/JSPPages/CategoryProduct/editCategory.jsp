<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/22
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<%
    String cid = request.getParameter("category.id");
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category();
    category.setId(Integer.parseInt(cid));
    category = categoryDAO.getCategory(category);
    request.setAttribute("category", category);
%>
<form action="${pageContext.request.contextPath}/updateCategory" method="post">
    <table border="1" cellspacing="0" align="center">
        <tr>
            <td>Category Name</td>
            <td>
                <input type="text" name="category.name" value="<%=category.getName()%>">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update Category"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="category.id" value="${category.id}">
</form>
</body>
</html>
