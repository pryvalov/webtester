
var iterator = 1;
function addFields(){
	var container = document.getElementById("editor_table");
	var input = document.createElement("input");
	
	input.setAttribute('type','text');
	input.setAttribute('name', 'answer-'+iterator); 
	input.setAttribute('size','45');
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

var iterator2 = 1;
function addEditorField(){
	var container = document.getElementById("editor_table");
	var input = document.createElement("input");
	
	input.setAttribute('type','text');
	input.setAttribute('name', 'answer-'+iterator2); 
	input.setAttribute('size','45');
	var tr = document.createElement("tr");
	tr.setAttribute('id', iterator2)
	var cbox = document.createElement("input");

	cbox.setAttribute('type','checkbox');
	cbox.setAttribute('name','cbox'+iterator2); 
	cbox.setAttribute('value', iterator2); 
	
	var removeButton = document.createElement("a");
	
	removeButton.setAttribute('class', 'common-button');
	removeButton.setAttribute('class', 'common-button');
	removeButton.setAttribute('onclick', 'removeRow('+iterator2+')');
	removeButton.appendChild(document.createTextNode('\Remove'));
	
	 var td=document.createElement('td');
	 var td2=document.createElement('td');
	 var td3=document.createElement('td');
     td2.appendChild(document.createTextNode('\u0020'));
     td2.appendChild(input);
     td3.appendChild(document.createTextNode('\u0020'));
     td3.appendChild(cbox);
     td.appendChild(document.createTextNode('\u0020'));
     td.appendChild(removeButton);
     tr.appendChild(document.createTextNode('\u0020'));
     tr.appendChild(td2);
     tr.appendChild(document.createTextNode('\u0020'));
     tr.appendChild(td3);
     tr.appendChild(td);
     container.appendChild(tr);
     iterator2++;
}

function removeFields(){
	var container = document.getElementById("editor_table");
	container.removeChild(container.lastChild);
	if(container.childNodes.length<3)
		document.getElementById("removeButton").style.display = 'none';
}

function removeRow(row_number){
	console.log(row_number)
	document.getElementById(row_number).parentNode.removeChild(document.getElementById(row_number));
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