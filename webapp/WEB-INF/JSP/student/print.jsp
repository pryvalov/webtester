<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
yoyo
<div id="student-print">
<table>
<tr>
<td></td>
<td>${test.name }</td>
</tr>
<tr>
<td></td>
<td>${test.subject }</td>
</tr>
<tr>
<td></td>
<td>By ${test.author.firstName} ${test.author.lastName}.</td>
</tr>
<c:forEach items="${test.questions}" var="question" varStatus="status">
<tr>
<td colspan="2">------------------------------</td>

</tr>
<tr>
<td></td>
<td>   ${question.questionText }</td>
</tr>
<c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
<tr>
<td># ${answerStatus.index + 1}</td>
<td>    ${answer.answerText}</td>
</tr>
</c:forEach>
<tr>
<td colspan="2">------------------------------</td>

</tr>
</c:forEach>

</table>
</div>