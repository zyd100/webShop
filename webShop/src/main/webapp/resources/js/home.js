/*页面加载时请求并填充数据*/
$(document).ready(function() {
	$("div#h_personal").attr("userName",JSON.parse('${userName}'));
	
	//添加分类标签信息
	var dataCategory=JSON.parse('${categories}');
	for (var i = 0; i < dataCategory.length; i++) {
		var liObj = $("#tagLink").find("li.a").eq(i);
		var category_name = dataCategory[i].categoryName;
		var category_id = dataCategory[i].id;
		liObj.text(category_name);
		liObj.attr("category_id", category_id);
	}
	
	//添加图片滚动信息、广告图片信息
	var dataRollProducts=JSON.parse('${rollProducts}');
	for (var i = 0; i < 4; i++) {
		var imgObj = $("div.swiper-wrapper").find(".swiper-slide a");
		var product_id = dataRollProducts[i].id;
		var product_img = dataRollProducts[i].image;
		imgObj.attr("product_id", product_id);
		imgObj.find("img").attr("src", product_img);
	}
	for (var i = 4; i < 7; i++) {
		var imgObj = $("div#adImg").find("div a");
		var product_id = dataRollProducts[i].id;
		var product_img = dataRollProducts[i].image;
		imgObj.attr("product_id", product_id);
		imgObj.find("img").attr("src", product_img);
	}
	
	//添加分类商品栏信息
	var dataRandomProducts=JSON.parse('${randomProducts}');
	for (var i = 0; i < 8; i++) {
		var waresObject = $("#h_center_wares").children("div#h_wares").eq(i);
		var wares_category = "";
		for (var j = 0; j < 4; j++) {
			var waresSingleObject = waresObject.find("div").eq(j);
			var waresObj = waresSingleObject.find("a");
			var product_id = dataRandomProducts[i].productList[j].id;
			var product_name = dataRandomProducts[i].productList[j].productName;
			var product_price = dataRandomProducts[i].productList[j].shopPrice;
			var product_img = dataRandomProducts[i].productList[j].image;
			wares_category = dataRandomProducts[i].categoryName;
			var wares_name = product_name + "<br>¥<b>" + product_price + "</b>";
			waresObj.attr("product_id", product_id);
			waresObj.find("img").attr("src", product_img);
			waresSingleObject.find("a p.textFont").html(wares_name);
		}
		if (wares_category != "") {
			waresObject.children("p.waresTitle").text(wares_category);
		}
	}
});

/* 添加事件 */
$(document).ready(function() {
	/* 分类标签点击搜索 */
	$("#tagLink").on("click", "li a", function() {
		var category_name = $(this).text();
		var category_text = {
			"searchText": category_name,
			"offset": 0,
			"limit": 40
		};
		var search_url = "${pageContext.request.contextPath }/search";
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 图片滚动迪点击查看商品详情 */
	$("div.swiper-wrapper").on("click", ".swiper-slide a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 广告图片点击查看商品详情 */
	$("div#adImg").on("click", "div a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 查看商品详情 */
	$("div#h_center_wares").on("click", "div.bd", function() {
		var product_id = $(this).find("a").attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).find("a").attr("href", search_url);
		$(this).find("a").attr("target", "_blank");
	});

	/* 打开个人页面 */
	$("div#h_personal").on("click", "a#personal", function() {
		var userName = $("div#h_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开购物车 */
	$("div#h_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#h_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开收藏页面 */
	$("div#h_personal").on("click", "a#collection", function() {
		var userName = $("div#h_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 退出登录 */
	$("div#h_personal").on("click", "a#outLogin", function() {
		$.ajax({
			url: "${pageContext.request.contextPath }/users/logout",
			type: "put",
			success: function() {}
		});
	});
});

/*添加商品展示动画效果*/
$(window).scroll(function() {
	var scrollT = document.documentElement.scrollTop || document.body.scrollTop;
	var waresObject = $("#h_center_wares").find("div#h_wares");
	waresObject.each(function() {
		var backTop = $(this).offset().top - $(window).height() / 2;
		if (scrollT > backTop) {
			$(this).find("div.a1").addClass("animated bounceInLeft show").removeClass("fade");
			$(this).find("div.a2").addClass("animated bounceInDown show").removeClass("fade");
			$(this).find("div.a3").addClass("animated bounceInUp show").removeClass("fade");
			$(this).find("div.a4").addClass("animated bounceInRight show").removeClass("fade");
		}
	});
});

/*设置页面框架元素高度*/
$(document).ready(function() {
	var num = $("#h_center_wares").children("div").length;
	$("#h_center_wares").height(eval(550 * num + 100));
	$("#h_left").height(eval(550 * num + 870));
	$("#h_center").height(eval(550 * num + 870));
	$("#h_right").height(eval(550 * num + 870));
	$("#h_container").height(eval(550 * num + 1085));
});