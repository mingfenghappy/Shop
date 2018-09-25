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
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/Test/uditetor/lang/zh-cn/zh-cn.js"></script>
<script src="/Test/static/js/jquery.js"></script>
</head>
<body>
<script>
var imageIndex = 0;
var filestr = "";
$(document).ready(function () {
	var selectText = $(".textBox option:first");
	$.each(JSON.parse(selectText.attr("data-obj")),function(index,obj){
    	//alert(obj.secondleveltitle);
    	$(".textBox1").append("<option value="+obj.secondlevelid+">"+obj.secondleveltitle+"</option>");
   	   });
	  $(".textBox").bind("change",function(){
	    //$(".textarea").val($(this).val());
	    $(".textBox1").empty();
	    //获取选中的option元素
	    var selectText = $(".textBox").find("option:selected");
	    $.each(JSON.parse(selectText.attr("data-obj")),function(index,obj){
	    	//alert(obj.secondleveltitle);
	    	$(".textBox1").append("<option value="+obj.secondlevelid+">"+obj.secondleveltitle+"</option>");
	   	   });
	  });
	});
function submitForm(jsessionid){
	var formData = new FormData();
	var shopTitle = $("input[name=shopTitle]").val();
	var shopId = $("input[name=shopid]").val();
	var shopPrice = $("input[name=shopPrice]").val()
	var classifyid = $("select[name=classifyid]").val()
	var secondlevel = $("select[name=secondlevel]").val()
	var shopCount = $("input[name=shopCount]").val()
	var shopPicText = $("input[name=shopPicText]").val()
	shopPicText
	var fileu = filestr;
	var editor = getContent();
	var recommend="1"; 
	$("input[name=recommend]").filter(":checked").each(function(){
		recommend = $(this).attr("value");//可以使用this.value+',';   或者   $(this).val()+',';
	});
	formData.append("shopTitle",shopTitle);
	formData.append("shopid",shopId);
	formData.append("shopPrice",shopPrice);
	formData.append("classifyid",classifyid);
	formData.append("secondlevel",secondlevel);
	formData.append("shopCount",shopCount);
	formData.append("shopPicText",shopPicText);
	formData.append("fileu",fileu);
	formData.append("editor",editor);
	formData.append("recommend",recommend);
	$.ajax({ 
	url : "http://114.215.46.63/Test/admin/submitProduct;jsessionid="+jsessionid, 
	type : 'POST', 
	data : formData, 
	// 告诉jQuery不要去处理发送的数据
	processData : false, 
	// 告诉jQuery不要去设置Content-Type请求头
	contentType : false,
	beforeSend:function(){
	},
	success : function(responseStr) { 
		alert("success");
	}, 
	error : function(responseStr) { 
		alert(responseStr);
	} 
	});
}
function uploadFile1(){
	var formData = new FormData();
	formData.append("file",$("#chanpinzhutu")[0].files[0]);
	$.ajax({ 
	url : "http://114.215.46.63/Test/person/fileUpload", 
	type : 'POST', 
	data : formData, 
	// 告诉jQuery不要去处理发送的数据
	processData : false, 
	// 告诉jQuery不要去设置Content-Type请求头
	contentType : false,
	beforeSend:function(){
	},
	success : function(responseStr) { 
		if(imageIndex==5) {
			imageIndex = 0;
		}
		imageIndex++;
		filestr = filestr + responseStr +",";
		alert(filestr);
		$("#img"+imageIndex).attr("src",responseStr);
		$("#chanpinzhutu").val(filestr);
	}, 
	error : function(responseStr) { 
		alert(responseStr);
	} 
	});
}
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');


function isFocus(e){
    alert(UE.getEditor('editor').isFocus());
    UE.dom.domUtils.preventDefault(e)
}
function setblur(e){
    UE.getEditor('editor').blur();
    UE.dom.domUtils.preventDefault(e)
}
function insertHtml() {
    var value = prompt('插入html代码', '');
    UE.getEditor('editor').execCommand('insertHtml', value)
}
function createEditor() {
    enableBtn();
    UE.getEditor('editor');
}
function getAllHtml() {
    alert(UE.getEditor('editor').getAllHtml())
}
function getContent() {
	//适配手机模板
	var headmy ="\n<html>\n<head>\n" 
		 +"<meta charset=\"gbk\"/>\n"
		 +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n"
		 + "<style>img{max-width:100% !important;}</style>\n"
		 +"</head>\n"
		 + "<body width=100% style=\"word-wrap:break-word; font-family:Arial\">\n"
    var content = UE.getEditor('editor').getContent();
    var end = ("\n</body>\n</html>\n");
    return headmy + content + end;
}
function getPlainTxt() {
    var arr = [];
    arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getPlainTxt());
    alert(arr.join('\n'))
}
function setContent(isAppendTo) {
    var arr = [];
    arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
    UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
    alert(arr.join("\n"));
}
function setDisabled() {
    UE.getEditor('editor').setDisabled('fullscreen');
    disableBtn("enable");
}

