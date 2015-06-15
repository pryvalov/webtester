<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="ua.pri.ent.Account"%>
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
<spring:url value="/resources/JS/passmatch.js" var="script" />
<script type="text/javascript" src="${script}"></script>
</head>

<body class="style1">


	<div id="wrap">
		<div id="headdiv">

			<% if(session.getAttribute("account")!=null){
		Account a = (Account)session.getAttribute("account");
	out.print("Hello, "+a.getFirstName()+" "+a.getLastName()
	/* "<br>Your mail: "+a.getEmail()+ */
	+"<br><br><br>Logged as: "+a.getLogin()+ " <a href=\"/wtapp/logout\"> Log out </a> "
	/* +"<br>You joined since: "+a.getCreated() */);
	}else{
		out.print("<br><br><br><br>");
	}
	%>
		</div>
		<div id="content">

			<section class="main">



				<div id="signup">
					<form name="regform" method="post" action="signup"
						onsubmit="return checkFields()">
						<table id="regtable">
							<thead>
								<tr>
									<th colspan="2">Fill the registration form</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>First Name</td>
									<td align="left"><input type="text" name="firstName"
										value="" id="fn" /> <span id="fnmessage"
										class="confirmMessage"></span></td>
								</tr>
								<tr>
									<td>Last Name</td>
									<td align="left"><input type="text" name="lastName"
										value="" /></td>
								</tr>
								<tr>
									<td>Middle Name</td>
									<td align="left"><input type="text" name="middleName"
										value="" /></td>
								</tr>
								<tr>
									<td>Email</td>
									<td align="left"><input type="text" name="email" value=""
										id="email" /> <span id="emailmessage" class="confirmMessage"></span>
									</td>
								</tr>
								<tr>
									<td>Login</td>
									<td align="left"><input type="text" name="login" value=""
										id="login" /> <span id="loginmessage" class="confirmMessage"></span>
									</td>
								</tr>
								<tr>
									<td>Password</td>
									<td align="left"><input type="password" name="password"
										id="pass" /> At least 4 symbols</td>
								</tr>
								<tr>
									<td>Confirm</td>
									<td align="left"><input type="password"
										name="passwordConfirm" id="passconfirm"
										onkeyup="checkPass(); return false;" /> <span
										id="confirmMessage" class="confirmMessage"></span></td>
								</tr>


								<tr>
									<td align="left"><input type="reset" value="Reset" /></td>
									<td align="left"><input type="submit" value="Submit"
										id="subm" /></td>

								</tr>
								<tr>
									<td colspan="2">have an account? <a href="login">Login
											Here</a></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<jsp:include page="templates/footer.jsp" />