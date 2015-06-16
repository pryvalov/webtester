<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    	<jsp:include page="../templates/header.jsp" />
	<jsp:include page="../templates/status.jsp" />

	<div class="inline-class" id="test-preview">
	${test.idTest}<br>
	${test.author.firstName} ${test.author.lastName}<br>
	${test.subject}<br>
	${test.name}<br>
	${test.created}<br>
	${test.time}<br>
	<c:forEach items="${test.questions}" var="question">
	<div class="question">
		${question.questionText}<br>
		<c:forEach items="${question.answers}" var="answer">
		${answer.answerText}, ${answer.correct}<br>
		</c:forEach>
		</div>
	</c:forEach>
	</div>


	<div id="test-editor" class="inline-class">
	<form action="save" id="editor_form"  method="post">
	<div class="editor-header">
	<table>
	<tr>
	<td><b>Name of test</b></td>
	<td><input type="text" name="name" size="40" value="${test.name}"/></td>
	</tr>
	<tr>
	<td><b>Subject </b></td>
	<td><input type="text" name="subj" size="40" value="${test.subject}"/></td>
	</tr>
	<tr>
	<td><b>Time per question </b></td>
	<td><input type="text" name="time" size="5" value="${test.time}"/> </td>
	</tr>
	<tr>
	<td> </td>
	<td><input type="submit" class="common-button" style="float: right;" value="Save"></td>
	</tr>
	
	</table>
	</div>
	</form>
	<form action="add" id="editor_form"  method="post">
	<div class="editor-block" id="question">
	<table id="editor_table">
	<tr>
	<td><a class="common-button" style="float: left;" onclick="addFields();"><span style="color: lime; font-size: 28px;">+</span></a></td>
	<td>
	<a class="common-button" id="removeButton" hidden=true style="float: left; display: none;" onclick="removeFields();"><span style="color: red; font-size: 28px;">-</span></a>

	<input type="submit" class="common-button" style="float: right;" value="Add"/></td>
	<td></td>
	</tr>

	<tr>
	<td>Question</td>
	<td><input type="text" name="question" size="40"/> </td>
	<td></td>
	</tr>

	</table>
	</div>
	</form>
	
	</div>

 	<jsp:include page="../templates/footer.jsp" />