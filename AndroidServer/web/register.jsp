<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2018/2/10
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>register.jsp</title>
    <style type="text/css">
        .errorInfo{
            color: red;
        }
    </style>
</head>

<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="${formbean.username}">*</td>
            <td class="errorInfo">${formbean.errors.username}</td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value="${formbean.password}">*</td>
            <td class="errorInfo">${formbean.errors.password}</td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" name="password2" value="${formbean.password2}">*</td>
            <td class="errorInfo">${formbean.errors.password2}</td>
        </tr>
        <tr>
            <td>生日：</td>
            <td><input type="text" name="birthday" value="${formbean.birthday}">*</td>
            <td class="errorInfo">${formbean.errors.birthday}</td>
        </tr>
        <tr>
            <td>Email：</td>
            <td><input type="text" name="email" value="${formbean.email}"></td>
            <td class="errorInfo">${formbean.errors.email}</td>
        </tr>
        <tr>
            <td align="right"><input type="reset" value="重置"></td>
            <td><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
</html>
