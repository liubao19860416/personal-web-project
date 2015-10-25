<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>不在上班时间访问的页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv=Content-Type content="text/html; charset=utf-8">

<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"/>
<script type="text/javascript" src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"/>


</head>


<body>
	<h1 align="center">页面开始位置</h1>
	<p align="center">开始段落内容</p>
	<table width="666" align="center" border="2">
		<tbody>
			<tr>
				<td>项目名称</td>
				<td>outsideOfficeHours.jsp测试页面</td>
			</tr>
			<tr>
				<td>测试人</td>
				<td>刘保</td>
			</tr>
			<tr>
				<td>测试单位</td>
				<td>赛可电子</td>
			</tr>
		</tbody>
	</table>
	<p align="center">结束段落内容</p>
	<h1 align="center">页面结束位置</h1>
</body>
</html>