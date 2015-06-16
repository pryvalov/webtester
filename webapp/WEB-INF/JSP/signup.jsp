<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    	<jsp:include page="templates/header.jsp" />


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
									<td align="left"></td>
									<td align="left"><input type="submit" class="common-button" value="Submit"
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