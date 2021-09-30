<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/21
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>File Upload Demo</title>
</head>
<body>
<%--
    此处用out(JSP的隐式对象)来输出项目根路径
    注意要上传文件的话必须使用post方法以及要在form标签下添加enctype="multipart/form-data"
    由于form提交的是二进制数据,单纯靠过滤器的编码设置无法解决编码问题。所以必须要在对应Servlet处手动设置编码。
--%>
<form action="<%out.print(((HttpServletRequest)pageContext.getRequest()).getContextPath());%>/fileUploadDemo"
      method="post" enctype="multipart/form-data">
    <table border="1" cellspacing="0">
        <tr>
            <td>Name</td>
            <td>
                <input type="text" name="name"/>
            </td>
        </tr>
        <tr>
            <td>Photo</td>
            <td>
                <%-- 要上传文件必须把type指定为file --%>
                <input type="file" name="images"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
