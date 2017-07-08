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
		<form class="mui-input-group" action="<c:url value="/blog/doCreate"/>" enctype="multipart/form-data" method="post">
			
			<div class="mui-input-row">
				<label>标题</label>
				<input type="text" name="title" class="mui-input-clear mui-input" placeholder="请输入标题">
			</div>
			
			<div class="mui-input-row">
				<label>图片</label>
				<input type="file" name="imageTmp" class="mui-input-clear mui-input" placeholder="请选择图片">
			</div>
			<div class="mui-input-row">
				<label>正文</label>
				<textarea rows="" cols="" name="body"></textarea>
			</div>
			<div class="mui-content-padded">
				<button type="submit" class="mui-btn mui-btn-block mui-btn-primary">提交</button>
			</div>
			<div class="mui-content-padded">
				&nbsp;
			</div>
		</form>
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