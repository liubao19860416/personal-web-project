<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.text.SimpleDateFormat;"%>

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>time.jsp测试的页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv=Content-Type content="text/html; charset=utf-8">

<c:set var="baseurl" value="${pageContext.request.contextPath}/" />
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${baseurl}js/easyui/jquery.easyui.min.1.2.2.js"></script>


</head>

<body>
	<font size="5" align="center" style="color: red;">
		<table size="10" align="center" style="color: red;width: 450px;height: 25px" border="1px" bordercolor="green">
			<tr>
				<td>本地电脑(JS  )时间0：</td>
				<td><span id="dt"></span><br/> </td>
			</tr>
			<tr>
				<td>本地电脑(JAVA)时间1：</td>
				<td><span id="dt1">${time1}</span> </td>
			</tr>
			<tr>
				<td >瑞金服务器(URL)时间1：</td>
				<td><span id="dt2" style="color: black;">${time2}</span> </td>
			</tr>
			<tr>
				<td>瑞金服务器(URL)时间2：</td>
				<td><span id="dt3">${time3}</span> </td>
			</tr>
			<tr>
				<td>百度服务器(URL)时间为1：</td>
				<td><span id="dt4">${time4}</span> </td>
			</tr>
			<tr>
				<td>百度服务器(URL)时间为2：</td>
				<td><span id="dt5">${time5}</span> </td>
			</tr>
			<tr>
				<td>114time服务(URL)时间为：</td>
				<td><span id="dt6">${time6}</span> </td>
			</tr>
		</table> 
		
	<hr/>
	<span id="dt1">${time1}</span><br/> 
	<span id="dt2">${time2}</span><br/> 
	<span id="dt3">${time3}</span><br/> 
	<span id="dt4">${time4}</span><br/>
	<span id="dt5">${time5}</span><br/>
	<span id="dt6">${time6}</span><br/>
	</font>
</body>


