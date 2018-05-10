var content=["","",""];
function redundant(){
	for(var i =0;i<3;i++){
		content[i]=document.getElementById(mainContentHolders[i]).innerHTML;
		//document.getElementById(mainContentHolders[i]).innerHTML="";
		document.getElementById(mainContentHolders[i]).style.display="none";
	}
}
