<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:set var="baseurl" value="${pageContext.request.contextPath}/"/>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"/>
<script type="text/javascript" src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"/>

<!-- 
其中dwrService.js是dwr根据配置文件自动生成的，就是dwr.xml文件中的javasecript标签对应的值，
engine.js和util.js是dwr自带的脚本文件，默认写上就行了
 -->
<script src="${baseurl}dwr/interface/dwrService.js"/> 
<script src="${baseurl}dwr/engine.js"/> 
<script src="${baseurl}dwr/util.js"/> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DWR测试页面(ROOT目录)</title>

<script type="text/javascript">

	$(function() {
		
		//dwr测试1
		/* dwrService.testdwr({
			callback : function(data) {
				alert(data);
			}
		}); */
		
		//dwr测试2
		/* dwrService.testdwr2('Liubao', {
			callback : function(data) {
				alert(data);
			}
		}); */
		
		//通过dwr加载年份
		businessYearList('businessYear');
		alert("ok!");
		
	});
	
	$(function(){
		alert("ok!");
	});

	/**
	 *加载最近5年时间
	 * id:年份下拉框的id
	 */
	function businessYearList(id) {
		 
		//获取select对象
		var selectobj = document.getElementById(id);
		//给下拉框里边的option数量清0
		selectobj.length = 0;
		//调用dwr的方法
		dwrService.businessyear({
					callback : function(data) {
						if (data) {
							for (var m = 0; m < data.length; m++) {
								var info = data[m].info;
								var value = data[m].value;
								//在select下拉框中创建option
								//alert(selectobj.length);
								selectobj.options[selectobj.length] = new Option(info, value);
								if (m == 0) {
									//选中下拉框第一个值
									selectobj.options[selectobj.length - 1].selected = true;
								}
							}
						}
					}
				});
	}
	
	
	function buttonClick(){
		alert("${baseurl}");
	}
	
	
</script>

</head>

<body onload="javascript:void(0)">

	<form id="myPostForm" name="myPostForm" method="post" action="#">
		<input type="hidden" name="indexs" id="indexs" />
		<table style="border: 2px; color: red;" align="center">
			<tbody>
				<tr>
					<td>DWR测试：</td>
					<td>
						<input type="button" name="dwrButton" value="DWR按钮" onclick="javascript:buttonClick()"/>
					</td>
				</tr>
				<tr>
					<td>年份(如2014)：</td>
					<td>
						<select id="businessYear" name="businessYear">
						<option value="">请选择年份</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>用户名称：</td>
					<td>
						<input type="text" name="userName" />
					</td>
				</tr>
				<tr>
					<td>采购时间：</td>
					<td>
						<input id="startTime" name="startTime" onfocus="wdatepicker({isshowweek:false,skin:'whygreen',datefmt:'yyyy-mm-dd'})" style="width: 80px" />
						 -- 
					 	<input id="endTime" name="endTime" onfocus="wdatepicker({isshowweek:false,skin:'whygreen',datefmt:'yyyy-mm-dd'})" style="width: 80px" />
					</td>
				</tr>
				<tr>
					<td>采购状态：</td>
					<td>已发货 
						<a id="btn" href="#" onclick="statusQuery()" iconcls='icon-search'>查询</a>
					</td>
				</tr>
			</tbody>
		</table>

		<table border=0 cellspacing=0 cellpadding=0 width="99%" align=center>
			<tbody>
				<tr>
					<td>
						<table id="dataList"></table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</html>