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
        message.innerHTML = "Passwords Match!"
        document.getElementById('subm').disabled = false;
    }else{
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
        document.getElementById('subm').disabled = true;
    }
}  
function checkFields(){
	var firstName = document.forms["regform"]["fn"].value;
	var email = document.forms["regform"]["email"].value;
	var login = document.forms["regform"]["login"].value;
	if(firstName == "" || firstName == null) {
		//document.getElementById('subm').disabled = false;
		var message = document.getElementById('fnmessage');
		message.style.color = "#ff6666";
		message.innerHTML = "First name required!"
		//alert("first name required!");
		return false;
		}
	if  (email=="" || email==null){
		alert("email required!");
		return false;
		
	}
	if (login=="" || login==null){
		alert("login required!");
		return false;
	}
	console.log("%s okay", firstName);
	

}