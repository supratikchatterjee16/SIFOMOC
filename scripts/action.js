function resolveFetch(text){
	var x= text.charAt(0);
	switch(x){
		case 'L':listOnLeft(text.substring(1));break;
		case 'D':formattedOutput(text.substring(1));break;
		default:alert(text);
	}	
}

function resolveUpdate(text){}
function triggerAction(e){
	//console.log(e);
	var i=0;
	switch(e.charAt(0)){
		case 'F':resolveFetch(e.substring(1));break;
		case 'U':resolveUpdate(e.substring(1));break;
		default:alert("Garbage returned from server");
	}
}
