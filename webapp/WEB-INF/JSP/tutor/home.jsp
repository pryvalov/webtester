<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>








<div id="table-content">
	<table id="tutor-table" class="common-table">
				<tr>
					<th class="table-header-check">id</th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">Name</a></th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="">Subject</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Questions</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Created</a></th>
						<th class="table-header-repeat line-left"><a
						href="">Time p question</a></th>
					<th class="table-header-repeat line-left"><a
						href="">Active</a></th>
					<th class="table-header-options line-left"><a href="">Options</a></th>
				</tr>
				<c:forEach items="${tests}" var="test" varStatus="status">
					<tr>
						<td>${test.idTest}	</td>
						<td>${test.name}</td>
						<td>${test.subject}</td>
						<td>N/A</td>
						<td>${test.created}</td>
						<td>${test.time} sec.</td>
						<td>${test.active}</td>

						<td class="options-width"><a
							href="edit?action=update&testId=${test.idTest}" title="Update"
							class="icon-3 info-tooltip"></a> <a
							href="edit?action=delete&testId=${test.idTest}" title="Delete"
							class="icon-2 info-tooltip"></a> <a
							href="edit?action=deactivate&testId=${test.idTest}"
							title="Deactivate" class="icon-1 info-tooltip"></a>
							<a href="edit?action=activate&testId=${test.idTest}"
							title="Activate" class="icon-5 info-tooltip"></a></td>
					</tr>
				</c:forEach>

			</table>

			
		


	</div>
		

