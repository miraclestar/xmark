function del_site(siteid) {
	function callback(data) {
		alert(data);
		$("#" + siteid).hide();
	}
	$.ajax({
		type : "POST",
		url : "DeleteSite",
		data : "siteid=" + siteid,
		success : callback,
		error : function(data) {
			alert("服务器异常,稍后重试下吧O(∩_∩)O~");
		}
	});
}

function add_site() {
	var s = $("#newsite").val();
	var st = $("#newtitle").val();
	function callback(data) {
		alert(data);
	}

	$.ajax({
		type : "POST",
		url : "AddSite",
		data : "surl=" + s + "&stitle=" + st,
		success : callback,
		error : function(data) {
			alert("服务器异常,稍后重试下吧O(∩_∩)O~");
		}
	});
}
