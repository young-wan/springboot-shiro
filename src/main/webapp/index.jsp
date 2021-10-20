<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport">
    <title>Document</title>
</head>
<body>
<h1>系统主页</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出</a>
<ul>
    <shiro:hasAnyRoles name="user,admin">
        <li><a href="">用户管理</a>
            <ul>
                <shiro:hasPermission name="user:create:*">
                    <li><a href="">添加</a></li>
                </shiro:hasPermission>
                <li><a href="">修改</a></li>
                <li><a href="">删除</a></li>
                <li><a href="">查询</a></li>
            </ul>
        </li>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin">
        <li><a href="">商品管理</a></li>
        <li><a href="">部门管理</a></li>
        <li><a href="">订单管理</a></li>
    </shiro:hasRole>
</ul>
</body>
</html>