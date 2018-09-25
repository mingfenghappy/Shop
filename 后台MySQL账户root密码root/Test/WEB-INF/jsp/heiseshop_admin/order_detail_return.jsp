<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet"
	type="text/css" />
<link href="/Test/static/style/style.css" rel="stylesheet"
	type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>

</head>
<body>
	<script>
function refuseReturn(orderNum,returnNum,shopId,jsessionid){
	if(confirm("确定拒绝？")) {
    	$.post("http://114.215.46.63/Test/admin/refuseReturn;jsessionid="+jsessionid+"?orderNum="+orderNum+"&returnNum="+returnNum
    			+ "&shopId="+shopId,function(data,status){
      	alert("数据：" + data + "\n状态：" + status);
    	});
    }
};
function acceptReturn(returnNum,orderNum,jsessionid){
	if(confirm("确定同意?")) {
    	$.post("http://114.215.46.63/Test/admin/acceptReturn;jsessionid="+jsessionid+"?returnNum="+returnNum+"&orderNum="+orderNum,
    			function(data,status){
      	alert("数据：" + data + "\n状态：" + status);
    	});
    }
};
function deleteOrder(orderNum){
	if(confirm("确定 删除？")) {
   		 $.get("http://114.215.46.63/Test/admin/deleteOrder?orderNum="+orderNum,function(data,status){
    		  alert("数据：" + data + "\n状态：" + status);
   		 });
	}
};
</script>
	<div class="kePublic">
		<!--效果html开始-->
		<!--图库弹出层 开始-->
		<div class="mskeLayBg"></div>
		<div class="mskelayBox">
			<div class="mske_html"></div>
			<img class="mskeClaose" src="/Test/static/images/mke_close.png" width="27"
				height="27" />
		</div>
	</div>
		<!--图库弹出层 结束-->

		<div class="wrap">
			<div class="page-title">
				<span class="modular fl"><i class="order"></i><em>退款编号：${product.refundIdInvalid}</em></span>
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
						<li><span class="h-cont h-right">退款状态：</span> 
						<c:if test="${pro.cancelState == 1}">
							<span class="h-cont h-left">待退款</span>
						</c:if> <c:if test="${pro.cancelState == 2}">
							<span class="h-cont h-left">已同意</span>
						</c:if> <c:if test="${pro.cancelState == 3}">
							<span class="h-cont h-left">已拒绝</span>
						</c:if>
						</li>
						<li><span class="h-cont h-right">下单时间：</span> <span
							class="h-cont h-left">${product.timeOfApplication}</span></li>
						<li><span class="h-cont h-right">退款时间：</span> <c:if
								test="${!empty product.aRefundOfTime || product.aRefundOfTime != null}">
								<span class="h-cont h-left">product.aRefundOfTime</span>
							</c:if> <c:if
								test="${product.aRefundOfTime == null && empty product.aRefundOfTime}">
								<span class="h-cont h-left">还没有下单</span>
							</c:if></li>
					</ul>
				</dd>
				<dd style="padding: 1em 0;">
					<span><b>退款原因：</b></span>${product.returnReason}<span></span>
				</dd>
				<dd style="padding: 1em 0;">
					<span><b>退款详细原因：</b></span>${product.reasonfortheCredit}<span></span>
				</dd>
				<dd style="padding: 1em 0;">
					<span><b>退款图片描述：</b></span>
					<div class="msKeimgBox">
					 <ul>
						<c:forEach items="${pic}" var="item">
						<li>
							<img src="${item}" style="width: 100px; height: 100px;" />
							<span class="hidden"> <img src="${item}"
								height="460" />
							</span>
						</li>
						</c:forEach>
					</ul>
					</div>
				</dd>
				<dd>
					<table class="list-style">
						<tr>
							<th>缩略图</th>
							<th>产品</th>
							<th>单价</th>
							<th>数量</th>
							<th>商品小计</th>
							<th>运费</th>
						</tr>

						<tr>
							<td><img src="#" class="thumbnail" /></td>
							<td>${product.shopTitle}</td>
							<td><span> <i>￥</i> <em>${product.shopprice}</em>
							</span></td>
							<td>${product.returnCount}</td>
							<td><span> <i>￥</i> <em>${product.returnCount * product.shopprice}</em>
							</span></td>
							<td>${product.shopfreight}</td>
						</tr>

						<tr>
							<td colspan="5">
								<div class="fr">
									<span style="font-size: 15px; font-weight: bold;"> <i>订单共计金额：￥</i>
										<b>${product.orderRental}</b>
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
						<input type="button" value="同意退款" class="btnStyle"
							onclick="acceptReturn('${product.refundIdInvalid}','${product.orderNum}','<%=s%>')" />	
						<input type="button" value="拒绝退款" class="btnStyle"
							onclick="refuseReturn('${product.orderNum}','${product.refundIdInvalid}',${product.shopId},'<%=s%>')" />
					</div>
				</dd>
			</dl>
		</div>
</body>
</html>