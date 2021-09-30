<%@ page import="ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product" %><%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/28
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EL Demo</title>
</head>
<body>
<%--
    不同版本的tomcat是否默认开启对EL表达式的支持，是不一定的。
    所以为了保证EL表达式能够正常使用，需要在<%@page 标签里加上isELIgnored="false"
--%>
<%--
    EL表达式可以从pageContext,request,session,application四个作用域中取到值
    但如果四个作用域有重复的变量名的时候，由于EL是按照 pageContext>request>session>application 的优先级顺序取值的
    所以哪个作用域中线找到了对应变量就用哪个。
--%>
<c:set var="name" value="AlienCatFromPage" scope="page"/>
<c:set var="name" value="AlienCatFromRequest" scope="request"/>
<c:set var="name" value="AlienCatFromSession" scope="session"/>
<c:set var="name" value="AlienCatFromApplication" scope="application"/>
<%-- 此处可以不用<c:out value="${name}"/> 直接写${name} --%>
<h5>${name}</h5>
<br/>

<%--
    EL可以很方便的访问JavaBean的属性
    例子：
        ${product.id}
    说明：
        假设有一个Product类的实例product，这个实例设在四个作用域任意一个中。
        就可以用 ${product.id} 取出该实例的id属性的值。
        原理很简单，就是先找第一个 . 之前的字符串，比如此处为 product
        然后到四个作用域按优先顺序依次去 getAttribute("product")
        如果取出来了实例就进行下一步，第一个 . 之后的字符串都会按照 . 切割成一个个字符串
        然后从前往后依次调用自己 . 之前的对象的getter方法尝试获取每个字符串所对应的属性对应的值。
        比如 product.id 就是在获取到 product 实例之后去调用 product.getId() 这个方法来获取id属性的值。
--%>
<%
    Product product = new Product();
    product.setId(1);
    product.setName("AppleWatch");
    product.setPrice(1200);
    pageContext.setAttribute("product", product);
%>
<h5>The Product id is ${$product}</h5>
<br/>

<%--
    EL表达式还可以做到 request.getParameter("name") 这样的形式获取浏览器传递过来的参数。
    比如当使用 ProjectName/JSPPages/ELDemo.jsp?name=AlienCat 来访问此JSP的时候
    除了可以通过 request.getParameter("name") 来获取request传参之外，还可以用 ${param.name} 来获取。
--%>
<h5>The param name's value is ${param.name}</h5>
<br/>

<%--
    EL表达式还可以进行条件判断，大大简化了JSTL的 c:if 和 c:choose 代码。
        <c:if test="${number>=10}"> <c:when test="${number>=10}"> 例子在JSTL的JSP中有
    EL还可以使用三元运算符：
        ${product.price ge 1000? "higher":"lower"}
        eq相等 ne、neq不相等，
        gt大于， lt小于
        gt大于， lt小于
        gte、ge大于等于
        lte、le 小于等于
        not非 mod求模
        is [not] div by是否能被某数整除
        is [not] even是否为偶数
        is [not] odd是否为奇数
--%>
<h5>The Product's price is ${product.price ge 1000? "higher":"lower"} than 1000</h5>
<br/>
</body>
</html>
