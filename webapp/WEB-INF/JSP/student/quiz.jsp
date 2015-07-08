<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<c:if test="${question!=null}">
	<script type="text/javascript">
	var testTime = ${test.time};
	var questions = ${test.questionsSize};
	document.addEventListener('DOMContentLoaded', function(){
		document.getElementById("timer").appendChild(document.createTextNode(testTime));
		 });
	setInterval(function() {
		
		for(var i=0; i<parseInt(questions); i++){
			document.getElementById("q_form").submit();}}, testTime*1000); 
	setInterval(function() {
		var elem = document.getElementById("timer");
		elem.removeChild(elem.firstChild);
		elem.appendChild(document.createTextNode(--testTime));}, 1000);
	
		
 
</script>
</c:if>
<div style="position: absolute; top: 130px; padding: 3px;" class="quiz">
	<h3 align="center">
		Test: <b>${test.name}</b><br> Subject: <b>${test.subject}</b><br>
		By: <b>${test.author.firstName} ${test.author.lastName}</b><br>
		Created: <b>${test.created}</b><br> <b>${test.time}</b> sec. per
		question.<br>Questions: ${test.questionsSize}

	</h3>
	<c:if test="${question==null}">
		<form method="POST" action="quiz">
			<input type="submit" class="common-button" value="Start test">
		</form>
	</c:if>
</div>


<div style="position: absolute; top: 130px; left: 340px;" class="quiz"
	id="quiz-question">
	<c:if test="${question!=null}">
		<form action="" method="POST" id="q_form">
			<table>
				<tr>
					<td>
						${fn:replace(question.questionText, newLineChar, '<br>')}</td>
						<td><div id="timer" style="background: #999; border-radius: 3px; width: 50px; float: right;"></div></td>
				</tr>
				<c:forEach items="${question.answers}" var="answer"
					varStatus="answerStatus">
					<tr>
						<td colspan="2"><div style="${answerStatus.index % 2 == 0 ? 'background: #ccc; color: #111;' : 'background: #999; color: #111;'} border-radius: 3px 3px 3px 3px;"><input type="checkbox" name="selected"
							value="${answer.idAnswer}"> ${answerStatus.index + 1}.
							${answer.answerText}</div></td>

					</tr>

				</c:forEach>
				<tr>
					<td colspan="2"><input type="checkbox" name="selected"
						value="-1"> Dont know right answer.</td>

				</tr>

				<tr>
					<td colspan="2"><input type="submit" id="q_subm"
						class="common-button" value="Next"></td>
				</tr>
			</table>
		</form>
	</c:if>

	${info}
</div>
<%-- <div style="position: absolute; top: 550px; left: 290px;" class="quiz"> ${score}</div> --%>
