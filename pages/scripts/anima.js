var ctrl=0;
function slideInLeft(div){
    var doc = document.getElementById("leftpane");
    alert(doc.clientWidth);
    if(ctrl%2==0)doc.style.left+=doc.clientWidth;
    else doc.style.left-=doc.clientWidth;
    ctrl++;
}
