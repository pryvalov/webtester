<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../templates/header.jsp" />
<div id="status-student" class="status">

	<table>
		<thead>
			<tr>
				<th colspan="2">Student home</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="2">Hello, ${account.firstName} ${account.lastName}</td>
			</tr>
			<tr>
			<tr>
				<td>Logged as:</td>
				<td>${account.login}</td>
			</tr>
			<tr>
				<td>
					<form action="/wtapp/logout">
						<input type="submit" value="Logout">
					</form>
				</td>
				<td></td>
			</tr>

			<tr>
				<td>
					<form action="">
						<input type="submit" value="My profile">
					</form>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>


</div>

<jsp:include page="../templates/footer.jsp" />