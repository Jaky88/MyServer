<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2018/2/10
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>login.jsp</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登陆"></td>
        </tr>
    </table>
</form>
</body>
</html>
