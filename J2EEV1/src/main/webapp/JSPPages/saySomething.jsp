<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/16
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Say Something</title>
</head>
<body>
<%--
   此处由于saySomething.jsp是从index.jps利用超链接跳转来的所以默认路径为 项目名/JSPPages/saySomething.jsp
   所以当action不设定相对路径的时候,action跳转的url为项目名/JSPPages/saySomething
   因为此时路径是在项目名/JSPPages/下所以跳转的时候是在该目录下跳转的
   这时候就需要把action设成正确的相对路径,避免在当前目录下跳转,如下面代码所写的那样.
   简单来说,利用超链接进行jsp之间跳转,超链接的url所在目录就是跳转后jsp页面的当前目录.
   超链接跳转jsp画面由于是直接向服务器请求,所以服务器直接翻译jsp并返回该资源,此时url是jsp的所在地.
   而利用servlet去进行跳转的时候由于是服务端跳转由sevlet帮你返回所要画面,url是不会变化的.
 --%>
<%--
    注意此处不能写中文
    因为用的是get方法,即便后台在过滤器上做了编码设定处理,但由于get方法是在url上记载传输数据,无法识别中文,所以到后端.
--%>
<form action="${pageContext.request.contextPath}/saySomething" method="get">
    <table border="1" cellspacing="0">
        <tr>
            <td>Some Word</td>
            <td>
                <input type="text" name="word"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Send"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
