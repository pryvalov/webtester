<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    	<jsp:include page="../templates/header.jsp" />
	<jsp:include page="../templates/status.jsp" />
	<c:if test="${test!=null}">
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
	</c:if>
	<div class="editor-block">
	<form action="submit" id="editor_form" method="post">
	<input type="submit" value="send"><input type="button" onclick="addFields();" value="Add"><br>
	<b>Name of test</b> <input type="text" name="name"/> <br> 
	<b>Subject </b><input type="text" name="subj"/><br>
		Question 1<input type="text" name="question1"/><br>
	</form>
	</div>
	 
 	<jsp:include page="../templates/footer.jsp" />