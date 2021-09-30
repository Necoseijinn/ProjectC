<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/16
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<h1>Home Page</h1>
<h3>Guaiding Page or Servlet</h3>
<ol>
    <%--
        测试利用超链接实现jsp之间的跳转,直接访问jsp文件跟通过servlet服务端跳转的区别在于二者最终的所在地址是不一样的.
        直接访问jsp文件,相当于你直接跑到jsp文件所在目录跟服务器说,我要看那个jsp文件,服务器就把jsp文件解析成servlet再返回响应数据.
        而通过servlet服务端跳转的情况是,你跑去服务器找一个服务器的手下,告诉那个手下你要看什么,那个手下就处理好相关数据,一并带到jsp文件并解析成servlet再返回响应数据.
        一个是跑到了jsp文件目录下去找jsp,一个是跑到servlet的地方.所以二者最终停留的地点不同.
        如果有第二次请求的情况,就需要考虑清楚目前停留点,再去设定请求路径或者action路径.
        记住,跳转的时候要看当前路径,还有一点如果你的超链接路径以"/"开头的话,这代表你要从服务器根路径(注意不是项目根路径)开始,
        比如
            <a href="/servletone">ServletTestOne</a>这代表localhost:8080/servletone
            <a href="${pageContext.request.contextPath} /servletone">ServletTestOne</a>这代表localhost:8080/ProjectName/servletone
    --%>
    <li><a href="${pageContext.request.contextPath}/servletone">ServletTestOne</a></li>
    <li><a href="${pageContext.request.contextPath}/gotologin">Login Pages</a></li>
    <li><a href="${pageContext.request.contextPath}/JSPPages/saySomething.jsp">Say Something</a></li>

    <%-- 测试中文传输 --%>
    <li><a href="${pageContext.request.contextPath}/JSPPages/inputEncodeSetDemo.jsp">Input Encode</a></li>

    <%-- 测试HttpServletRequest的常见方法,获取参数以及请求头的方法. --%>
    <li><a href="${pageContext.request.contextPath}/JSPPages/requestMethodDemo.jsp">Request Method Demo</a></li>

    <%-- 测试HttpServletResponse的常见方法,例如:设置响应内容,设置响应格式,设置响应编码,301或者302客户端跳转,设置不使用缓存等. --%>
    <li><a href="${pageContext.request.contextPath}/responseMethodDemo">Response Method Demo</a></li>

    <%-- 文件下载测试 start --%>
    <%--
        简单来说,想要实现下载的功能,依赖于浏览器如何解析从服务器返回来的响应信息.
        情景1:浏览器不能识别响应数据的类型(一般通过设置resp.setContentType("text/xxxx");来实现,写一个浏览器不认识的类型就行,但此方法不适用所有浏览器,chrome会先尝试去解析响应数据,然后在浏览器打开响应数据而不是下载.)
             情景1关于chrome浏览器无法下载而是直接在浏览器解析并显示响应数据的解决办法,要么多添加一句情景3的语句,要么按情景2的方式处理.
        情景2:通过设置<a>标签的download属性来让浏览器明白,在<a>标签向服务器发出请求后得到的响应数据要做为下载文件来处理,而不是去解析并显示在浏览器上.
        情景3:通过后端servlet处理响应的时候加上一句resp.setHeader("Content-Disposition","attachment;filename=MAVEN_IMPORT.txt");来实现,跟情景2的效果一样区别在于可以在servlet处理.

        情景1有局限性不推荐使用,情景2跟3可以稳定实现下载效果.
        在除了下载之外不需要其他业务处理的情况下用情景2,在除了下载之外需要别的业务处理(servlet)的时候用情景3.
        补充:情景2跟3可以混在一起使用.
        总的来说,其实浏览器就是处理请求和响应,是要把服务器发来的响应数据包装成文件去下载还是浏览器自己解析就看怎么设置去告诉服务器了.
    --%>
    <%-- 情景2 --%>
    <li><a href="${pageContext.request.contextPath}/ForDownload/MAVEN_IMPORT.txt" download="MAVEN_IMPORT.txt">Download
        Test V1</a></li>
    <%-- 情景3 --%>
    <li><a href="${pageContext.request.contextPath}/downloadTest">Download Test V2</a></li>
    <%-- 文件下载测试 end --%>

    <%-- 演示文件上传 --%>
    <li><a href="<%=request.getContextPath()%>/JSPPages/FileUploadDemo.jsp">File Upload Demo</a></li>

    <%-- 演示增删改查 --%>
    <li><a href="<%=request.getContextPath()%>/listProducts">List Products</a></li>
    <li><a href="<%=request.getContextPath()%>/listCategories">List Categories</a></li>

    <%-- 演示利用Json和Ajax传输和获取数据 --%>
    <li><a href="${pageContext.request.contextPath}/JSPPages/CategoryProduct/JSONTest/jsonAddCategory.jsp">JSON Add
        Category</a></li>
    <li><a href="<%out.print(request.getContextPath());%>/requestDataServlet">Get Category By JSON And Ajax</a></li>

    <%-- 用于演示和说明JSP相关的知识 --%>
    <li><a href="${pageContext.request.contextPath}/JSPPages/JSPDemoFile.jsp">JSP Demo</a></li>
    <li><a href="${pageContext.request.contextPath}/JSPPages/TryDemo.jsp">TryCatchException Demo</a></li>
    <li><a href="${pageContext.request.contextPath}/JSPPages/JSTLDemo.jsp">JSTL Demo</a></li>
    <li><a href="${pageContext.request.contextPath}/JSPPages/ELDemo.jsp?name=AlienCat">EL Demo</a></li>

    <%-- 利用监听器来实现统计当前在线用户的总数 --%>
    <li><a href="${pageContext.request.contextPath}/JSPPages/CurrentOlineUserCount.jsp">Check Current Online User
        Count</a></li>

    <%-- 利用删除Session容器中的userName变量来达到LogOut效果 --%>
    <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>

</ol>
</body>
</html>
