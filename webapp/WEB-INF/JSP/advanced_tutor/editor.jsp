<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="inline-class" id="test-preview">
	Test: <b>${test.name}</b><br> Subject: <b>${test.subject}</b><br>
	By: <b>${test.author.firstName} ${test.author.lastName}</b><br>
	Created: <b>${test.created}</b><br> <b>${test.time}</b> sec. per
	question.<br>
	<c:forEach items="${test.questions}" var="question">
		<div class="question">
			<div class="question-body">
				<b>${question.questionText} </b>
			</div>
			<table id="answer-tabe">
				<c:forEach items="${question.answers}" var="answer">
					<tr>
						<td>
							<div class="answer">${answer.correct ? '<font style="color: #93DF5E;">' : ''}
								${answer.answerText} ${answer.correct ? '</font>' : ''}</div>
						</td>
						<td></td>
						<td>&nbsp;</td>
					</tr>


				</c:forEach>
				<tr>
					<td colspan="2"><a class="common-button"
						style="float: right; margin-top: 10px;"
						href="delete?idQuestion=${question.idQuestion}">Delete</a> <a
						class="common-button" style="float: left; margin-top: 10px;"
						href="edit?action=update&testId=${test.idTest}&idQuestion=${question.idQuestion}">Edit<!-- <span class="div-link"></span> --></a>

					</td>
					<td></td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>


<div id="test-editor" class="inline-class">
	<form action="save" id="editor_form" method="post">
		<div class="editor-header">
			<table>
				<tr>
					<td><b>Name of test</b></td>
					<td><input type="text" name="name" size="45"
						value="${test.name}" /></td>
				</tr>
				<tr>
					<td><b>Subject </b></td>
					<td><input type="text" name="subj" size="45"
						value="${test.subject}" /></td>
				</tr>
				<tr>
					<td><b>Time per question </b></td>
					<td><input type="text" name="time" size="3"
						value="${test.time}" /> sec. <input type="submit"
						class="common-button" style="float: right;" value="Save test">
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>

			</table>
		</div>
	</form>
	<form action="add" id="editor_form" method="post">
		<c:if test="${question==null}">
			<div class="editor-block" id="question">
				<table id="editor_table">
					<tr>
						<td><a class="common-button"
							style="float: left; border: inset; border-color: #93DF5E; background: none; padding-bottom: 3px;"
							onclick="addFields();"> <span
								style="color: #93DF5E; font-size: 28px;">+</span></a></td>
						<td><a class="common-button" id="removeButton" hidden=true
							style="float: left; border: inset; border-color: #EF5252; padding-bottom: 5px; background: none; display: none;"
							onclick="removeFields();"><span
								style="color: #EF5252; font-size: 28px;">-</span></a> <input
							type="submit" class="common-button" style="float: right;"
							value="Add" /></td>
						<td></td>
					</tr>

					<tr>
						<td>Question</td>
						<td><input type="text" name="question" size="45" /></td>
						<td></td>
					</tr>

				</table>
			</div>
		</c:if>

	</form>
	<c:if test="${question!=null}">
		<form action="savequestion" method="post">
			<div class="editor-block" id="question">
				<table id="editor_table">
					<tr>
						<td><a class="common-button"
							style="float: left; border: inset; border-color: #93DF5E; background: none; padding-bottom: 3px;"
							onclick="addEditorField()"><span
								style="color: #93DF5E; font-size: 28px;">+</span></a></td>
						<td></td>
						<td><input type="submit" class="common-button"
							style="float: right;" value="Save" /></td>
					</tr>

					<tr>
						<td><input type="text" name="question" size="45"
							value="${question.questionText}" /></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach items="${question.answers}" var="answer">

						<tr id="${answer.idAnswer}">
							<td><input type="text" name="answer${answer.idAnswer}"
								size="45" value="${answer.answerText}" /></td>
							<td><input type="checkbox" name="cbox${answer.idAnswer}"
								value="checked${answer.idAnswer}"
								<c:if test="${answer.correct }">checked="checked"</c:if> /></td>
							<td><a class="common-button" style="float: right;"
								onclick="removeRow(${answer.idAnswer})">Remove</a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</form>
	</c:if>

</div>
