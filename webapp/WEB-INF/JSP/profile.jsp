<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function checkPass()
	{
	    var pass1 = document.getElementById('pass');
	    var pass2 = document.getElementById('passconfirm');
	    var message = document.getElementById('confirmMessage');
	    var goodColor = "#66cc66";
	    var badColor = "#ff6666";
	    if(pass1.value == pass2.value){
	        pass2.style.backgroundColor = goodColor;
	        message.style.color = goodColor;
	        message.innerHTML = "Passwords match!"
	        document.getElementById('subm').disabled = false;
	    }else{
	        pass2.style.backgroundColor = badColor;
	        message.style.color = badColor;
	        message.innerHTML = "No match!"
	        document.getElementById('subm').disabled = true;
	    }
	}
</script>
<div style="position: absolute; top: 130px;">
	<table>
		<tr>
			<td>Login:</td>
			<td>${account.login}</td>
		</tr>

		<tr>
			<td>Email:</td>
			<td>${account.email}</td>
		</tr>

		<tr>
			<td>First name:</td>
			<td>${account.firstName}</td>
		</tr>

		<tr>
			<td>Last name:</td>
			<td>${account.lastName}</td>
		</tr>


		<tr>
			<td>Middle name:</td>
			<td>${account.middleName}</td>
		</tr>
		<tr>
			<td>Joined:</td>
			<td>${account.created}</td>
		</tr>

		<tr>
			<td colspan="2"><a class="common-button" href="profile?edit=true">Edit</a></td>
		</tr>

	</table>
	

</div>

<c:if test="${edit!=null}"><div id="profile-update" >
		<form action="update" method="post" >
		<table>
		<tr>
		<td colspan="2">${exception_text}</td>
		</tr>
		<tr>
		<td>Login</td>
		<td><input type="text" name="login" value="${account.login}"></td>
		</tr>
		<tr>
		<td>Email</td>
		<td><input type="text" name="email" value="${account.email}"></td>
		</tr>
		<tr>
		<td>First name</td>
		<td><input type="text" name="firstName" value="${account.firstName}"></td>
		</tr>
		<tr>
		<td>Last name</td>
		<td><input type="text" name="lastName" value="${account.lastName}"></td>
		</tr>
		<tr>
		<td>Middle name</td>
		<td><input type="text" name="middleName" value="${account.middleName}"></td>
		</tr>
		<tr>
		<td>Password</td>
		<td><input type="password" name="password" id="pass"></td>
		</tr>
		<tr>
		<td>Confirm</td>
		<td><input type="password" name="confirm" id="passconfirm" onkeyup="checkPass(); return false;" /> <span
						id="confirmMessage" class="confirmMessage"></span></td>
		</tr>
	
		
		<tr>
		<td></td>
		<td><input type="submit" id="subm" class="common-button" value="update"></td>
		</tr>
		
		
		
		
		</table>
		</form>
</div></c:if>