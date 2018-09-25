<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<c:url value="/showMessage.html" var="messageUrl" />
		<a href="${messageUrl}">test</a>
		<h1><a href="http://localhost:8080/Test/swagger-doc/index.html">[后台测试]</a></h1>
		<c:url value="/swagger-doc/index.html" var="messageUrl" />
		<a href="${messageUrl}">优卡丹后台</a>
		<!-- webapp是根目录，当前同级目录 直接子目录_/，相对路径&和&  根目录下/_，绝对路径，-->
		<h2><a href="swagger-doc/index.html">[后台测试]</a></h2>
		<h3><a href="/Test/swagger-doc/index.html">[后台测试]</a></h3>
	</body>
</html>
