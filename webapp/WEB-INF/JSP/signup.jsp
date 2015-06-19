<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	        message.innerHTML = "Passwords mtch!"
	        document.getElementById('subm').disabled = false;
	    }else{
	        pass2.style.backgroundColor = badColor;
	        message.style.color = badColor;
	        message.innerHTML = "No match!"
	        document.getElementById('subm').disabled = true;
	    }
	}  
	function checkFields(){
		var firstName = document.forms["regform"]["firstName"].value;
		var email = document.forms["regform"]["email"].value;
		var login = document.forms["regform"]["login"].value;
		if(firstName == "" || firstName == null) {
			//document.getElementById('subm').disabled = false;
			var message = document.getElementById('fnmessage');
			message.style.color = "#ff6666";
			message.innerHTML = "*"
			//alert("first name required!");
			return false;
			}
		if  (email=="" || email==null){
			var message = document.getElementById('emailmessage');
			message.style.color = "#ff6666";
			message.innerHTML = "*"
			return false;
			
		}
		if (login=="" || login==null){
			var message = document.getElementById('loginmessage');
			message.style.color = "#ff6666";
			message.innerHTML = "*"
			return false;
		}

	}
	</script>

<div id="signup">


	<form:form modelAttribute="signUpForm" onsubmit="return checkFields()" name="regform">
		<table id="regtable">
			<thead>
				<tr>
					<th colspan="2">Fill the registration form</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="login">Login: </label></td>
					<td align="left"><form:input path="login" id="login" /> <span
						id="loginmessage" class="confirmMessage"></span></td>
				</tr>

				<tr>
					<td><label for="email">Email: </label></td>
					<td align="left"><form:input path="email" id="email" /> <span
						id="emailmessage" class="confirmMessage"></span></td>
				</tr>


				<tr>
					<td><label for="firstName">First Name: </label></td>
					<td align="left"><form:input path="firstName" id="firstName" />
						<span id="fnmessage" class="confirmMessage"></span></td>
				</tr>
				<tr>
					<td><label for="lastName">Last Name: </label></td>
					<td align="left"><form:input path="lastName" id="lastName" /></td>
				</tr>
				<tr>
					<td><label for="middleName">Middle Name: </label></td>
					<td align="left"><form:input path="middleName" id="middleName" /></td>
				</tr>

				<tr>
					<td><label for="pass">Password: </label></td>
					<td align="left"><form:password path="password" id="pass" />
						At least 4 symbols</td>
				</tr>
				<tr>
					<td><label for="passconfirm">Confirm: </label></td>
					<td align="left"><form:password path="confirm"
							id="passconfirm" onkeyup="checkPass(); return false;" /> <span
						id="confirmMessage" class="confirmMessage"></span></td>
				</tr>


				<tr>
					<td align="left"></td>
					<td align="left"><input type="submit" class="common-button"
						value="Submit" id="subm" /></td>

				</tr>
				<tr>
					<td colspan="2">have an account? <a href="login">Login
							Here</a></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
