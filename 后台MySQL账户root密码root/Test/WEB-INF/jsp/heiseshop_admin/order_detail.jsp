<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
</head>
<body>
<script>
function changeStatus(orderNum,orderType,jsessionid){
	if(confirm("确定发货？")) {
    	$.get("http://114.215.46.63/Test/admin/changeOrder;jsessionid="+jsessionid+"?orderNum="+orderNum+"&orderType="+orderType,function(data,status){
      	alert("数据：" + data + "\n状态：" + status);
    	});
    }
};
function deleteOrder(orderNum,jsessionid){
	if(confirm("确定 删除？")) {
   		 $.get("http://114.215.46.63/Test/admin/deleteOrder;jsessionid="+jsessionid+"?orderNum="+orderNum,function(data,status){
    		  alert("数据：" + data + "\n状态：" + status);
   		 });
	}
};
</script>
	<div class="wrap">
		<div class="page-title">
			<span class="modular fl"><i class="order"></i><em>订单编号：${product.ordernum}</em></span>
		</div>
		<dl class="orderDetail">
			<dt class="order-h">订单详情</dt>
			<dd>
				<ul>
					<li><span class="h-cont h-right">收件人姓名：</span> <span
						class="h-cont h-left">${product.username}</span></li>
					<li><span class="h-cont h-right">联系电话：</span> <span
						class="h-cont h-left">${product.usertelephone}</span></li>
					<li><span class="h-cont h-right">联系地址：</span> <span
						class="h-cont h-left">${product.userprovince}${product.usercity}${product.userdistrict}${product.userdetailedaddress}</span>
					</li>
					<li><span class="h-cont h-right">付款状态：</span> 
					    <c:if
							test="${product.orderType == 1}">
							<span class="h-cont h-left">未付款</span>
						</c:if> <c:if test="${product.orderType == 2}">
							<span class="h-cont h-left">待发货</span>
						</c:if> <c:if test="${product.orderType == 3}">
							<span class="h-cont h-left">待收货</span>
						</c:if> <c:if test="${product.orderType == 4}">
							<span class="h-cont h-left">待评价</span>
						</c:if> <c:if test="${product.orderType == 5}">
							<span class="h-cont h-left">已完成</span>
						</c:if>
					 </li>
					<li><span class="h-cont h-right">下单时间：</span> <span
						class="h-cont h-left">${product.orderTime}</span></li>
					<li><span class="h-cont h-right">付款时间：</span> 
					<c:if test="${product.orderType == 2 || product.orderType == 3 || product.orderType == 4
					|| product.orderType == 5}">
						<span class="h-cont h-left">2015-04-18 13:35</span>
					</c:if>
					<c:if test="${product.orderType == 1}">
						<span class="h-cont h-left">还没有下单</span>
					</c:if>	
					</li>
				</ul>
			</dd>
			<dd style="padding: 1em 0;">
				<span><b>订单留言：</b></span>${product.buyermessage}<span></span>
			</dd>
			<dd>
				<table class="list-style">
					<tr>
						<th>缩略图</th>
						<th>产品</th>
						<th>单价</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<c:forEach items="${product.orderShopList}" var="pro">
					<tr>
						<td><img src="#" class="thumbnail" /></td>
						<td>${pro.shopTitle}</td>
						<td><span> <i>￥</i> <em>${pro.shopPrice}</em>
						</span></td>
						<td>${pro.shopNumber}</td>
						<td><span> <i>￥</i> <em>${pro.shopNumber * pro.shopPrice}</em>
						</span></td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<div class="fr">
								<span style="font-size: 15px; font-weight: bold;"> <i>订单共计金额：￥</i>
									<b>${product.sumofSalesPrice}</b>
								</span>
							</div>
						</td>
					</tr>
				</table>
			</dd>
			<dd>
				<table class="noborder">
					<tr>
						<td width="10%" style="text-align: right;"><b>管理员操作：</b></td>
						<td><textarea class="block"
								style="width: 80%; height: 35px; outline: none;"></textarea></td>
					</tr>
				</table>
			</dd>
			<dd>
			<%String s = (String)session.getId(); //获取session ID号  %>
				<!-- Operation -->
				<div class="BatchOperation">
					<input type="button" value="发货" class="btnStyle" onclick="changeStatus('${product.ordernum}',3,'<%=s%>')"/> 
					<input type="button" value="取消订单" class="btnStyle" onclick="deleteOrder('${product.ordernum}','<%=s%>')"/>
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>