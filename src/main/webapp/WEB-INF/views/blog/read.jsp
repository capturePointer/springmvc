<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../include/header.jsp"%>
	<header class="mui-bar mui-bar-nav">
		<h1 class="mui-title">博文列表</h1>
	</header>
	<div class="mui-content">		
		<div class="mui-card">
			<!--页眉，放置标题-->
			<div class="mui-card-header">
				<div class="mui-media-body mui-content-padded">
					${data.title}
					<p>发表于 ${data.create_time}</p>
					<p>描述 ${data.body}</p>
				</div>
			</div>
			<!--内容区-->
			<div class="mui-card-content">
				<img width="100%" class="mui-media-object" src="<c:url value="/${data.image}"/>">
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer"></div>
		</div>
	</div>
	
	<nav class="mui-bar mui-bar-tab">
	  <a class="mui-tab-item mui-active" href="<c:url value="/blog/create"/>">
	    添加
	  </a>
	  <a class="mui-tab-item" href="<c:url value="/blog/list"/>">
	    列表
	  </a>
	  <a class="mui-tab-item" href="<c:url value="/user/loginOut"/>">
	    注销
	  </a>
	</nav>
<%@ include file="../include/footer.jsp"%>