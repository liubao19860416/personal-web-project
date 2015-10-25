<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>

<title>demo.jsp(ROOT位置)</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<c:set var="baseurl" value="${pageContext.request.contextPath}/" />
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js" />
<script type="text/javascript"
	src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js" />

</head>
<body>
	<h1>所有的演示例子</h1>
	<h3>[url=./dao.do?id=1]Dao正常错误[/url]</h3>
	<h3>[url=./dao.do?id=10]Dao参数错误[/url]</h3>
	<h3>[url=./dao.do?id=]Dao未知错误[/url]</h3>


	<h3>[url=./service.do?id=1]Service正常错误[/url]</h3>
	<h3>[url=./service.do?id=10]Service参数错误[/url]</h3>
	<h3>[url=./service.do?id=]Service未知错误[/url]</h3>


	<h3>[url=./controller.do?id=1]Controller正常错误[/url]</h3>
	<h3>[url=./controller.do?id=10]Controller参数错误[/url]</h3>
	<h3>[url=./controller.do?id=]Controller未知错误[/url]</h3>


	<h3>[url=./404.do?id=1]404错误[/url]</h3>
</body>
</html>
