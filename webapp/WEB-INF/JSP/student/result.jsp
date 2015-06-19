<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%=request.getContextPath() %>

<div id="quiz-result">
<table border="1">
<tr>
<th>Date</th>
<th>Test name</th>
<th>Result</th>


</tr>
<c:forEach items="${results}" var="result">
<tr>

<td>${result.date}</td>
<td>${result.test.name}</td>
<td> ${result.score}</td>


</tr>





</c:forEach>
</table>
</div>