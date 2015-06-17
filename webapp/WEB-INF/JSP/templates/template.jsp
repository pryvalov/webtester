<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Web tester ${role}</title>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/normalize.css?v=${CSS_JS_VERSION}" />
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/styles.css?v=${CSS_JS_VERSION}" />
	<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/styles-plus.css?v=${CSS_JS_VERSION}" />
<%-- <link rel="stylesheet" href="${context}/resources/css/screen.css" type="text/css" media="screen" title="default" /> --%>
<spring:url value="/resources/JS/passmatch.js" var="script" />
<script type="text/javascript" src="${script}"></script>
</head>

<body>

	<div id="wrap">
		<div id="headdiv">
		<a href="/wtapp/login">
		<img class="inline-class" src="${context}/resources/images/wtlogo1.png" style="{float: left; position: absolute; top: 0;}" alt="logo"/>
		</a>
		<a href="/wtapp/${role}" class="navbutton">Home</a><a href="" class="navbutton">Profile</a>
		<div id="overhead">Open source online testing platform.</div>
		</div>
		<div id="content">
		<c:if test=""></c:if>
		<c:if test="${account!=null}"><jsp:include page="../templates/status.jsp" /></c:if>
		<decorator:body />
<!-- </div> -->

</div>
<!-- <div id="footdiv">Copyright &copy;</div> -->

</div>


</body>

</html>