<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="splitRole" value="${fn:split(role,'/')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Web tester ${role}</title>
<link rel="shortcut icon" href="${context}/resources/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/normalize.css?v=${CSS_JS_VERSION}" />
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/styles.css?v=${CSS_JS_VERSION}" />
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/styles-plus.css?v=${CSS_JS_VERSION}" />
<spring:url value="/resources/JS/scripts.js" var="script" />
<script type="text/javascript" src="${script}"></script>
</head>

<body>

	<div id="wrap">
		<div id="headdiv">
			<a href="/wtapp/login"> <img class="inline-class"
				src="${context}/resources/images/wtlogo3.png"
				style="float: left; position: absolute; top: 0;" alt="logo" />
			</a>
			<c:if test="${account!=null}">
			 <a href="/wtapp/${role}" class="navbutton">Home</a> <a href="/wtapp/profile"
				class="navbutton">Profile</a>
			</c:if>
			<c:if test="${role=='tutor/home'||role=='advanced_tutor/home'}">
				<a class="navbutton" href="create">Create test</a>
			</c:if>
			<c:if test="${role=='admin/home'}">
				<a class="navbutton" href="/wtapp/signup">Create user</a>
			</c:if>
			<c:if test="${role=='student/home'}">
				<a class="navbutton" href="/wtapp/student/results">Results</a>
			</c:if>
			<c:if test="${account!=null}">
			<div id="overhead">
				Hello, ${account.firstName} ${account.lastName}<br> Access
				role: ${splitRole[0]}<br> <a href="/wtapp/logout">Logout</a>
			</div>
			</c:if>
		</div>
		<div id="content">
			<decorator:body />

		</div>
	</div>
</body>
</html>