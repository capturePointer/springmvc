<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>message tips</title>
<link href="<c:url value="/resources/css/mui.min.css"/>" rel="stylesheet" />
</head>
	<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">错误信息</h1>
	</header>
	<div class="mui-content">
		<div class="mui-content-padded">
			<div style="color: #cc0000;">
			<h1 class="mui--text-center">您迷路了</h1>
			</div>
		</div>
		<a href="<c:url value="/blog/list"/>" class="mui-btn mui-btn-primary mui-btn-block"><span id="jumpTo">5</span>秒后自动跳转</a>
	</div>

	
	<script type="text/javascript">     
	function countDown(secs,surl){      
		 var jumpTo = document.getElementById('jumpTo');
		 jumpTo.innerHTML=secs;  
		 if(--secs>0){     
		     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
		 }     
		 else{       
		     location.href=surl;     
		 }     
	 }     
	countDown(5,'<c:url value="/blog/list"/>');
	</script> 
</body>
</html>