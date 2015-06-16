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
var iterator = 1;
function addFields(){
	var container = document.getElementById("editor_table");
	var input = document.createElement("input");
	
	input.setAttribute('type','text');
	input.setAttribute('name', 'answer-'+iterator); 
	input.setAttribute('size','40');
	var label = document.createTextNode("answer "+iterator+" ");
	var tr = document.createElement("tr");
	var cbox = document.createElement("input");
	
	cbox.setAttribute('type','checkbox');
	cbox.setAttribute('name','cbox'+iterator); 
	cbox.setAttribute('value', iterator); 
	
	iterator++;
	 var td=document.createElement('td');
	 var td2=document.createElement('td');
	 var td3=document.createElement('td');
     td.appendChild(document.createTextNode('\u0020'));
     td.appendChild(label);
     td2.appendChild(document.createTextNode('\u0020'));
     td2.appendChild(input);
     td3.appendChild(document.createTextNode('\u0020'));
     td3.appendChild(cbox);
     tr.appendChild(td);
     tr.appendChild(document.createTextNode('\u0020'));
     tr.appendChild(td2);
     tr.appendChild(document.createTextNode('\u0020'));
     tr.appendChild(td3);
     container.appendChild(tr);
     document.getElementById("removeButton").style.display = 'block';
}

function removeFields(){
	var container = document.getElementById("editor_table");
	container.removeChild(container.lastChild);
	console.log(container.childNodes.length)
	if(container.childNodes.length<3)
		document.getElementById("removeButton").style.display = 'none';
}

/*var queIterator = 1;
function addQuestion(){
	var editor = document.getElementById("test-editor");
	var question = document.getElementById("question");
	var clone=question.cloneNode(true);
	clone.setAttribute("id", queIterator);
	editor.appendChild(document.createTextNode('\u0020'));
	document.body.appendChild(clone);
	queIterator++;
	
}*/