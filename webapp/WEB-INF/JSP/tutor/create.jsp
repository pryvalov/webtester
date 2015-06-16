<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../templates/header.jsp" />
<div id="create-form-wrapper">
<jsp:include page="../templates/status.jsp" />

<form action="create" id="editor_form" method="post">
	<div id="create-form" class="inline-class">
		<table>
			
			<tr>
				<td><b>Name of test</b></td>
				<td><input type="text" name="name" size="40" /></td>
			</tr>
			<tr>
				<td><b>Subject </b></td>
				<td><input type="text" name="subj" size="40" /></td>
			</tr>
			
			<tr>
				<td><b>Time per question </b></td>
				<td><input type="text" name="time" size="3" /> <input type="submit" class="common-button" value="Create" style="float: right;"></td>
			</tr>
			

		</table>
	</div>
</form>
</div>



<jsp:include page="../templates/footer.jsp" />