$(document).ready(function(){
	
 //Tabel Interlaced background color 2015-04-20 DeathGhost
 $('.Interlaced tr:odd').addClass('trbgcolor');
 //left menu toggle style
 $('.menu-list-title').click(function(){
	   $(this).next('li').toggle('1500');
	  });
 //menu current background color
 $(".menu-children li").click(function(){
	 $(".menu-children li").css({background:'none'});
	 $(this).css({background:'#f35844'});
	});
//图库弹出层
	$(".mskeLayBg").height($(document).height());
	$(".mskeClaose").click(function(){$(".mskeLayBg,.mskelayBox").hide()});
	$(".msKeimgBox li").click(function(){$(".mske_html").html($(this).find(".hidden").html());$(".mskeLayBg").show();$(".mskelayBox").fadeIn(300)});
	$(".mskeTogBtn").click(function(){$(".msKeimgBox").toggleClass("msKeimgBox2");$(this).toggleClass("mskeTogBtn2")});

	//全局的ajax访问，处理ajax清求时sesion超时
	jQuery.ajaxSetup({
	    contentType : "application/x-www-form-urlencoded;charset=utf-8",
	    complete : function(XMLHttpRequest, textStatus) {
	        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
	        if (sessionstatus == "timeout") {
	            // 如果超时就处理 ，指定要跳转的页面
	            window.location.replace("/Test/admin/login.html");
	        }
	    }
	}); 
});

