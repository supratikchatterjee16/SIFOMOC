var promptActive = false, backup='',password = '';

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

function customPrompt(type,str){

  if(!promptActive){
    backup = document.body.innerHTML;
    document.body.innerHTML='<center style=\"padding-top:30vh;padding-left:40vw;\">'+
                            str+'<input id=\'auth\' type=\''+type+'\'></input><br><br>'+
                            '<button onclick=customPrompt(\'submit\',\'\')>Submit</button>'+
                            '<button onclick=customPrompt(\'cancel\',\'\')>Cancel</button>'+
                            '</center>';
    promptActive = true;
  }
  else{
    if(type=='submit'){
      password = document.getElementById('auth').value;
      alert('password = '+ password);
    }
    document.body.innerHTML = backup;
    promptActive = false;
  }
}
