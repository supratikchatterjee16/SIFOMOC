var listType = '';
function create(str){}
function list(str){
	var ctr=0;
	var id='', title='';
	var dom = document.getElementsByTagName('subjopt')[0];
	dom.innerHTML='';
	alert('Listing triggered');
	listType = str.substring(0,str.indexOf(','));
	str = str.substring(str.indexOf(',')+1);
	while(str.indexOf(',')!=-1){
		id = str.substring(0,str.indexOf(','));
		str = str.substring(str.indexOf(',')+1);
		title = str.substring(0,str.indexOf(','));
		str = str.substring(str.indexOf(',')+1);
		dom.innerHTML += '<button title=\''+id+'\'>'+title;
	}
	assignFuncLeftButtons();
}
function detail(str){
	
}
function update(str){}
function authenticate(str){}
function triggerAction(e){
	console.log(e);
	var i=0;
	switch(e.charAt(0)){
		case'a':authenticate();break;
		case 'c':create(e.substring(1));break;
		case 'd':detail(e.substring(1));break;
		case 'l':list(e.substring(1));break;
		case 'u':update(e.substring(1));break;
		default:alert("Garbage returned from server : "+e);
	}
}
