var ctr=0;
var originalSubjectSet=[];
function assignFuncLeftButtons(){
    var dom = document.getElementsByTagName("subjopt")[0];
    dom = dom.getElementsByTagName("button");
    for(var i=0;i<dom.length;i++)
    dom[i].onclick=function(){
        //Space for the function to be assigned to each button.Ex:subjopt(this);
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
function populateSubjectSet(){
    //Provision to get initial subject list from server. The following line is a temporary testing solution
    var elems=document.getElementsByTagName("subjopt")[0].getElementsByTagName("button");
    for(var i=0;i<elems.length;i++)originalSubjectSet.push(elems[i].innerHTML);
}
