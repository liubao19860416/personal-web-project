<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<HTML>
<HEAD>

<title>error.jsp(ROOT位置)</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"/>
<script type="text/javascript" src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"/>


<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<title>欢迎您！十分抱歉，服务器出错了 （ROOT位置）！</title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<meta http-equiv="refresh"
	content="100;URL=http://localhost:8080/estore">
<STYLE type=text/css>
INPUT {
	FONT-SIZE: 12px
}

TD {
	FONT-SIZE: 12px
}

.p2 {
	FONT-SIZE: 12px
}

.p6 {
	FONT-SIZE: 12px;
	COLOR: #1b6ad8
}

A {
	COLOR: #1b6ad8;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: red
}
</STYLE>

<META content="Microsoft FrontPage 5.0" name=GENERATOR>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false">
	<P align=center></P>
	<P align=center></P>

错误信息显示位置:
	<%-- <s:fielderror></s:fielderror> --%>


	<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
		<TBODY>
			<TR>
				<TD vAlign=top height=270>
					<DIV align=center>
						<BR>
						<IMG height=211 src="images/error.gif" width=329><BR>
						<BR>
						<TABLE cellSpacing=0 cellPadding=0 width="80%" border=0>
							<TBODY>
								<TR>
									<TD><FONT class=p2>&nbsp;&nbsp;&nbsp; <FONT
											color=#ff0000><IMG height=13 src="images/emessage.gif"
												width=12>&nbsp;无法访问本页的原因是：</FONT>
									</FONT>
									</TD>
								</TR>
								<TR>
									<TD height=8></TD>
								<TR>
									<TD>
										<P>
											<FONT color=#000000><BR> 服务器出现异常 ，异常信息...</FONT>!
										</P>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD height=5></TD>
			<TR>
				<TD align=middle>
					<CENTER>
						<TABLE cellSpacing=0 cellPadding=0 width=480 border=0>
							<TBODY>
								<TR>
									<TD width=6><IMG height=26 src="images/left.gif" width=7>
									</TD>
									<TD background=images/bg.gif>
										<DIV align=center>
											<FONT class=p6><A href="http://localhost:8080/estore">返回首页</A>
												| <A href="javascript:history.go(-1)">返回出错页</A> | <A
												href="http://localhost:8080/estore">关闭本页</A>
											</FONT>
										</DIV>
									</TD>
									<TD width=7><IMG src="images/right.gif">
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</CENTER>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<P align=center></P>
	<P align=center></P>
</BODY>
</HTML>
