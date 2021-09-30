<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/27
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%--
    以下属于JSP的<%@page指令
    contentType="text/html; charset=UTF-8"
        相当于response.setContentType("text/html; charset=UTF-8"); 通知浏览器以UTF-8进行中文解码
    pageEncoding="UTF-8"
        如果jsp文件中出现了中文，这些中文使用UTF-8进行编码
    import="java.util.*
        导入其他类，如果导入多个类，彼此用,逗号隔开，像这样 import="java.util.*,java.sql.*"
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>JSP Demo</title>
</head>
<body>
<%--
    JSP文件本质也是servlet内含request和response对象以及一些jsp特有的隐式对象。

    JSP页面元素
        1. 静态内容
            就是html,css,javascript等内容
        2. 指令
            以 <%@ 开始 %> 结尾，比如<%@page import="java.util.*"%>
        3. 表达式 <%=%>
            用于输出一段html
        4. Scriptlet
            在<%%> 之间，可以写任何java 代码
        5. 声明
            在<%!%> 之间可以声明字段或者方法。但是不建议这么做。
        6. 动作
            <jsp:include page="Filename" > 在jsp页面中包含另一个页面。在包含的章节有详细的讲解
        7. 注释 < %-- --% >
            不同于 html的注释 <!-- --> 通过jsp的注释，浏览器也看不到相应的代码，相当于在servlet中注释掉了

    <%%>
        可以在此标志范围内写java代码，导包用<%@ page import="" %>进行导包。
        jsp里正常的html语句其实翻译成servlet都是一堆写在 out.write("HTML CONTENTS") 中的语句
        <%%>中的java语句是直接挪到翻译后的servlet中的。
        比如：
            <%if(true){%>
            <h1>HELLO WORLD</h1>
            <%}else{%>
            <h1>GOODBYE</h1>
            <%}%>
        实际上翻译后的servlet中是下面的样子
            if(true){
                out.write("<h1>HELLO WORLD</h1>\r\n");
            }else{
                out.write("<h1>GOODBYE</h1>\r\n");
            }

    <%=%>
        相当于<%out.print()%>，out是JSP内置的隐式对象，实质是response.getWriter()。
        注意，此处是输出到输出流中，不是输出到控制台。
        也可以写成<%response.getWriter.println()%>

    <%@include%>指令
        使用<%@include%>指令例子：
            <%@include file="FooterDemo.jsp" %>
        此用法相当于把 FooterDemo.jsp 的内容转移到当前jsp中，也就是说当前JSP页面所生成的java文件中包含了 FooterDemo.jsp 的内容
            JSPDemoFile.jsp ---
                               --- >JSPDemoFile.java
            FooterDemo.jsp  ---
    补充：<jsp:include page="FooterDemo.jsp" />
        此种用法不同于<%@include%>指令的地方是，此写法相当于调用了两个servlet文件
            JSPDemoFile.jsp --- > JSPDemoFile.java

            FooterDemo.jsp  --- > FooterDemo.java
        <%@include%>指令由于是把 FooterDemo.jsp 的内容复制到了 JSPDemoFile.jsp 中，所以可以直接调用 JSPDemoFile.jsp 里面设置的变量
        而< jsp:include page="FooterDemo.jsp" />相当于是在servlet中调用了或者说类似于服务器跳转那样访问了 FooterDemo.jsp 独立于 JSPDemoFile.jsp
        所以如果需要用到 JSPDemoFile.jsp 中的变量，就需要让 JSPDemoFile.jsp 通过传参的形式传给 FooterDemo.jsp
        原理也很简单就是利用类似url传参的形式以键值对传给 FooterDemo.jsp 让其解析
        说白了就是 FooterDemo.jsp?paramKey=paramValue
        然后 FooterDemo.jsp 再通过request.getParameter(paramKey)的形式取出paramValue

    JSP的服务端跳转和客户端跳转
        其实跟servlet同理因为本质就是servlet
        服务端跳转
            301
                <%resp.setStatus(301);%>
                <%resp.setHeader("cache-control","no-cache;no-store");%>
                <%resp.setHeader("location", uri);%>
            302
                <%resp.sendRedirect(uri);%>
        客户端跳转
            <%req.getRequestDispatcher(uri).forward(req, resp);%>
            或者用jsp特有的写法：
            <jsp:forward page="uri"/>

    Cookie
        Cookie是一种浏览器和服务器交互数据的方式。
        Cookie是由服务器端创建，但是不会保存在服务器。
        创建好之后，发送给浏览器。浏览器保存在用户本地。
        下一次访问网站的时候，就会把该Cookie发送给服务器。
        由于jsp网页是默认创建Cookie和Session所以即便不手动设置，也会自动生成。
        Cookie使用例子：
            Cookie c = new Cookie("name", "Gareen");
                创建了一个cookie,名字是"name" 值是"Gareen"
            c.setMaxAge(24 * 60 * 60);
                表示这个cookie可以保留一天，如果是0，表示浏览器一关闭就销毁
            c.setPath("/");
                Path表示访问服务器的所有应用都会提交这个cookie到服务端，如果其值是 /a, 那么就表示仅仅访问 /a 路径的时候才会提交 cookie
            response.addCookie(c);
                通过response把这个cookie保存在浏览器端
        Cookie种类
            Cookie分为内存Cookie和硬盘Cookie
                内存Cookie
                    保存在浏览器内存中，浏览器窗口关闭而消失。
                    设置成内存Cooki的方法：
                        c.setMaxAge(0);
                硬盘Cookie
                    保存在硬盘中，不会随着浏览器窗口关闭而消失。
                    设置成硬盘Cookie的方法：
                        c.setMaxAge(24 * 60 * 60);
                不设置的话默认为硬盘Cookie保存时间为30分钟。


    Session
        Session其实就是一个用于访问一个Map的Key
        服务器在启动时会创建一个Map，里面保存着每个访问用户的信息，但要获取这些信息就需要用到Session里的ID
        Session的ID保存在Cookie中，每次用户带着Cookie访问服务器时，服务器就可以从Cookie中获取Session的ID从而获取Map中的用户信息。
        Session可以在web.xml中设置有效期，详情可以参考web.xml
        Session由于是依赖于Cookie存在的，所以一旦Cookie消失Session也跟着消失。
        想要设置Session为关闭浏览器窗口Session就消失的话只要把Cookie设置成内存Cookie就行
            c.setMaxAge(0);

    作用域
        JSP有4个作用域，分别是
            1，pageContext 当前页面
                JSP有一个隐式对象pageContext
                这个隐式对象有着几个跟request一样的方法
                    pageContext.setAttribute(key,value)
                    pageContext.getAttribute(key)
                由于pageContext是JSP的隐式对象，所以只能在该JSP页面中使用。
            2，requestContext 一次请求
                其实就是request对象
                使用JSP的隐式对象request：
                    request.setAttribute(key,value);
                    request.getAttribute(key);
                既然是request对象，那毋庸置疑范围肯定就是本次request从开始到结束。
                在JSP中也可以用以下写法设置
                    pageContext.setAttribute(key,value,pageContext.REQUEST_SCOPE);
                    pageContext.getAttribute(key,pageContext.REQUEST_SCOPE);
            3，sessionContext 当前会话
                其实就是Session对象，或者说SessionID作为Key对应SessionMap中保存的用户信息。
                使用JSP的隐式对象session：
                    session.setAttribute(key,value);
                    session.getAttribute(key);
                在JSP中也可以用以下写法设置
                    pageContext.setAttribute(key,value,pageContext.SESSION_SCOPE);
                    pageContext.getAttribute(key,pageContext.SESSION_SCOPE);
            4，applicationContext 全局，所有用户共享
                相当于在JSP中使用application对象，application对象是ServletContext接口的实例。
                也可以通过 request.getServletContext()来获取。(所以 application == request.getServletContext() 会返回true)
                application映射的就是web应用本身。
                使用JSP的隐式对象application：
                    application.setAttribute(key,value);
                    application.getAttribute(key);
                在JSP中也可以用以下写法设置
                    pageContext.setAttribute(key,value,pageContext.APPLICATION_SCOPE);
                    pageContext.getAttribute(key,pageContext.APPLICATION_SCOPE);

    JSP的隐式对象
        JSP一共有9个隐式对象，分别是：
            1，request
            2，response
            3，out
            4，pageContext
            5，session
            6，application
            7，page
                page 对象即表示当前对象
                JSP 会被编译为一个Servlet类，运行的时候是一个Servlet实例。
                page即代表this
            8，config
                config可以获取一些在web.xml中初始化的参数。
                本质是ServletConfig。
                ServletConfig用于在初始化一个servlet的时候就默认设置的一组键值对。
                在JSP中使用config比较复杂，需要如下几个步骤
                    1. 在web.xml中进行配置，注意必须设置<jsp-file>
                    2. 在JSP文件中通过config.getInitParameter(Key) 获取参数
                下面有例子，请结合web.xml思考。
            9，exception
                exception 对象只有当前页面的<%@page 指令设置为isErrorPage="true"的时候才可以使用。
                同时，在其他页面也需要设置 <%@page 指令 errorPage="" 来指定一个专门处理异常的页面。
                详情参考TryDemo.jsp和CatchDemo.jsp

--%>
<h1>A JSPDemo</h1>
<%--
    只有通过 项目地址/jspConfigTest 才能显示对应的值
--%>
<h3>This param is setting in web.xml : <%response.getWriter().print(config.getInitParameter("word"));%></h3>
<%--
    因为以下二者均为jsp专用标签或者命令所以配置地址时候开头的 / 会被翻译成项目目录
    也就是http://127.0.0.1:8080/J2EEV1_war/
--%>
<%@include file="/JSPPages/FooterDemo.jsp" %>
<jsp:include page="/JSPPages/FooterDemo.jsp">
    <jsp:param name="year" value="2021"/>
</jsp:include>
</body>
</html>
