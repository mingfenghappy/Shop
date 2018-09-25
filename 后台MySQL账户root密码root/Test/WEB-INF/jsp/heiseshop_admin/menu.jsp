<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>左侧导航</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%String path = request.getContextPath();
System.out.println(path);%>
<link href="/Test/static/style/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>
</head>
<body style="background:#313131">
<div class="menu-list">
 <a href="main.html" target="mainCont" class="block menu-list-title center" style="border:none;margin-bottom:8px;color:#fff;">起始页</a>
 <ul>
  <li class="menu-list-title">
   <span>订单管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="orders?page=0&rows=10" title="商品列表" target="mainCont">订单列表</a></li>
   </ul>
  </li>
 
  <li class="menu-list-title">
   <span>商品管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="productList?page=0&rows=10" title="商品列表" target="mainCont">商品列表</a></li>
    <li><a href="product_category.html" title="商品分类" target="mainCont">商品分类</a></li>
    <li><a href="recycle_bin.html" title="商品分类" target="mainCont">商品回收站</a></li>
    <li><a href="orderReturnList?page=0&rows=10" title="商品分类" target="mainCont">商品退款</a></li>
   </ul>
  </li>
  
  <li class="menu-list-title">
   <span>会员管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="user_list.html" title="会员列表" target="mainCont">会员列表</a></li>
    <li><a href="add_user.html" title="添加会员" target="mainCont">添加会员</a></li>
    <li><a href="user_rank.html" title="会员等级" target="mainCont">会员等级</a></li>
    <li><a href="user_message.html" title="会员留言" target="mainCont">会员留言</a></li>
   </ul>
  </li>
  
  <li class="menu-list-title">
   <span>系统设置</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="basic_settings.html" title="站点基本设置" target="mainCont">站点基本设置</a></li>
    <li><a href="admin_list.html" title="站点基本设置" target="mainCont">站点管理员</a></li>
   </ul>
  </li>
  
  <li class="menu-list-title">
   <span>广告管理</span>
   <i>◢</i>
  </li>
  <li>
   <ul class="menu-children">
    <li><a href="advertising_list.html" title="站点基本设置" target="mainCont">广告列表</a></li>
   </ul>
  </li>
    
 </ul>
</div>
<div class="menu-footer">© 更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></div>
</body>
</html>