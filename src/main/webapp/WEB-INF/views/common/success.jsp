<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>
	<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">错误信息</h1>
	</header>
	<div class="mui-content">
		<div class="mui-content-padded">
			<div style="color: #00cc00;">
			<h1 class="mui--text-center">${jumpMsg}</h1>
			</div>
		</div>
		<a href="<c:url value="${jumpUrl}"/>" class="mui-btn mui-btn-primary mui-btn-block"><span id="jumpTo">5</span>秒后自动跳转</a>
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
countDown(5,'<c:url value="${jumpUrl}"/>');
</script> 
<%@ include file="../include/footer.jsp"%>