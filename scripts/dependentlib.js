
function send(str){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           // Action to be performed when the document is read;
           triggerAction(xhttp.response); // Is in action.js
        }
    };
    xhttp.open("POST", window.location, true);
    xhttp.send(str);
}
