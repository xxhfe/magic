function showmessage(text,type){
	$(".message_popup").text(text);
	$(".message_popup").addClass(type);
	$(".message_popup").css({top:"-10em"}).animate({top: "1em" }, 300);
	setTimeout(function(){
		$(".message_popup").removeClass(type);
	},3000);
}

$(function(){
	parent.document.getElementById('LoadDiv').style.display='none';
	parent.document.getElementById('topdiv').style.background='#fff';
})

