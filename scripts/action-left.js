function fetchListFor(e){
	var id=e.toLowerCase();
	if(id.charAt(id.length-1)=="s")id=id.substring(0,id.length-1);
	//alert(id);
	for(var i=0;i<3;i++){
		if(id==mainContentHolders[i])document.getElementById(mainContentHolders[i]).style.display="block";
		else document.getElementById(mainContentHolders[i]).style.display="none";
	}
	send("l"+e);
}

var originalSubjectSet=[];

function assignFuncLeftButtons(){
    var dom = document.getElementsByTagName("subjopt")[0],dom2=document.getElementsByTagName("leftpane")[0];
    dom = dom.getElementsByTagName("button");
    dom2 = dom2.getElementsByTagName("button");
    for(var i=0;i<dom2.length;i++){
   		dom2[i].onclick=function(){
   			fetchListFor(this.innerHTML);//change this to fetchListFor(this.innerHTML) after testing
   		};
   	}

    for(var i=0;i<dom.length;i++)
    dom[i].onclick=function(){
        //Space for the function to be assigned to each button.Ex:subjopt(this);
        var temp=this.title;
        send("d "+temp);
    };

}

function subjopt(dom){
    var re = new RegExp(dom.value,"ig");
    var ls = [];
    for(var i=0;i<originalSubjectSet.length;i++){
        if(originalSubjectSet[i].match(re)!=null){
            ls.push(originalSubjectSet[i]);
        }
    }
    var target = document.getElementsByTagName("subjopt")[0];
    target.innerHTML="";
    for(var i=0;i<ls.length;i++)target.innerHTML+="<button>"+ls[i];
    assignFuncLeftButtons();
}

function listOnLeft(text){
	var dom=document.getElementsByTagName("subjopt")[0];
	dom.innerHTML="";
	var x=text.substring(text.indexOf("\n")+1);
	while(x.length>0){
		var temp=x.substring(0,x.indexOf("\n"));
		temp=temp.replace(/,/g,"\n");
		if(temp.length>10)dom.innerHTML+="<button>"+temp+"</button>";
		x=x.substring(x.indexOf("\n")+1);
		originalSubjectSet.push(temp);
	}
	assignFuncLeftButtons();
}
