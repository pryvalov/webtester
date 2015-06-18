
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<!--  start table-content  -->
	<div class="inline-class" id="admin-notifications" ></div>
	<div id="table-content" class="margin-top-15">
	<table class="common-table" id="admin-table">
				<tr>
					<th class="table-header-check">id</th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="home?sort=login">Login</a></th>
					<th class="table-header-repeat line-left minwidth-1"><a
						href="home?sort=email">Email</a></th>
					<th class="table-header-repeat line-left"><a
						href="home?sort=firstName">First name</a></th>
					<th class="table-header-repeat line-left"><a
						href="home?sort=lastName">Last Name</a></th>
					<th class="table-header-repeat line-left"><a
						href="home?sort=middleName">MN</a></th>
					<th class="table-header-repeat line-left"><a
						href="home?sort=created">Joined</a></th>
					<th class="table-header-repeat line-left"><a
						href="home?sort=updated">Updated</a></th>
					<th class="table-header-repeat line-left"><a href="">Active</a></th>
					<th class="table-header-repeat line-left"><a href="">Roles</a></th>
					<th class="table-header-options line-left"><a href="">Options</a></th>
				</tr>
				<c:forEach items="${accounts}" var="account" varStatus="status">
					<tr>
						<td>
							${account.accountId} 
						</td>
						<td>${account.login}</td>
						<td>${account.email}</td>
						<td>${account.firstName}</td>
						<td>${account.lastName}</td>
						<td>${account.middleName}</td>
						<td>${account.created}</td>
						<td>${account.updated}</td>
						<td>${account.active}</td>
						<td><c:forEach items="${account.accountRoles}" var="role">
					${role.roleName},&nbsp;
					</c:forEach></td>
						<td class="options-width"><a
							href="action?action=update&login=${account.login}" title="Update"
							class="icon-3 info-tooltip"></a> <a
							href="action?action=delete&login=${account.login}" title="Delete"
							class="icon-2 info-tooltip"></a> <a
							href="action?action=deactivate&login=${account.login}"
							title="Deactivate" class="icon-1 info-tooltip"></a> <!-- <a href="" title="Edit" class="icon-4 info-tooltip"></a> -->
							<a href="action?action=activate&login=${account.login}"
							title="Activate" class="icon-5 info-tooltip"></a></td>
					</tr>
				</c:forEach>

			</table>

			
			


	</div>
		
	

