<%@ page import="com.hnanet.servlet.loginServlet.User" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.hutool.db.Session" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/2/5
  Time: 10:28 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" buffer="16kb"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3> out.write 与 response.getWriter().write </h3>
<%
    out.write("hello jsp out.write 1.");
%>
<br>
<%="hello jsp2"%>
<br>
<%
    response.getWriter().write("hello jsp response.getWriter().write(). 3");
    response.getWriter().write("<br>");
%>
<br>
<%
    User user = new User();
    user.setBirthDay(new Date());
    user.setName("tom");
    user.setPassword("123456");
    request.setAttribute("u", user);

    List list = new ArrayList<String>();
    list.add("li1");
    list.add("li2");
    list.add("li3");
    pageContext.setAttribute("list",list);

    Map map = new HashMap<>();
    map.put("map1","value1");
    map.put("map2","value2");
    map.put("map3","value3");
    application.setAttribute("map",map);
%>

<h3> EL 表达式 </h3>
<div><%=request.getAttribute("u")==null? "" : ((User)request.getAttribute("u")).getBirthDay()%></div><br>
${requestScope.u.id}<br>
${requestScope.u.name}<br>
${requestScope.u.birthDay}<br>

<h4> 获取 List </h4>
${list}<br>
${list[0]}<br>
${list[1]}<br>

<h4> 获取 Map </h4>
${map}<br>
${map.map1}<br>
${map.map2}<br>
${map["map3"]}<br>

<h4> 获取 ,empty </h4>
<%
    String nullStr = null;
    session.setAttribute("nullStr",nullStr);
    String str = "null";
    session.setAttribute("str",str);
    Map map1 = new HashMap();
    session.setAttribute("map1",map1);
    session.setAttribute("str",str);
    List list1 = new ArrayList();
    session.setAttribute("list1",list1);
%>
${empty str}<br>
${empty nullStr}<br>
${empty map1}<br>
${empty list1}<br>
<h4> 动态获取虚拟目录 </h4>
${pageContext.request.contextPath}


</body>
</html>
