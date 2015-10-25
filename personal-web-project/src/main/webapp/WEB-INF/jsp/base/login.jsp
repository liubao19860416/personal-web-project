<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
<title>WEB-INF下登录页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"/>
<script type="text/javascript" src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"/>

<style type="text/css">
.btnalink {
	cursor: hand;
	display: block;
	width: 80px;
	height: 29px;
	float: left;
	margin: 12px 28px 12px auto;
	line-height: 30px;
	background: url('${baseurl}images/login/btnbg.jpg') no-repeat;
	font-size: 14px;
	color: #fff;
	font-weight: bold;
	text-decoration: none;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	//*****************表单校验******************
	$.formValidator.initConfig({
		formID : "loginform",
		mode:'AlertTip',
		onError : function(msg) {
			alert(msg);
		},
		onAlert : function(msg) {
			alert(msg);
		}
	});
	$("#userid").formValidator({
		onShow : "",
		onCorrect:"&nbsp;"
	}).inputValidator({
		min : 1,
		onError:"请输入用户名"
	});
	$("#password").formValidator({
		onShow : "",
		onCorrect:"&nbsp;"
	}).inputValidator({
		min : 1,
		onError:"请输入密码"
	});
	$("#randomcode").formValidator({
		onShow : "",
		onCorrect:"&nbsp;"
	}).inputValidator({
		min : 1,
		onError:"请输入验证码"
	});
	//*****************表单校验******************
});

	//校验表单输入
	function checkinput() {
		//return $('#loginform').form('validate');
		return $.formValidator.pageIsValid();
	}

	//登录提示方法
	function loginsubmit() {
		if(checkinput()){//校验表单，如果校验通过则执行jquerySubByFId
			//ajax form提交
			jquerySubByFId('loginform', login_commit_callback,null,'json'); 
		}

	}
	//登录提示回调方法
	function login_commit_callback(data) {
		message_alert(data);
		var type = data.resultInfo.type;
		if (1 == type) {//如果登录成功，这里1秒后执行跳转到首页
			setTimeout("tofirst()", 1000);
		} else {
			//登录错误，重新刷新验证码
			randomcode_refresh();
		}

	}
	//刷新验证码
	//实现思路，重新给图片的src赋值，后边加时间，防止缓存 
	function randomcode_refresh() {
		$("#randomcode_img").attr('src',
				'${baseurl}validatecode.jsp?time' + new Date().getTime());
	}
	//回首页
	function tofirst(){
		if(parent.parent.parent.parent){
			parent.parent.parent.parent.location='${baseurl}first.action';
		}else if(parent.parent.parent){
			parent.parent.parent.location='${baseurl}first.action';
		}else if(parent.parent){
			parent.parent.location='${baseurl}first.action';
		}else if(parent){
			parent.location='${baseurl}first.action';
		}else{
			window.location='${baseurl}first.action';
		}

	}
</script>
</head>
<body style="background: #f6fdff url(${baseurl}images/login/bg1.jpg) repeat-x;">
	<form id="loginform" name="loginform" action="${baseurl}loginsubmit.action"
		method="post">
		<div class="logincon">

			<div class="title">
				<img alt="" src="${baseurl}images/login/logo.png">
			</div>

			<div class="cen_con">
				<img alt="" src="${baseurl}images/login/bg2.png">
			</div>

			<div class="tab_con">

				<input type="password" style="display:none;" />
				<table class="tab" border="0" cellspacing="6" cellpadding="8">
					<tbody>
						<tr>
							<td>用户名：</td>
							<td colspan="2"><input type="text" id="userid"
								name="userid" style="width: 130px" /></td>
						</tr>
						<tr>
							<td>密 码：</td>
							<td><input type="password" id="password" name="pwd" style="width: 130px" />
							</td>
						</tr>
						<tr>
							<td>验证码：</td>
							<td><input id="randomcode" name="randomcode" size="8" /> <img
								id="randomcode_img" src="${baseurl}validatecode.jsp" alt=""
								width="56" height="20" align='absmiddle' /> <a
								href=javascript:randomcode_refresh()>刷新</a></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="button"
								class="btnalink" onclick="loginsubmit()" value="登&nbsp;&nbsp;录" />
								<input type="reset" class="btnalink" value="重&nbsp;&nbsp;置" /></td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>
	</form>
</body>
</html>
