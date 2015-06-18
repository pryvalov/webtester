<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!--           STUDENT HOME          STUDENT HOME                      STUDENT HOME                        -->





<div id="table-content">
	<table id="tutor-table" class="common-table">
				<tr>
					<th class="table-header-check">id</th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">Name</a></th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">Subject</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Author</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Questions</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Created</a></th>
						<th class="table-header-repeat line-left"><a
						href="">Time p question</a></th>
						<th class="table-header-repeat line-left"><a
						href=""></a></th>
					
						
					
				</tr>
				<c:forEach items="${tests}" var="test" varStatus="status">
					<c:if test="${test.active==true}">
					<tr>
						<td>${test.idTest}	</td>
						<td>${test.name}</td>
						<td>${test.subject}</td>
						<td>${test.author.firstName} ${test.author.lastName}</td>
						<td>N/A</td>
						<td>${test.created}</td>
						<td>${test.time} sec.</td>
						<td><a class="common-button" href="load?testId=${test.idTest}&print=0">Start quiz</a> 
						<a class="common-button" href="load?testId=${test.idTest}&print=1">Print</a></td>
						
						</tr>
						</c:if>
						
				</c:forEach>

			</table>

			
		


	</div>
		
