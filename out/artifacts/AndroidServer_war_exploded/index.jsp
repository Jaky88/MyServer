<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2018/2/9 0009
  Time: 下午 8:04
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>首页</title>
</head>

<body>
<h1>Jaky网站</h1>
<br><br>

<c:if test="${user!=null}">
  欢迎您，${user.username}!
  <a href="${pageContext.request.contextPath}/logout">退出</a>
</c:if>

<c:if test="${user==null}">
  <a href="${pageContext.request.contextPath}/login/ui">登陆</a>
  <a href="${pageContext.request.contextPath}/register/ui" target="_blank">注册</a>
</c:if>

<hr>
</body>
</html>
