<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>header</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
 <div class="logo">
  <img src="/Test/static/images/admin_logo.png" title="在哪儿"/>
 </div>
 <div class="fr top-link">
 <%String s = (String)session.getAttribute("user"); //获取session ID号  %>
  <a href="#" target="_blank" title="访问站点"><i class="shopLinkIcon"></i><span>访问站点</span></a>
  <a href="admin_list.html" target="mainCont" title="DeathGhost"><i class="adminIcon"></i><span>管理员：<%=s%></span></a>
  <a href="#" title="修改密码"><i class="clearIcon"></i><span>清除缓存</span></a>
  <a href="revise_password.html" target="mainCont" title="修改密码"><i class="revisepwdIcon"></i><span>修改密码</span></a>
  <a href="logout" title="安全退出" style="background:rgb(60,60,60);"><i class="quitIcon"></i><span>安全退出</span></a>
 </div>
</div>
</body>
</html>