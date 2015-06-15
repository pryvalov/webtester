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
	var firstName = document.forms["regform"]["fn"].value;
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
var iterator = 2;
function addFields(){
	var container = document.getElementById("editor_form").firstChild;
	var input = document.createElement("input");
	var br = document.createElement("br");
	
	input.setAttribute('type','text');
	input.setAttribute('name','question'+iterator); // set the CSS clas
	var label = document.createTextNode("Question "+iterator);
	iterator++;
	container.parentNode.appendChild(label);
	container.parentNode.appendChild(input);
	container.parentNode.appendChild(br);
	
	


	

}