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
<spring:url value="/resources/JS/scripts.js" var="script" />
<script type="text/javascript" src="${script}"></script>
</head>

<body>

	<div id="wrap">
		<div id="headdiv">
		<a href="/wtapp/login">
		<img class="inline-class" src="${context}/resources/images/wtlogo3.png" style="{float: left; position: absolute; top: 0;}" alt="logo"/>
		</a>
		<a href="/wtapp/${role}" class="navbutton">Home</a>
		<a href="" class="navbutton">Profile</a>
		<c:if test="${role=='tutor/home'}"><a class="navbutton" href="create">Create test</a></c:if>
		<c:if test="${role=='admin/home'}"><a class="navbutton" href="/wtapp/signup">Create user</a></c:if>
		<div id="overhead">Open source online testing platform.</div>
		</div>
		<div id="content">
		<c:if test=""></c:if>
		<c:if test="${account!=null}">	<div class="status">

		<table>
			<thead>
				<tr>
					<th colspan="2">${role}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">Hello, ${account.firstName}
						${account.lastName}</td>
				</tr>
				<tr>
				<tr>
					<td>Logged as:</td>
					<td>${account.login}</td>
				</tr>
				<tr>
					<td>
						<!-- <form action="/wtapp/logout">
							<input type="submit" value="Logout">
						</form> -->
						<a href="/wtapp/logout" class="common-button">Logout</a>
					</td>
					<td>
						<!-- <form action="">
							<input type="submit" value="My profile">
						</form> -->
						<a href="" class="common-button">My profile</a>
					</td>
				</tr>

			</tbody>
		</table>
	</div></c:if>
		<decorator:body />
<!-- </div> -->

</div>
<!-- <div id="footdiv">Copyright &copy;</div> -->

</div>


</body>

</html>