<script language="javascript">

	//本地电脑上获取初始时间 
	var currentDate = new Date(
<%=new java.util.Date().getTime()%>
	);
	function run() {
		currentDate.setSeconds(currentDate.getSeconds() + 1);
		var time = "";
		var year = currentDate.getFullYear();
		var month = currentDate.getMonth() + 1;
		var day = currentDate.getDate();
		var hour = currentDate.getHours();
		var minute = currentDate.getMinutes();
		var second = currentDate.getSeconds();
		if (hour < 10) {
			time += "0" + hour;
		} else {
			time += hour;
		}
		time += ":";
		if (minute < 10) {
			time += "0" + minute;
		} else {
			time += minute;
		}
		time += ":";
		if (second < 10) {
			time += "0" + second;
		} else {
			time += second;
		}
		document.getElementById("dt").innerHTML = year + "年" + month + "月"
				+ day + "日" + time;
	}

	//获取其他服务器的时间
	var timeNowLong = $("#dt1").html();
	//alert(timeNowLong);
	var currentDate1 = new Date(timeNowLong * 1);
	function showtime1() {
		//alert(currentDate1);
		currentDate1.setSeconds(currentDate1.getSeconds() + 1);
		var time1 = "";
		var year1 = currentDate1.getFullYear();
		var month1 = currentDate1.getMonth() + 1;
		var day1 = currentDate1.getDate();
		var hour1 = currentDate1.getHours();
		var minute1 = currentDate1.getMinutes();
		var second1 = currentDate1.getSeconds();
		if (hour1 < 10) {
			time1 += "0" + hour1;
		} else {
			time1 += hour1;
		}
		time1 += ":";
		if (minute1 < 10) {
			time1 += "0" + minute1;
		} else {
			time1 += minute1;
		}
		time1 += ":";
		if (second1 < 10) {
			time1 += "0" + second1;
		} else {
			time1 += second1;
		}
		document.getElementById("dt1").innerHTML = year1 + "年" + month1 + "月"
				+ day1 + "日" + time1;

	}
	
	//获取其他服务器的时间
	var timeNowLong = $("#dt2").html();
	var currentDate2 = new Date(timeNowLong * 1);
	//通用方法
	function showtime2() {
		//alert(currentDate1);
		currentDate2.setSeconds(currentDate2.getSeconds() + 1);
		var time2 = "";
		var year2 = currentDate2.getFullYear();
		var month2 = currentDate2.getMonth() + 1;
		var day2 = currentDate2.getDate();
		var hour2 = currentDate2.getHours();
		var minute2 = currentDate2.getMinutes();
		var second2 = currentDate2.getSeconds();
		if (hour2 < 10) {
			time2 += "0" + hour2;
		} else {
			time2 += hour2;
		}
		time2 += ":";
		if (minute2 < 10) {
			time2 += "0" + minute2;
		} else {
			time2 += minute2;
		}
		time2+= ":";
		if (second2 < 10) {
			time2 += "0" + second2;
		} else {
			time2 += second2;
		}
		document.getElementById("dt2").innerHTML = year2 + "年" + month2 + "月"
				+ day2 + "日" + time2;

	}
	
	//获取其他服务器的时间
	var timeNowLong = $("#dt3").html();
	var currentDate3 = new Date(timeNowLong * 1);
	//通用方法
	function showtime3() {
		currentDate3.setSeconds(currentDate3.getSeconds() + 1);
		var time3 = "";
		var year3 = currentDate3.getFullYear();
		var month3 = currentDate3.getMonth() + 1;
		var day3 = currentDate3.getDate();
		var hour3 = currentDate3.getHours();
		var minute3 = currentDate3.getMinutes();
		var second3 = currentDate3.getSeconds();
		if (hour3 < 10) {
			time3 += "0" + hour3;
		} else {
			time3 += hour3;
		}
		time3 += ":";
		if (minute3 < 10) {
			time3 += "0" + minute3;
		} else {
			time3 += minute3;
		}
		time3+= ":";
		if (second3 < 10) {
			time3 += "0" + second3;
		} else {
			time3 += second3;
		}
		document.getElementById("dt3").innerHTML = year3 + "年" + month3 + "月"
				+ day3 + "日" + time3;

	}
	
	//获取其他服务器的时间
	var timeNowLong = $("#dt4").html();
	var currentDate4 = new Date(timeNowLong * 1);
	//通用方法
	function showtime4() {
		currentDate4.setSeconds(currentDate4.getSeconds() + 1);
		var time4 = "";
		var year4 = currentDate4.getFullYear();
		var month4 = currentDate4.getMonth() + 1;
		var day4 = currentDate4.getDate();
		var hour4 = currentDate4.getHours();
		var minute4 = currentDate4.getMinutes();
		var second4 = currentDate4.getSeconds();
		if (hour4 < 10) {
			time4 += "0" + hour4;
		} else {
			time4 += hour4;
		}
		time4 += ":";
		if (minute4 < 10) {
			time4 += "0" + minute4;
		} else {
			time4 += minute4;
		}
		time4+= ":";
		if (second4 < 10) {
			time4 += "0" + second4;
		} else {
			time4 += second4;
		}
		document.getElementById("dt4").innerHTML = year4 + "年" + month4 + "月"
				+ day4 + "日" + time4;
	}
	
	
	//获取其他服务器的时间
	var timeNowLong = $("#dt5").html();
	var currentDate5 = new Date(timeNowLong * 1);
	//通用方法
	function showtime5() {
		currentDate5.setSeconds(currentDate5.getSeconds() + 1);
		var time5 = "";
		var year5 = currentDate5.getFullYear();
		var month5 = currentDate5.getMonth() + 1;
		var day5 = currentDate5.getDate();
		var hour5 = currentDate5.getHours();
		var minute5 = currentDate5.getMinutes();
		var second5 = currentDate5.getSeconds();
		if (hour5 < 10) {
			time5 += "0" + hour5;
		} else {
			time5 += hour5;
		}
		time5 += ":";
		if (minute5 < 10) {
			time5 += "0" + minute5;
		} else {
			time5 += minute5;
		}
		time5+= ":";
		if (second5 < 10) {
			time5 += "0" + second5;
		} else {
			time5 += second5;
		}
		document.getElementById("dt5").innerHTML = year5 + "年" + month5 + "月"
				+ day5 + "日" + time5;
	}
	
	//获取其他服务器的时间
	var timeNowLong = $("#dt6").html();
	var currentDate6 = new Date(timeNowLong * 1);
	//通用方法
	function showtime6() {
		currentDate6.setSeconds(currentDate6.getSeconds() + 1);
		var time6 = "";
		var year6 = currentDate6.getFullYear();
		var month6 = currentDate6.getMonth() + 1;
		var day6 = currentDate6.getDate();
		var hour6 = currentDate6.getHours();
		var minute6 = currentDate6.getMinutes();
		var second6 = currentDate6.getSeconds();
		if (hour6 < 10) {
			time6 += "0" + hour6;
		} else {
			time6 += hour6;
		}
		time6 += ":";
		if (minute6 < 10) {
			time6 += "0" + minute6;
		} else {
			time6 += minute6;
		}
		time6+= ":";
		if (second6 < 10) {
			time6 += "0" + second6;
		} else {
			time6 += second6;
		}
		document.getElementById("dt6").innerHTML = year6 + "年" + month6 + "月"
				+ day6 + "日" + time6;
	}

	//页面加载完成后调用
	$(function() {

		//显示通过js代码获取的本地电脑时间
		window.setInterval("run();", 1000);
		//显示通过java程序获取的本地时间
		window.setInterval("showtime1();", 1000);
		//显示通过URL获取的瑞金时间1
		window.setInterval("showtime2();", 1000);
		//显示通过URL获取的瑞金时间2
		window.setInterval("showtime3();", 1000);
		//显示通过URL获取的百度时间1
		window.setInterval("showtime4();", 1000);
		//显示通过URL获取的百度时间2
		window.setInterval("showtime5();", 1000);
		//显示通过URL获取的114time时间2
		window.setInterval("showtime6();", 1000);

	});
</script>

</html>