function setEnabled() {
    UE.getEditor('editor').setEnabled();
    enableBtn();
}

function getText() {
    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
    var range = UE.getEditor('editor').selection.getRange();
    range.select();
    var txt = UE.getEditor('editor').selection.getText();
    alert(txt)
}

function getContentTxt() {
    var arr = [];
    arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
    arr.push("编辑器的纯文本内容为：");
    arr.push(UE.getEditor('editor').getContentTxt());
    alert(arr.join("\n"));
}
function hasContent() {
    var arr = [];
    arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
    arr.push("判断结果为：");
    arr.push(UE.getEditor('editor').hasContents());
    alert(arr.join("\n"));
}
function setFocus() {
    UE.getEditor('editor').focus();
}
function deleteEditor() {
    disableBtn();
    UE.getEditor('editor').destroy();
}
function disableBtn(str) {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        if (btn.id == str) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        } else {
            btn.setAttribute("disabled", "true");
        }
    }
}
function enableBtn() {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
    }
}

function getLocalData () {
    alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
}

function clearLocalData () {
    UE.getEditor('editor').execCommand( "clearlocaldata" );
    alert("已清空草稿箱")
}
</script>
 <div class="wrap">
  <div class="page-title">
    <span class="modular fl"><i class="add"></i><em>编辑/添加产品</em></span>
    <span class="modular fr"><a href="product_list.html" class="pt-link-btn">产品列表</a></span>
  </div>
  <table class="list-style">
  <tr>
   <td style="text-align:right;width:15%;">商品id</td>
   <td><input type="text" class="textBox length-long" name="shopid" value="${product.shopid}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;width:15%;">产品名称：</td>
    <td><input type="text" class="textBox length-long" name="shopTitle" value="${product.shoptitle}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;">产品一级分类：</td>
    <td>
     <select class="textBox" name="classifyid">
     <c:forEach items="${category}" var="cate">
      <option value="${cate.stairId}" data-obj=${cate.secondLevelList}>${cate.stairTitle}</option>
      </c:forEach>
     </select>
    </td>
   </tr>
      <tr>
    <td style="text-align:right;">产品二级分类：</td>
    <td>
     <select class="textBox1" name="secondlevel">
     </select>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">市场价：</td>
    <td>
     <input type="text" class="textBox length-short" name="shopPrice" value="${product.shopprice}"/>
     <span>元</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">会员价：</td>
    <td>
     <input type="text" class="textBox length-short"/>
     <span>元</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">库存：</td>
    <td>
     <input type="text" class="textBox length-short" name="shopCount" value="${product.shopCount}"/>
     <span>盘</span>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">推荐至：</td>
    <td>
     <input type="checkbox" name="recommend" id="jingpin" value="1"/>
     <label for="jingpin" >推荐</label>
     <input type="checkbox" name="recommend" id="xinpin" value="0"/>
     <label for="xinpin">普通商品</label>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">产品主图：</td>
    <td>
     <input type="file" id="chanpinzhutu" class="hide" name="fileu"
     onchange="uploadFile1()"/>
     <label for="chanpinzhutu" class="labelBtn2">本地上传(最多选择N张)</label>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <td>
     <img id="img1" src="#" width="80" height="80"/>
     <img id="img2" src="#" width="80" height="80"/>
     <img id="img3" src="#" width="80" height="80"/>
     <img id="img4" src="#" width="80" height="80"/>
     <img id="img5" src="#" width="80" height="80"/>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;">使用已有产品详情：</td>
    <td><input type="text" class="textBox length-long" name="shopPicText" value="${product.shopgraphicdetails}"/></td>
   </tr>
   <tr>
    <td style="text-align:right;">产品详情：</td>
    <td>
    	<script id="editor" type="text/plain"
				style="width:100%;height:30%;"></script>
    </td>
   </tr>
   <tr>
    <td style="text-align:right;"></td>
    <%String s = (String)session.getId(); //获取session ID号  %>
    <td><input type="button" value="发布新商品" class="tdBtn" onclick="submitForm('<%=s%>')"/></td>
   </tr>
  </table>
  </form>
 </div>
</body>
</html>