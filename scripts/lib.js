String.prototype.replaceAt=function(index, replacement) {
    return this.substr(0, index) + replacement+ this.substr(index + replacement.length);
}
function initKeys(){
    document.onkeydown = function(evt) {
        evt = evt || window.event;
        if (evt.keyCode == 27) {
            closeAll();
        }
        if(evt.ctrlKey){
            switch(evt.keyCode){
                case 73:;
                case 116:break;
                default:evt.preventDefault();
            }
            switch(evt.keyCode){
                case 82:window.location.reload();
                case 80:console.log("Printing");break;
                case 83:console.log("Saving");break;
                default:console.log(evt.keyCode);
            }
        }
    };
}
function send(str){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           // Action to be performed when the document is read;
           triggerAction(xhttp.response);
        }
    };
    xhttp.open("POST", window.location, true);
    xhttp.send(str);
}
