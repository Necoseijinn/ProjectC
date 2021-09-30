<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/24
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add Category By JSON And Ajax</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/JSPPages/JSIMPORT/jquery-3.6.0.min.js"></script>
</head>
<body>
<table border="1" cellspacing="0">
    <tr>
        <td>Category Name</td>
        <td>
            <input type="text" id="name"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" id="sender" value="Add Category">
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%=request.getContextPath()%>/JSPPages/index.jsp">Back To Index</a>
        </td>
        <td>
            <a href="<%=request.getContextPath()%>/listCategories">List Categories</a>
        </td>
    </tr>
</table>
<script>
    $('#sender').click(function () {
        var name = document.getElementById('name').value;
        var category = {"name": name};
        var url = "${pageContext.request.contextPath}/JSONAddCategory";

        /***
         * JQuery的Ajax写法(POST方式)
         */
        $.post(
            //请求的url
            url,
            /***
             * 以键值对的形式传输JSON字符串
             * Key:"data"
             * Value:JSON.stringify(category)
             */
            {"data": JSON.stringify(category)},
            //回调函数
            function (resultData) {
                window.location.href = "${pageContext.request.contextPath}/listCategories";
            }
        )
    })
</script>
</body>
</html>
