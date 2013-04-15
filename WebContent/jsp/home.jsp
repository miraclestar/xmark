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
<script src="js/google.js"></script>
<title>云书签</title>
</head>
<body>
	<!-- JiaThis Button BEGIN -->
	<div class="jiathis_style_32x32">
		<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a> <a class="jiathis_button_tqq"></a> <a class="jiathis_button_renren"></a> <a
			href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a> <a class="jiathis_counter_style"></a>
	</div>
	<script type="text/javascript">
		var jiathis_config = {
			url : "http://studytree.cloudfoundry.com/Home?id=1768291940",
			summary : "这是我的云书签，想要自己的云书签吗",
			title : " #云书签#",
			pic : "http://studytree.cloudfoundry.com/qrcode/1768291940.png",
			hideMore : false
		}
	</script>
	
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1345429709335132" charset="utf-8"></script>
	<!-- JiaThis Button END -->
	<a href="/"><img alt="logo" src="img/logo.jpg"></a>
	<div class="welcome">
		<%
			User user = (User) session.getAttribute("user");
			String encryptid = "-1";
			if (user != null) {
				String uid = user.getUid();
				String name = user.getName();
				if (name != null && !name.equals("")) {
		%>

		<div class="logo">
			<img src="${sessionScope.user.avatar }" width=30 height=30>${sessionScope.user.name },欢迎光临~
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
				HREF="javascript:(function(){var jsScript=document.createElement('script');var cst=document.charset;var isie6=!-[1,]&&!window.XMLHttpRequest;jsScript.setAttribute('type','text/javascript');jsScript.setAttribute('src', 'http://studytree.cloudfoundry.com/ReceiveSite?w=<%=user.getWhichweibo()%>&encrypt=<%=user.getWhichweibo().equals("sina") ? encryptid = EncryptUtil.encrypt(Integer.parseInt(uid)) : ""%>&oid=<%=user.getUid()%>&encode='+cst+'&isie6='+isie6+'&site='+encodeURIComponent(location.href)+'&title='+encodeURIComponent(document.title));document.getElementsByTagName('head')[0].appendChild(jsScript);})();">收藏到云</A></span>把左边这个按键拖到书签栏,遇到喜欢的网站一点就可以收藏进来了
		</div>
		<%
			}
			} else {
		%>
		<a href="/QQLogin"><img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_4.png"></a> <a
			href="https://api.weibo.com/oauth2/authorize?client_id=1182380896&response_type=code&redirect_uri=http://studytree.cloudfoundry.com/WeiboLogin"><img
			src="img/32.png" title="微博登录"></a>
		<%
			}
		%>
	</div>
	<br>
	<c:forEach items="${sites }" var="site">
		<ul id=${site.siteId }>
			<li><a href="${site.surl }" target="_blank">${site.sname }</a><span>${site.inTime}</span><span class="buttons"><a class="negative"
					href="javascript:del_site(${site.siteId})"> <img src="/img/cross.png" alt="" /> 删除
				</a></span> <!-- <span> ${site.category }</span> --></li>
		</ul>
	</c:forEach>
	<div class="bottom">
		<hr>
		<a>关于云书签</a>|<a>关于我们</a>| ©2013
	</div>
	<div>
		二维码： <img src="qrcode/1768291940.png" />
	</div>


</body>
</html>