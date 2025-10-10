//************** SPECIF CRUD ********

window.onload=function(){
	(document.getElementById("inputId")).disabled=true; //if auto_incr
	initListeners(); 
}

function objectFromInput(){
	let numero = (document.getElementById("inputId")).value;
	if(numero=="")numero=null;
	
	let label = (document.getElementById("inputLabel")).value;
	let prix = (document.getElementById("inputPrix")).value;
	
	document.getElementById("spanMsg").innerHTML="";
	let obj = { nume : numero,
				label : label,
	            prix : parseFloat(prix)
	            };
	return obj;
}

function displayObject(obj){
	(document.getElementById("inputId")).value=obj.num;
	(document.getElementById("inputLabel")).value=obj.label;
	(document.getElementById("inputPrix")).value=obj.prix;
}

function insertRowCells(row,obj){
	(row.insertCell(0)).innerHTML = obj.num;
	(row.insertCell(1)).innerHTML = obj.label;
	(row.insertCell(2)).innerHTML = obj.prix;
}


function blankObject(){
	return {num:"" , label: "" , prix : 0 };	
}

function getWsBaseUrl(){
	return "./rest/api-produits/v1/produits";
}

//obj = object with values to check
//action = "add" or "update" or ...
function canDoAction(action,obj){
	ok=true; //by default
	if(obj.label==null || obj.label == "")
	  ok=false;
	if(obj.prix==null)
	  ok=false;
	return ok;
}
