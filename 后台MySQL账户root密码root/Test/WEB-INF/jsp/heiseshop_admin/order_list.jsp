<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet"
	type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>
<script type="text/javascript">  
       var page = 0;
        function go(url) {  
        	page = page + 1;
        	 $.get(url+"?page="+page+"&rows=20",function(data,status){
        		 $(".wrap").html(data);
        	    }); 
        }  
        function pre(url) {  
        	page = page - 1;
        	alert(page);
      		if(page < 0) {
      			return;
      		}
        	 $.get(url+"?page="+page+"&rows=20",function(data,status){
        		 $(".wrap").html(data);
        	    }); 
        } 
        function deleteOrder(orderNum){
        	if(confirm("确定 删除？")) {
           		 $.get("http://114.215.46.63/Test/admin/deleteOrder?orderNum="+orderNum,function(data,status){
            		  alert("数据：" + data + "\n状态：" + status);
           		 });
        	}
        };
        function setAllCheck(){
        	
        	$(".check").each(function(){
        		//.attr('checked):   //看版本1.6+返回:”checked”或”undefined” ;1.5-返回:true或false
        		//这里不能用
        		//$("#cb1").attr("checked",true);在这只有第一次有作用
        		//alert($(this).is(":checked"));
        		if($(this).is(":checked")){
        			$(this).prop("checked", false);
        		} else {
        			$(this).prop("checked",true);
        		}
        	});
        	
        }
        function sendPro(jsessionid){
        	var value="";
        	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//可以使用this.value+',';   或者   $(this).val()+',';
        	});
        	 $.post("http://114.215.46.63/Test/admin/changeMoreOrder;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
        		 alert("数据：" + data + "\n状态：" + status + " value " + data.progressValue );
       			$(".check").filter(":checked").parents("tr").children(".wstatus").children().text("待收货");
      		 });
        }
        function getAllCheck(jsessionid){
        	var value="";
        	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//可以使用this.value+',';   或者   $(this).val()+',';
        	});
        	 $.post("http://114.215.46.63/Test/admin/deleteMoreOrder;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
        		 alert("数据：" + data + "\n状态：" + status + " value " + data.progressValue );
       			$(".check").filter(":checked").parents("tr").remove();
      		 });
        }
    </script>
</head>
<body class="mybody">
	<div class="wrap">
		<div class="page-title">
			<span class="modular fl"><i class="order"></i><em>订单列表</em></span>
		</div>
		<div class="operate">
			<form action="/Test/admin/searchOrder" method="post">
				<img src="/Test/static/images/icon_search.gif" /> 
					<input type="text" name="content"
					class="textBox length-long" placeholder="输入订单编号或收件人姓名..." /> 
					<select name="orderType" class="inline-select">
						<option value="1">未付款</option>
						<option value="2">已付款</option>
					</select> 
				<input type="submit" value="查询" class="tdBtn" />
			</form>
		</div>
		<table class="list-style Interlaced">
			<tr>
				<th>订单编号</th>
				<th>下单时间</th>
				<th>收件人</th>
				<th>订单金额</th>
				<th>订单状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${products}" var="pro">
				<tr>
					<td><input type="checkbox" class="check" value="${pro.ordernum}"/> 
					<a href="order_detail?orderNum=${pro.ordernum}">${pro.ordernum}</a>
					</td>
					<td class="center"><span class="block">DeatGhost</span> <span
						class="block">${pro.orderTime}</span></td>
					<td width="450"><span class="block">${pro.username}[${pro.usertelephone}]</span>
						<address>${pro.userprovince}${pro.usercity}${pro.userdistrict}${pro.userdetailedaddress}</address>
					</td>
					<td class="center"><span><i>￥</i><b>${pro.sumofSalesPrice}</b></span>
					</td>
					<td class="center wstatus"><c:if test="${pro.orderType == 1}">
							<span>未付款</span>
						</c:if> <c:if test="${pro.orderType == 2}">
							<span>待发货</span>
						</c:if> <c:if test="${pro.orderType == 3}">
							<span>待收货</span>
						</c:if> <c:if test="${pro.orderType == 4}">
							<span>待评价</span>
						</c:if> <c:if test="${pro.orderType == 5}">
							<span>已完成</span>
						</c:if></td>
					<td class="center"><a href="order_detail?orderNum=${pro.ordernum}"
						class="inline-block" title="查看订单"><img
							src="/Test/static/images/icon_view.gif" /></a> <a
						class="inline-block" title="删除订单"><img
							src="/Test/static/images/icon_trash.gif" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- BatchOperation -->
		<div style="overflow: hidden;">
			<!-- Operation -->
			<%String s = (String)session.getId(); //获取session ID号  %>
			<div class="BatchOperation fl">
				<input type="checkbox" id="del" onclick="setAllCheck()"/> <label for="del"
					class="btnStyle middle">全选</label> 
					 <input type="button" value="配货"
					class="btnStyle" onclick="sendPro('<%=s%>')"/> 
					<input type="button" value="删除订单"
					class="btnStyle" onclick="getAllCheck('<%=s%>')"/>
			</div>
			<!-- turn page -->
			<div class="turnPage center fr">
				<c:choose>
					<c:when test="${page>=1}">
						<a
							href="orders?page=${page-1}&rows=10">上一页</a>
					</c:when>
					<c:otherwise>
						<a>首页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page<=pageNum}">
						<a
							href="orders?page=${page+1}&rows=10">下一页</a>
					</c:when>
					<c:otherwise>
						<a>最后一页</a>
					</c:otherwise>
				</c:choose>
				<a href="orders?page=0&rows=10">第一页</a> 
				<a href="orders?page=${pageNum}&rows=10">最后一页</a>
			</div>
		</div>
	</div>
</body>
</html>