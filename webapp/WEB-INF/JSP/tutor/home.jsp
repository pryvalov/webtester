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
				<th colspan="2">Tutor home</th>
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






<div id="table-content">
	<table id="product-table">
				<tr>
					<th class="table-header-check">*</th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">id</a></th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">Name</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Subject</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Created</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Active</a></th>
					<th class="table-header-options line-left"><a href="">Options</a></th>
				</tr>
				<c:forEach items="${tests}" var="test" varStatus="status">
					<tr>
						<td>
						</td>
						<td>${test.idTest}</td>
						<td>${test.name}</td>
						<td>${test.subject}</td>
						<td>${test.created}</td>
						<td>${test.active}</td>

						<td class="options-width"><a
							href="" title="Update"
							class="icon-3 info-tooltip"></a> <a
							href="" title="Delete"
							class="icon-2 info-tooltip"></a> <a
							href=""
							title="Deactivate" class="icon-1 info-tooltip"></a>
							<a href=""
							title="Activate" class="icon-5 info-tooltip"></a></td>
					</tr>
				</c:forEach>

			</table>

			


	</div>
		<div class="mybutton">
			<a href="edit">Create test</a>
		</div>

 	<jsp:include page="../templates/footer.jsp" />