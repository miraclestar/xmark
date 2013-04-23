
function del_site(siteid) {
	function callback(data) {
		alert(data);
		if (data.indexOf('login') == -1) {
			$("#" + siteid).hide();
		}
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

function logout() {
	function callback(data) {
		alert(data);
		$(".welcome")
				.html(
						"<a href='/QQLogin'><img src='http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_4.png'></a> <a href='https://api.weibo.com/oauth2/authorize?client_id=1182380896&response_type=code&redirect_uri=http://studytree.cloudfoundry.com/WeiboLogin'><img src='img/32.png' title='微博登录'></a>");
	}
	$.ajax({
		type : "POST",
		url : "Logout",
		success : callback,
		error : function(data) {
			alert("服务器异常,稍后重试下吧O(∩_∩)O~");
		}
	});

}