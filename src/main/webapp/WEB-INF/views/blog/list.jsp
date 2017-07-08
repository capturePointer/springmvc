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
		<ul class="mui-table-view">
			<c:if test="${not empty lists}">
				<c:forEach items="${lists}" var="value">
				    <div class="mui-row">
				        <div class="mui-col-sm-8">
				            <div class="mui-content-padded">
				            	<img class="mui-media-object mui-pull-left" src="<c:url value="/${value.image}"/>">
				                <a class="mui-navigate-right" href="<c:url value="/blog/read/${value.id}"/>">
				                    ${value.title}   
				                </a>
			                	<p class='mui-ellipsis'>${value.create_time}</p>
				            </div>
				        </div>
				        <div class="mui-col-sm-2">
				            <div class="mui-content-padded">
				                <a class="mui-btn mui-btn-block mui-btn-primary" href="<c:url value="/blog/delete/${value.id}"/>">
		                    		删除
				                </a>
			                </div>
				        </div>
				        <div class="mui-col-sm-2">
				            <div class="mui-content-padded">
				                <a class="mui-btn mui-btn-block mui-btn-primary" href="<c:url value="/blog/update/${value.id}"/>">
		                    		更新
				                </a>
			                </div>
				        </div>
				    </div>
				
				</c:forEach>
			</c:if>
		</ul>
	
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