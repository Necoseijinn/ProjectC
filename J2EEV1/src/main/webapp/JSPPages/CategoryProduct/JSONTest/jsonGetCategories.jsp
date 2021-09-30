<%--
  Created by IntelliJ IDEA.
  User: ITS-069
  Date: 2021/09/24
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSON Get Categories</title>
</head>
<body>
<table border="1" cellspacing="0" id="categoryTable">
    <tr>
        <td>Category Type</td>
        <td>
            <select id="categoryId">
                <option value="all">all</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr id="targetTr">
        <td>
            <a href="JSPPages/index.jsp">Back To Index</a>
        </td>
        <td>
            <input type="button" id="sender" value="Get Category"/>
        </td>
    </tr>
</table>
<script>
    function getCategory() {
        /***
         * 获取所有由JS添加的<td>节点
         * @type {HTMLCollectionOf<Element>}
         */
        var removeTargetNoeds = document.getElementsByClassName("add");
        /***
         * 由于删除节点的时候会导致整个List长度变短并且角标也会变化
         * 所以要用从末尾开始删除的方法来实现删除子节点List
         */
        for (var i = removeTargetNoeds.length - 1; i >= 0; i--) {
            removeTargetNoeds[i].parentNode.removeChild(removeTargetNoeds[i]);
        }
        var categoryId = document.getElementById("categoryId").value;
        var url = "${pageContext.request.contextPath}/JSONGetCategories";
        var data = {"type": categoryId};
        /***
         * 原生JavaScript的Ajax写法
         */
            //创建XMLHttpRequest对象
        var xhr = new XMLHttpRequest();

        /***
         * 设置请求方法为Post以及请求url以及设置同步还是异步方式发送请求
         * 同步请求
         *    主线程中的同步请求会阻塞页面，由于对用户体验的糟糕效果，部分最新浏览器在主线程上的同步请求已经被弃用。
         *    在少数情况下,只能使用同步模式的XMLHttpRequest请求.比如在 window.onunload和window.onbeforeunload 事件处理函数中。
         *    在页面unload事件处理函数中使用异步的XMLHttpRequest会引发这样的问题:当响应返回之后,页面已经不复存在,
         *    所有变量和回调函数也已经销毁.结果只能引起一个错误 ,“函数未定义”。
         *    解决办法是在这里使用同步模式的请求,这样的话,当请求完成之前,页面不会被关闭.
         * 异步请求
         *    使用异步模式的话,当数据完全请求回来以后,会执行一个指定的回调函数, 在执行请求的同时,浏览器可以正常的执行其他事务的处理。
         * 此处为异步模式
         */
        xhr.open("POST", url, true);
        /***
         * application/x-www-urlencoded是浏览器默认的编码格式，用于键值对参数，参数之间用&间隔
         * multipart/form-data常用于文件等二进制，也可用于键值对参数，最后连接成一串字符传输(参考Java OK HTTP)。
         * 除了这两个编码格式，还有application/json也经常使用。
         *
         * 此处为了将完整的JSON数据以字符串表现形式传输到后端,所以用的application/x-www-urlencoded
         * ServletRequest无法直接解析JSON字符串数据，因为要解析的话必须符合键值对的形式，而JSON对象的字符串形式并不符合。
         * 所以这里利用了一个Key指向一个JSON字符串数据的Value传到后端，用的 Content-Type:application/x-www-form-urlencoded;charset=UTF-8
         */
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var targetTr = document.getElementById("targetTr")
                var resultList = JSON.parse(xhr.responseText);
                for (i in resultList) {
                    var trTag = document.createElement("tr");
                    /***
                     * 此处通过设置innerHTML直接添加HTML语句到对应标签上
                     */
                    trTag.innerHTML = "<td class='add'>CategoryID : " + resultList[i].id + "</td><td class='add'>CategoryName : " + resultList[i].name + "</td>";
                    targetTr.parentNode.insertBefore(trTag, targetTr);
                }
            }
        }
        xhr.send("data=" + JSON.stringify(data));

        /** 以下为上面原生Javascript对应的JQuery的Ajax写法 **/
        /*
            $.post(
                url,
                {"data":JSON.stringify(data)},
                function (resultData){
                    var resultList = JSON.parse(resultData);
                    for (i in resultList) {
                        var trTag = document.createElement("tr");
                        trTag.innerHTML = "<td class='add'>CategoryID : " + resultList[i].id + "</td><td class='add'>CategoryName : " + resultList[i].name + "</td>";
                        targetTr.parentNode.insertBefore(trTag, targetTr);
                    }
                }
            );
         */
    }

    var sendButton = document.getElementById("sender");
    sendButton.setAttribute("onclick", "getCategory()");
</script>
</body>
</html>
