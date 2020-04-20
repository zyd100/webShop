 function initProInfo(productInfo){
	$("img.bigImg").attr("src",  ctxImg +productInfo.product.image);
	$("img.bigImg").attr("myImg",  ctxImg +productInfo.product.image);
	//预备展示图片填入
	$("div.smallImgDiv").find("img").remove();
	for (var i = 0; i < 5 && i < productInfo.productImages.length; i++) {
		var product_image =  ctxImg +productInfo.productImages[i].image;
		var smallImgHtml = "<img src='" + product_image + "' bigImageUrl='" + product_image + "' class='smallImg'>";
		var $new = $(smallImgHtml);
		$("div.smallImgDiv").append($new);
	}
	var inforText = $("div.infor_text");
	inforText.find("p.productName").text(productInfo.product.productName);
	inforText.find("p.productName").attr("product_id",productInfo.product.id);
	inforText.find("span.oldPrice").text(productInfo.product.price);
	inforText.find("span.newPrice").text(productInfo.product.shopPrice);
	inforText.find("span#storeCount").text(productInfo.product.quantity);
	//添加商品介绍图片信息
	$("div#waresDetail").find("img").remove();
	for (var i = 0; i < productInfo.productImages.length; i++) {
		var imgIntro =  ctxImg +productInfo.productImages[i].image;
		var imgIntroHtml = "<img src='" + imgIntro + "'>";
		var $new=$(imgIntroHtml);
		$("div#waresDetail").append($new);
	}
	imgShow();
}

function initAceInfo(accessInfo){
	$("div#access").find("div.accessView").remove();
	for (var i = 0; i < accessInfo.length; i++) {
		var accessContent = accessInfo[i].comment.content;
		var accessDate = accessInfo[i].comment.createTime;
		var accessName = accessInfo[i].comment.userName;
		var accessHtml = "<div class='accessView'><div class='accessContent'>" + accessContent +
			"</div><div class='acessDate'>" +
			accessDate + "</div><div id='accessName'>" + accessName + "</div></div>";
		var $new = $(accessHtml);
		$("div#access").prepend($new);
	}
	$("div#wd_paging").attr("page", 1);
}

function setPageHeight(){
	//原始最低宽度1170  底部120	底部边距100   左右1020
	//评价150一个	标题50	遍历图像
	var lenImg = 0;
	var lenAce = 0;
	var len = 0;
	var obj = $("div#waresDetail").find("img");
	
	obj.each(function() {
		lenImg += $(this).height();
	});
	
	lenAce=150*$("div#access").find("div.accessView").length;
	
	len=lenAce>lenImg?len=lenAce:len=lenImg;
	
	$("#wd_center_intro").height(eval(len + 200));
	$("#wd_left").height(eval(len + 970));
	$("#wd_center").height(eval(len + 970));
	$("#wd_right").height(eval(len + 970));
	$("#wd_container").height(eval(len + 1250));

	/*var accessNum = parseInt(len / 150) - 1;
	var count = 1;
	$("div.introAccess").children("div.accessView").each(function() {
		if (count > accessNum) {
			$(this).css("display", "none");
		}
		count++;
	});*/
}

function imgShow(){
	/* 商品信息图片鼠标悬停事件 */
	$("img.smallImg").mouseover(function() {
		var bigImageURL = $(this).attr("bigImageUrl");
		$("img.bigImg").attr("src", bigImageURL);
	});
	
	$("img.smallImg").mouseleave(function(){
		var myImage=$("img.bigImg").attr("myImg");
		$("img.bigImg").attr("src",myImage);
	});
	
	/* 图片加载事件 */
	$("img.bigImg").load(
		function() {
			$("img.smallImg").each(function() {
				var bigImageURL = $(this).attr("bigImageUrl");
				img = new Image();
				img.src = bigImageURL;

				img.onload = function() {
					console.log(bigImageURL);
					$("div.img4load").append($(img));
				};
			});
		}
	);
}

/*页面加载时请求并填充数据*/
/*$(document).ready(function() {
	$("img.bigImg").css("src", data.product.image[0]);
	for (var i = 1; i < 6 && i < data.product.image.length; i++) {
		var product_image = data.product.image[i];
		var smallImgHtml = "<img src='" + product_image + "' bigImageUrl='" + product_image + "' class='smallImg'>";
		var $new = $(smallImgHtml);
		$("div.smallImgDiv").append($new);
	}
	var inforText = $("div.infor_text");
	inforText.find("p.productName").text(data.product.productName);
	inforText.find("p.productName").attr("product_id",data.product.id);
	inforText.find("span.oldPrice").text(data.product.price);
	inforText.find("span.newPrice").text(data.product.shopPrice);
	inforText.find("span#storeCount").text(data.product.quantity);
	//添加商品介绍图片信息
	for (var i = 0; i < data.productImage.length; i++) {
		var imgIntro = data.productImage.image[i];
		var imgIntroHtml = "<img src='" + imgIntro + "'>";
		var $new = $(imgIntroHtml);
		$("div#waresDetail").last().append($new);
	}
});*/

/* 请求商品评论数据 */
/*$(document).ready(function() {
	var productId = $("div.infor_text").find("p.productName").attr("product_id");
	var access_url = "${pageContext.request.contextPath }/products/{" + productId + "}/comments/{0}/{10}";
	$.ajax({
		type: "get",
		url: access_url,
		success: function(data) {
			if (JSON.parse(data).success) {
				for (var i = 0; i < JSON.parse(data).data.length; i++) {
					var accessContent = JSON.parse(data).data[i].content;
					var accessDate = JSON.parse(data).data[i].date;
					var accessName = JSON.parse(data).data[i].userName;
					var accessHtml = "<div class='accessView'><div class='accessContent'>" + accessContent +
						"</div><div class='acessDate'>" +
						accessDate + "</div><div id='accessName'>" + accessName + "</div></div>";
					var $new = $(accessHtml);
					$("div#access").prepend($new);
				}
				$("div#wd_paging").attr("page", 1);
			}
		}
	});
});*/

