var ctrl=[];
var elements=[];
function slideInLeft(div){
    var doc = document.getElementsByTagName(div)[0];
    var pos=0;
    while(pos<elements.length){
        if(elements[pos]==doc.tagName)break;
        pos++;
    }
    if(ctrl[pos]%2==0)doc.style.left=0;
    else doc.style.left=("-"+doc.clientWidth+"pt");
    ctrl[pos]++;
}
function test(){
    var ls = document.body.getElementsByTagName("*");
    for(var i=0;i<ls.length;i++){
        //console.log(ls[i].tagName);
        if(
            ls[i].tagName!="BUTTON"&&
            ls[i].tagName!="INPUT"
        ){elements.push(ls[i].tagName);ctrl.push(0);}
    }
    elements.sort();
}