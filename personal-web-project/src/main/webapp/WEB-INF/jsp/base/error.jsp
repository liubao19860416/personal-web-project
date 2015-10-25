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
<title>失败提示信息(WEB-INF位置)</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${baseurl}styles/body.css" type="text/css" />

<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"/>
<script type="text/javascript" src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"/>

</head>
<body>
	<table class="submit_hint">
		<tr>
			<td>
				<div id="tabBtnContainer" width="100%">
					<ul id="tabBtnUi">
						<li><a href="#"></a>失败提示信息(WEB-INF位置)</li>
					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td style="font-size: 14px; color: #ff0000; padding-top: 2px;"
				align='center' valign="middle"><img alt=""
				src="${baseurl}images/info.png" align="middle" height="30"
				width="30" /> <span style="vertical-align: middle">${exceptionResultInfo.resultInfo.message}</span>
			</td>
		</tr>
	</table>
	<table width='100%'>
		<tr>
			<td align="center"><input type="button" align="middle"
				class="button" value="确定" onclick="history.go(-1);" /></td>
		</tr>
	</table>
</body>
</html>