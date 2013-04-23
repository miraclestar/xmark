<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.miracle.myfav.base.*,java.sql.*,java.util.*,com.miracle.myfav.entity.*,com.miracle.myfav.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="js/home.js"></script>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="css/home.css" />
 <link rel="icon" type="image/x-icon" href="/xmark.ico" />
<script src="js/google.js"></script>
<title>云书签</title>
</head>
<body>

	<!-- <a href="/"><img alt="logo" src="img/logo.jpg"></a> -->
	<div class="welcome">
		<%
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String uid = user.getUid();
		%>

		<div class="logo">
			<img src="${sessionScope.user.avatar }" width=30 height=30>${sessionScope.user.name },欢迎光临~<a href="javascript:logout()">退出登录</a>
		</div>
		<div>
			<input id="newsite" type="text" placeholder="http://www.example.com" name="surl" size="50"> <input id="newtitle" type="text" placeholder="title 标题"
				name="stitle" size="14"> <span class="buttons"><button class="positive" onclick="add_site()" name="save">
					<img src="/img/apply.png" alt="记下" />记下
				</button></span>
		</div>
		<br>
		<div class="buttons">
			<span><A class="regular"
				HREF="javascript:(function(){var jsScript=document.createElement('script');var cst=document.charset;var isie6=!-[1,]&&!window.XMLHttpRequest;jsScript.setAttribute('type','text/javascript');jsScript.setAttribute('src', 'http://studytree.cloudfoundry.com/ReceiveSite?w=${sessionScope.user.whichweibo }&encrypt=<%=user.getWhichweibo().equals("weibo")? EncryptUtil.encrypt(Integer.parseInt(uid)):""%>&oid=${sessionScope.user.uid }&encode='+cst+'&isie6='+isie6+'&site='+encodeURIComponent(location.href)+'&title='+encodeURIComponent(document.title));document.getElementsByTagName('head')[0].appendChild(jsScript);})();">收藏到云</A></span>把左边这个按键拖到书签栏,遇到喜欢的网站一点就可以收藏进来了
		</div>
		<%
			} else {
		%>
		<div id="notlogin">
		<a href="/QQLogin"><img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_4.png"></a> <a
			href="https://api.weibo.com/oauth2/authorize?client_id=1182380896&response_type=code&redirect_uri=http://studytree.cloudfoundry.com/WeiboLogin"><img
			src="img/32.png" title="微博登录"></a>
			</div>
		<%
			}
		%>
	</div>
	<br>
	<c:forEach items="${sites }" var="site">
		<ul id=${site.siteId }>
			<li><a href="${site.surl }" target="_blank" title="插入时间：${site.inTime}">${site.sname }</a><span class="buttons"><a class="negative"
					href="javascript:del_site(${site.siteId})"> <img src="/img/cross.png" alt="" /> 删除
				</a></span> <!-- <span> ${site.category }</span> --></li>
		</ul>

	</c:forEach>
	
	<div id="qrcode">
		 <img src="qrcode/${param.id}.png" alt="二维码"/>
	</div>
	<div class="bottom">
		<hr>
		<a>关于云书签</a>|<a>关于我们</a>| ©2013 
	</div>


<!-- JiaThis Button BEGIN -->
<script type="text/javascript" >
var jiathis_config={
	data_track_clickback:true,
	url:"http://studytree.cloudfoundry.com/Home?id=${param.id}",
	summary:"我的云书签，一处收藏，处处查看。",
	title:"云书签 #云书签#",
	pic:"http://studytree.cloudfoundry.com/qrcode/${param.id}.png",
	showClose:true,
	hideMore:false
}
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?uid=1760423&btn=r.gif&move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->

</body>
</html>