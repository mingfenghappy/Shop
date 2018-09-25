<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>产品列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/Test/static/style/adminStyle.css" rel="stylesheet" type="text/css" />
<script src="/Test/static/js/jquery.js"></script>
<script src="/Test/static/js/public.js"></script>
</head>
<body>
<script>
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
function getAllCheck(jsessionid){
	var value="";
	$(".check").filter(":checked").each(function(){value = value + $(this).attr("value")+',';//可以使用this.value+',';   或者   $(this).val()+',';
	});
	 $.post("http://114.215.46.63/Test/admin/deleteMoreProduct;jsessionid="+jsessionid+"?orderNum="+value,function(data,status){
		 alert("数据：" + data + "\n状态：" + status + " value " + data.progressValue );
			$(".check").filter(":checked").parents("tr").remove();
		 });
}  
</script>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i></i><em>产品列表</em></span>
    <span class="modular fr"><a href="addProduct?shopid=0" class="pt-link-btn">+添加商品</a></span>
  </div>
  <div class="operate">
   <form>
    <select class="inline-select">
     <option>A店铺</option>
     <option>B店铺</option>
    </select>
    <input type="text" class="textBox length-long" placeholder="输入产品名称..."/>
    <input type="button" value="查询" class="tdBtn"/>
   </form>
  </div>
  <table class="list-style Interlaced">
   <tr>
    <th>ID编号</th>
    <th>产品</th>
    <th>名称</th>
    <th>市场价</th>
    <th>会员价</th>
    <th>库存</th>
    <th>推荐商品</th>
    <th>操作</th>
   </tr>
   <c:forEach items="${product}" var="pro">
    <tr>
    <td>
     <span>
     <input type="checkbox" class="middle children-checkbox check" value="${pro.shopid}"/>
     <i>${pro.shopid}</i>
     </span>
    </td>
    <td class="center pic-area"><img src="#" class="thumbnail"/></td>
    <td class="td-name">
      <span class="ellipsis td-name block">${pro.shoptitle}</span>
    </td>
    <td class="center">
     <span>
      <i>￥</i>
      <em>${pro.shopprice}</em>
     </span>
    </td>
    <td class="center">
     <span>
      <i>￥</i>
      <em>${pro.shopprice}</em>
     </span>
    </td>
    <td class="center">
     <span>
      <em>${pro.shopCount}</em>
      <i>件</i>
     </span>
    </td>
    <c:if test="${pro.recommend == 1}">
		 <td class="center"><img src="/Test/static/images/yes.gif"/></td>
	</c:if>
    <c:if test="${pro.recommend == 0}">
    	<td class="center"><img src="/Test/static/images/no.gif"/></td>
    </c:if>
    <td class="center">
    <a href="addProduct?shopid=${pro.shopid}" title="编辑"><img src="/Test/static/images/icon_edit.gif"/></a>
    <a title="删除" href="deleteProduct?shopId=${pro.shopid}"><img src="/Test/static/images/icon_drop.gif"/></a>
    </td>
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
					<input type="button" value="批量删除" class="btnStyle" onclick="getAllCheck('<%=s%>')"/>
			</div>
			<!-- turn page -->
			<div class="turnPage center fr">
				<c:choose>
					<c:when test="${page>=1}">
						<a
							href="productList?page=${page-1}&rows=10">上一页</a>
					</c:when>
					<c:otherwise>
						<a>首页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page<=pageNum}">
						<a
							href="productList?page=${page+1}&rows=10">下一页</a>
					</c:when>
					<c:otherwise>
						<a>最后一页</a>
					</c:otherwise>
				</c:choose>
				<a href="productList?page=0&rows=10">第一页</a> 
				<a href="productList?page=${pageNum}&rows=10">最后一页</a>
			</div>
		</div>
 </div>
</body>
</html>