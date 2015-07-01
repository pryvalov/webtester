<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${question!=null}">
<script type="text/javascript">
/* window.onload = function() {
	  console.log('window loaded');
	}; */
	setInterval(function() {
		for(var i=0; i<parseInt(${test.questionsSize}); i++){
			document.getElementById("q_form").submit();}}, ${test.time}*1000); 
		
 
</script>
</c:if>
<div style="position: absolute; top: 130px; left: 260px; padding: 3px;"
	class="quiz">
	<h3 align="center">
		Test: <b>${test.name}</b><br> Subject: <b>${test.subject}</b><br>
		By: <b>${test.author.firstName} ${test.author.lastName}</b><br>
		Created: <b>${test.created}</b><br> <b>${test.time}</b> sec. per
		question.<br>Size: ${test.questionsSize}

	</h3>
	<c:if test="${question==null}">
		<form method="POST" action="quiz">
			<input type="submit" class="common-button" value="Start test">
		</form>
	</c:if>
</div>


<div style="position: absolute; top: 350px; left: 290px;" class="quiz"
	id="quiz-question">
	<c:if test="${question!=null}">
		<form action="" method="POST" id="q_form">
			<table>
				<tr>
					<td colspan="2">${question.questionText }</td>
				</tr>
				<c:forEach items="${question.answers}" var="answer"
					varStatus="answerStatus">
					<tr>
						<td># ${answerStatus.index + 1} ${answer.answerText}</td>
						<td><input type="checkbox" name="selected"
							value="${answer.idAnswer}"></td>
					</tr>

				</c:forEach>
				<tr>
					<td>Dont know right answer.</td>
					<td><input type="checkbox" name="selected" value="-1"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" id="q_subm" class="common-button"
						value="Next"></td>
				</tr>
			</table>
		</form>
	</c:if>

	${info}
</div>
<%-- <div style="position: absolute; top: 550px; left: 290px;" class="quiz"> ${score}</div> --%>
