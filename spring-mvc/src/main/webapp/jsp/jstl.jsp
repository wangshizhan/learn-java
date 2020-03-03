<%@ page import="com.sun.org.apache.xml.internal.utils.AttList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hnanet.servlet.loginServlet.User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/2/18
  Time: 2:31 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List list =  new ArrayList();
    list.add("one");
    list.add("two");
    list.add("three");
    request.setAttribute("list",list);

    request.setAttribute("number",2);

%>
<h3>if</h3>
<br>
<c:if test="${not empty list}">
    <h3> choose when </h3>
    <br>
    <c:choose>
        <c:when test="${number=='1'}">星期-</c:when>
        <c:when test="${number=='2'}">星期二</c:when>
        <c:when test="${number=='3'}">星期三</c:when>
        <c:when test="${number=='4'}">星期四</c:when>
        <c:when test="${number=='5'}">星期五</c:when>
        <c:when test="${number=='6'}">星期六</c:when>
        <c:when test="${number=='7'}">星期日</c:when>
        <c:otherwise>错误日期</c:otherwise>
    </c:choose>
    <br>
    <h3> forEach </h3>
    <br>
    <c:forEach begin="0" end="20" step="2" var="i" varStatus="s">
        value: ${i}  index: ${s.index} count:${s.count} <br>
    </c:forEach>
    <br>
    <c:forEach items="${list}" var="item" varStatus="i">
        value: ${list[i.index]} value: ${item} index: ${i.index} count: ${i.count} <br>
    </c:forEach>
    <br>

    <%
        List<User> users = new ArrayList<User>();
        users.add(new User("100","tom","123",new Date()));
        users.add(new User("101","toc","124",new Date()));
        users.add(new User("102","tob","125",new Date()));
        users.add(new User("103","ton","126",new Date()));
        request.setAttribute("users",users);
    %>
    <table border="1" width="300" align="left">
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>密码</th>
            <th>生日</th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="i">
            <c:if test="${i.count % 2 == 0}">
                <tr bgcolor="#5f9ea0">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.password}</td>
                    <td>${user.birthDay}</td>
                </tr>
            </c:if>
            <c:if test="${i.count % 2 != 0}">
                <tr bgcolor="#7fff00">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.password}</td>
                    <td>${user.birthDay}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
