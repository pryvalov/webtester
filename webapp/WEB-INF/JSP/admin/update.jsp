<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../templates/header.jsp" />
<jsp:include page="../templates/status.jsp" />
		<div id="updateuser">
		<form action="update" method="post" >
		<table>
		<tr>
		<td>Login</td>
		<td><input type="text" name="login" value="${user.login}"></td>
		</tr>
		<tr>
		<td>Email</td>
		<td><input type="text" name="email" value="${user.email}"></td>
		</tr>
		<tr>
		<td>First name</td>
		<td><input type="text" name="firstName" value="${user.firstName}"></td>
		</tr>
		<tr>
		<td>Last name</td>
		<td><input type="text" name="lastName" value="${user.lastName}"></td>
		</tr>
		<tr>
		<td>Middle name</td>
		<td><input type="text" name="middleName" value="${user.middleName}"></td>
		</tr>
		<c:forEach items="${user.accountRoles}" var="role" varStatus="status">
		<c:if test="${role.roleName == 'admin'}"> <c:set var="admin" scope="request" value="admin_val"/> </c:if>
		<c:if test="${role.roleName == 'student'}"> <c:set var="student" scope="request" value="student_val"/> </c:if>
		<c:if test="${role.roleName == 'tutor'}"> <c:set var="tutor" scope="request" value="tutor_val"/> </c:if>
		<c:if test="${role.roleName == 'advanced tutor'}"> <c:set var="advanced_tutor" scope="request" value="advanced_tutor_val"/> </c:if>
		</c:forEach>
		<tr>
		<td><input type="checkbox" name="adminRole" value="0"  
    	<c:if test="${admin == 'admin_val'}">checked="checked"</c:if> /></td>
		<td> admin</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="studentRole" value="1"  
    	<c:if test="${student == 'student_val'}">checked="checked"</c:if> /></td>
		<td> student</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="tutorRole" value="2"  
    	<c:if test="${tutor == 'tutor_val'}">checked="checked"</c:if> /></td>
		<td> tutor</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="advancedTutorRole" value="3"  
    	<c:if test="${advanced_tutor == 'advanced_tutor_val'}">checked="checked"</c:if> /></td>
		<td> advanced tutor</td>
		</tr>
		
		<tr>
		<td></td>
		<td><input type="submit" class="common-button" value="update"></td>
		</tr>
		
		
		
		
		</table>
		</form>
		</div>
		
		

<jsp:include page="../templates/footer.jsp" />