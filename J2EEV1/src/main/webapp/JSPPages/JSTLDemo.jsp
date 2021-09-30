<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/28
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>JSTL Demo</title>
</head>
<body>
<%--
    JSTL JSP Standard Tag Library 标准标签库
    JSTL允许开人员可以像使用HTML标签 那样在JSP中开发Java功能。
    JSTL库有core, i18n, fmt, sql 等等。
    i18n和sql用的很少，core和fmt在工作中会用到，本章节主要讲解core和fmt。
--%>
<%--
    要想使用JSTL必须先导入JSTL的标签库
    只要添加下面（上方已添加）这句就行。
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        tablib 表示这是一个标签库的导入
        prefix="c"表示此标签库以 <c: 打头
--%>
<%--
    set out remove

    set
        <c:set var="name" value="$ {'AlienCat'}" scope="request" />
        在作用域request中设置name,等价于下面的语句：
            <%request.setAttribute("name","AlienCat")%>

    out
        <c:out value="$ {name}" />(不会发生换行)
        等价于下面这些语句：
            <%=request.getAttribute("name")%>
            <%out.print(request.getAttribute("name"));%>
            <%response.getWriter().print(request.getAttribute("name"));%>
        其实也可以直接写成：$ {name}，但是out有比这更多的用法
        区别：
            后三者可以添加标签
            而 <c:out 标签的value属性中即便写了标签也会被当作文本输出。
            还有一点很重要的是
                如果用的是<%response.getWriter().print(request.getAttribute("name"));%>
                由于<c:out还有<%=还有<%out.print()%>用的都是JSP的隐式对象out
                out对象是包装过后的response.getWriter()，所以刷新流或者说flush的时机并不跟response.getWriter()一样。
                所以如果用了<%response.getWriter().print()%>，即便顺序在后面也会发现这个比使用了隐式对象out输出的文本更靠前。
                但只是输出顺序靠前，并不代表执行顺序也靠前，实际内部逻辑执行顺序还是跟JSP上面写的顺序一样。

    remove
        <c:remove var="name" scope="request" />
        在作用域request中删掉name,等价于下面的语句：
        <%request.removeAttribute("name");% >

    作用域可以是pageContext, request, session, application
--%>
<h2>set out remove Test</h2>

<%request.setAttribute("SpiderMan", "PeterParker");%>
<%-- 下面两句表明value属性可以用EL也可以不用 --%>
<c:set var="IronMan" value="TonyStark" scope="request"/>
<c:set var="CaptainAmerica" value="${'SteveRogers'}" scope="request"/>

<%="<h5>SpiderMan is " + request.getAttribute("SpiderMan") + "</h5>"%>
<c:out value="<h5>IronMan is ${IronMan}</h5>"/>
<%out.print("<h5>Sherlock is " + request.getAttribute("IronMan") + "</h5>");%>
<%response.getWriter().print("<h5>CaptainAmerica is " + request.getAttribute("CaptainAmerica") + " 这句话由于是使用response.getWriter()所以先被flush了，输出文本顺序就靠前。</h5>");%>

<c:remove var="IronMan" scope="request"/>
<%request.removeAttribute("CaptainAmerica");%>

<h5>After Remove</h5>
<c:out value="<h5>IronMan is ${IronMan}</h5>"/>
<c:out value="<h5>CaptainAmerica is ${CaptainAmerica}</h5>"/>
<br/>

<%--
    if else

    JSTL通过<c:if test=""> 进行条件判断
    但是JSTL没有<c:else，所以常用的办法是在<c:if的条件里取反
    配合if使用的还有通过empty进行为空判断，empty可以判断对象是否为null,字符串长度是否为0，集合长度是否为0。
    详见下方例子
--%>
<h2>if else Test</h2>
<c:set var="length" value="${10}" scope="request"/>

<c:if test="${length<20}">
    <h5>length is lower than 20</h5>
</c:if>

<c:if test="${length>20}">
    <h5>length is bigger than 20</h5>
</c:if>
<br/>

<%--
    choose

    虽然JSTL没有提供else标签，但是提供了一个else功能的标签
    <c:choose>
        <c:when test="${length<5}">
	    </c:when>
	    <c:otherwise>
	    </c:otherwise>
    </c:choose>
--%>
<c:choose>
    <c:when test="${length<5}">
        <h5>length is lower than 5</h5>
    </c:when>
    <c:otherwise>
        <h5>length is bigger than 5</h5>
    </c:otherwise>
</c:choose>
<br/>

<%--
    forEach

    可以在JSP中使用for循环，但是其可读性很差。
    借助JSTL的c:forEach标签，可以改善可读性。
    例：
    <c:forEach items="${products}" var="product" varStatus="st"  >
    解释：
        items="${products}" 表示遍历的集合
        var="product" 表示把每一个集合中的元素放在product上
        varStatus="st" 表示遍历的状态
--%>
<%
    List<Product> products = new ArrayList<Product>();
    for (int i = 0; i < 3; i++) {
        Product product = new Product();
        product.setId(6 + i);
        product.setName("Nokia 9" + (i + 6) + "0");
        product.setPrice(4000 + i * 100);
        products.add(product);
    }
    request.setAttribute("products", products);