/*添加事件*/
$(document).ready(function() {
	/* 前往个人中心 */
	$("div#wd_personal").on("click", "a#personal", function() {
		var userName = $("div#wd_personal").attr("userName");
		var search_url = ctx+"/users/" + userName;
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
		/*$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});*/
	});

	/* 前往购物车 */
	$("div#wd_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#wd_personal").attr("userName");
		var search_url = ctx+"/users/" + userName + "/carts";
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
		/*$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});*/
	});

	/* 前往收藏页面 */
	$("div#wd_personal").on("click", "a#collection", function() {
		var userName = $("div#wd_personal").attr("userName");
		var search_url = ctx+"/users/" + userName + "/stars";
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
		/*$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});*/
	});

	/* 退出登录 */
	$("div#wd_personal").on("click", "a#outLogin", function() {
		$.ajax({
			url: ctx+"/users/logout",
			type: "post",
			success: function() {}
		});
	});

	/* 添加商品至购物车 */
	$("div.buyBtns").on("click", "button#addCart", function() {
		var buyCount = $("input#buyCount").val();
		var product_id = $("p.productName").attr("product_id");
		var userName = $("div#wd_personal").attr("userName");
		if(userName==""){
			alert("请先登录！！！");
			return;
		}	
		var cartText = {
			"productId": product_id,
			"quantity": buyCount
		};
		var search_url = ctx+"/users/" + userName + "/carts/" + product_id ;
		/* 添加数据 */
		$.ajax({
			url: search_url,
			type: "post",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(cartText),
			success: function(data) {
				if (JSON.parse(data).success == true) {
					alert("添加成功");
				}
			}
		});
	});

	/* 添加商品至收藏 */
	$("div.buyBtns").on("click", "button#addCollect", function() {
		var product_id = $("p.productName").attr("product_id");
		var userName = $("div#wd_personal").attr("userName");
		if(userName==""){
			alert("请先登录！！！");
			return;
		}	
		var collectText = {
			"productId": product_id
		};
		var collect_url = ctx+"/users/" + userName + "/stars/" + product_id;
		/* 添加数据 */
		$.ajax({
			url: collect_url,
			type: "post",
			data: collectText,
			success: function(data) {
				if (JSON.parse(data).success == true) {
					alert("添加成功");
				}
			}
		});
	});

	/* 立即下单 */
	$("div.buyBtns").on("click", "button#buyBtn", function() {
		var userName = $("div#wd_personal").attr("userName");
		var buyCount = $("input#buyCount").val();
		var product_id = $("p.productName").attr("product_id");
		if(userName==""){
			alert("请先登录！！！");
			return;
		}	
		var order_url = ctx+"/users/" + userName + "/carts/order";
		$("form#buyForm").attr("action",order_url);
		
		var nameHtml="<input type='hidden' name='userName' value='"+userName+"' />";
		var $new=$(nameHtml);
		$("form#buyForm").prepend($new);
		idHtml="<input type='hidden' name='orderItemList[0].productId' value='"+product_id+"' />";
		$new=$(idHtml);
		$("form#buyForm").prepend($new);
		countHtml="<input type='hidden' name='orderItemList[0].quantity' value="+buyCount+" />";
		$new=$(countHtml);
		$("form#buyForm").prepend($new);
		
		$("form#buyForm").submit();
	});
});

/* 评论翻页事件 */
$(document).ready(function() {
	/* 向前翻页 */
	$("div#access nav").on("click", "li.prevPage a", function() {
		var productId = $("div.infor_text").find("p.productName").attr("product_id");
		var nowPage = $("div#wd_paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var prev_url = ctx+"/products/" + productId + "/comments/" + (--nowPage - 1)*10 +"/10";
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("div#access").find("div.accessView").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var accessContent = JSON.parse(data).data[i].comment.content;
						var accessDate = JSON.parse(data).data[i].createTime;
						var accessName = JSON.parse(data).data[i].userName;
						var accessHtml = "<div class='accessView'><div class='accessContent'>" + accessContent +
							"</div><div class='acessDate'>" +
							accessDate + "</div><div id='accessName'>" + accessName + "</div></div>";
						var $new = $(accessHtml);
						$("div#access").prepend($new);
					}
					$("div#wd_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});

	/* 向后翻页 */
	$("div#access nav").on("click", "li.nextPage a", function() {
		var productId = $("div.infor_text").find("p.productName").attr("product_id");
		var nowPage = $("div#wd_paging").attr("page");
		var next_url = ctx+"/products/" + productId + "/comments/" + (++nowPage - 1)*10 +"/10";
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					if(JSON.parse(data).data.length==0){
						alert("已经是最后一页了~");
						$("div#wd_paging").attr("page", (--nowPage));
						return;
					}
					$("div#access").find("div.accessView").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var accessContent = JSON.parse(data).data[i].comment.content;
						var accessDate = JSON.parse(data).data[i].createTime;
						var accessName = JSON.parse(data).data[i].userName;
						var accessHtml = "<div class='accessView'><div class='accessContent'>" + accessContent +
							"</div><div class='acessDate'>" +
							accessDate + "</div><div id='accessName'>" + accessName + "</div></div>";
						var $new = $(accessHtml);
						$("div#access").prepend($new);
					}
					$("div#wd_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});
});

/*图片展示实现*/
$(document).ready(function() {
	imgShow();
});

/*设置页面框架元素高度*/
$(document).ready(function() {
	//setPageHeight();
});
