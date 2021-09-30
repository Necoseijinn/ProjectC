<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/22
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.dao.ProductDAO" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.dao.CategoryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<%
    /***
     * 从listProducts.jsp的url跳转过来
     * url : JSPPages/CategoryProduct/editProduct.jsp?product.id=product.getId()
     * 可见后面带有传参
     * request解析了参数并添加到了AttribuMap中
     * 所以此处可以直接获取product.id参数对应的值
     */
    String pid = request.getParameter("product.id");
    ProductDAO productDAO = new ProductDAO();
    Product product = new Product();
    product.setId(Integer.parseInt(pid));
    product = productDAO.getProduct(product);
    request.setAttribute("product", product);
%>
<body>
<form action="${pageContext.request.contextPath}/updateProduct" method="post">
    <table>
        <tr>
            <td>Product Category</td>
            <td>
                <select name="category.id">
                    <%
                        List<Category> categories = new CategoryDAO().listCategories();
                        for (Category category : categories) {
                    %>
                    <option value="<%=category.getId()%>"><%out.println(category.getName());%></option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td>Product Name</td>
            <td>
                <input type="text" name="product.name" value="${product.name}">
            </td>
        </tr>
        <tr>
            <td>Product Price</td>
            <td>
                <input type="text" name="product.price" value="<%=product.getPrice()%>">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update Product"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="product.id" value="<%out.print(product.getId());%>"/>
</form>
</body>
</html>