%>
<table border="1" cellspacing="0">
    <tr>
        <td>Number</td>
        <td>Product ID</td>
        <td>Product Name</td>
        <td>Product Price</td>
    </tr>
    <c:forEach items="${products}" var="product" varStatus="st">
        <tr>
            <td>${st.count}</td>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<%System.out.println("aa");%>
<%--
    forTokens

    <c:forTokens专门用于字符串拆分，并且可以指定多个分隔符
--%>
<c:set var="categories" value="Nokia Line,Apple Line;HuaWei Line|XiaoMi Line!Oppo Line"/>
<table border="1" cellspacing="0">
    <tr>
        <td>Number</td>
        <td>Brand Name</td>
    </tr>
    <c:forTokens items="${categories}" delims=",;!|" var="category" varStatus="st">
        <tr>
            <td>${st.count}</td>
            <td>${category}</td>
        </tr>
    </c:forTokens>
</table>
<br/>

<%--
    fmt:formatNumber 格式化数字

    fmt 标签常用来进行格式化，其中fmt:formatNumber用于格式化数字
    (使用之前要加上<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>)

    <fmt:formatNumber type="number" value="${price}" minFractionDigits="2"/>
    解释：
        <fmt:formatNumber 表示格式化数字
        minFractionDigits 小数点至少要有的位数
        maxFractionDigits 小数点最多能有的位数
--%>
<c:set var="number" value="888.8"/>
<c:set var="pi" value="3.1415926"/>
<c:set var="integer" value="${1000}"/>
<h5>最少两个小数点：<fmt:formatNumber type="number" value="${number}" minFractionDigits="2"/></h5>
<h5>最多两个小数点：<fmt:formatNumber type="number" value="${pi}" maxFractionDigits="2"/></h5>
<br/>

<%--
    fmt:formatDate 格式化日期

    fmt 标签常用来进行格式化，其中fmt:formatDate 用于格式化日期
    <fmt:formatNumber type="number" value="${price}" minFractionDigits="2"/>

    <fmt:formatDate value="${now}" pattern="G yyyy年MM月dd日 E"/>
    <fmt:formatDate value="${now}" pattern="a HH:mm:ss.S z"/>
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
    解释：
        <fmt:formatDate 表示格式化日期
        G 表示公元前还是后
        yyyy 表示年份
        MM 表示月份
        dd 表示日期
        E 表示星期几
        a 表示是上午还是下午
        HH 表示小时
        mm 表示分钟
        ss 表示秒
        S 表示毫秒
        z 表示时区
--%>
<%
    Date date = new Date();
    pageContext.setAttribute("currentDate", date);
%>
<h5><fmt:formatDate value="${currentDate}" pattern="G yyyy年MM月dd日 E"/></h5>
<h5><fmt:formatDate value="${currentDate}" pattern="a HH时mm分ss.S秒 z"/></h5>
<h5><fmt:formatDate value="${currentDate}" pattern="G yyyy-MM-dd HH:mm:ss"/></h5>
<br/>

<%--
    fn:

    fn标签提供各种实用功能，首先使用之前使用加入如下指令
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    用法举例：
        ${fn:substring(name, 0, 5)
        获取name的前5位

        fn:contains(string, substring)
        如果参数string中包含参数substring，返回true

        fn:containsIgnoreCase(string, substring)
        如果参数string中包含参数substring（忽略大小写），返回true

        fn:endsWith(string, suffix)
        如果参数 string 以参数suffix结尾，返回true

        fn:escapeXml(string)
        将有特殊意义的XML (和HTML)转换为对应的XML character entity code，并返回

        fn:indexOf(string, substring)
        返回参数substring在参数string中第一次出现的位置

        fn:join(array, separator)
        将一个给定的数组array用给定的间隔符separator串在一起，组成一个新的字符串并返回。

        fn:length(item)
        返回参数item中包含元素的数量。参数Item类型是数组、collection或者String。如果是String类型,返回值是String中的字符数。

        fn:replace(string, before, after)
        返回一个String对象。用参数after字符串替换参数string中所有出现参数before字符串的地方，并返回替换后的结果

        fn:split(string, separator)
        返回一个数组，以参数separator 为分割符分割参数string，分割后的每一部分就是数组的一个元素

        fn:startsWith(string, prefix)
        如果参数string以参数prefix开头，返回true

        fn:substring(string, begin, end)
        返回参数string部分字符串, 从参数begin开始到参数end位置，包括end位置的字符

        fn:substringAfter(string, substring)
        返回参数substring在参数string中后面的那一部分字符串

        fn:substringBefore(string, substring)
        返回参数substring在参数string中前面的那一部分字符串

        fn:toLowerCase(string)
        将参数string所有的字符变为小写，并将其返回

        fn:toUpperCase(string)
        将参数string所有的字符变为大写，并将其返回

        fn:trim(string)
        去除参数string 首尾的空格，并将其返回
--%>
</body>
</html>
