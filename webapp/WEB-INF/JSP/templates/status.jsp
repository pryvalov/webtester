	<div class="status">

		<table>
			<thead>
				<tr>
					<th colspan="2">${role}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">Hello, ${account.firstName}
						${account.lastName}</td>
				</tr>
				<tr>
				<tr>
					<td>Logged as:</td>
					<td>${account.login}</td>
				</tr>
				<tr>
					<td>
						<!-- <form action="/wtapp/logout">
							<input type="submit" value="Logout">
						</form> -->
						<a href="/wtapp/logout" class="common-button">Logout</a>
					</td>
					<td>
						<!-- <form action="">
							<input type="submit" value="My profile">
						</form> -->
						<a href="" class="common-button">My profile</a>
					</td>
				</tr>

			</tbody>
		</table>
	</div>