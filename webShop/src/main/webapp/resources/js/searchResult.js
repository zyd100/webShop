/*页面加载时请求并填充数据*/
$(document).ready(function() {
	var data = JSON.parse('$ {searchResult}');
	var pageCount = 1;
	$(":text").val(JSON.parse('$ {searchText}'));
	
	$("div#sr_personal").attr("userName",JSON.parse('${userName}'));
	
	if (data.productMaxCount == 0) {
		$("div#sr_wares").css("display", "none");
		$("div#sr_paging").css("display", "none");
		$("div#sr_failed").css("display", "block");
	} else {
		if (Number(eval(data.productMaxCount % 40)) == 0) {
			pageCount = Number(eval(data.productMaxCount / 40));
		} else {
			pageCount = Number(eval(data.productMaxCount / 40) + 1);
		}
		//添加查询结果商品信息
		for (var i = 0; i < data.productList.length; i++) {
			var product_id = data.productList[i].id;
			var product_name = data.productList[i].productName;
			var product_price = data.productList[i].shopPrice;
			var product_img = data.productList[i].image;
			var wares_name = product_name + "<br>¥<b>" + product_price + "</b>";
			var htmlText = "<div class='col-sm-3 col-xs-3 a3 bd fade'><a href='javascript:;' product_id='" + product_id +
				"'><img src='" + product_img +
				"' class='waresImg mgImg hvImg'><br><br><p class='textFont'>" + wares_name + "</p></a></div>";
			var $new = $(htmlText);
			$("div#sr_wares").last().append($new);
		}
		//设置翻页数量
		for (var i = pageCount; i > 1; i--) {
			var pageHtml = "<li><span>" + i + "</span></li>";
			var $new = $(pageHtml);
			$("li.pageOne").after($new);
		}
	}
});

/*添加点击事件*/
$(document).ready(function() {
	/* 查看商品信息 */
	$("div#sr_wares").on("click", "div.bd", function() {
		var product_id = $(this).find("a").attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).find(a).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 前往个人中心 */
	$("div#sr_personal").on("click", "a#personal", function() {
		var userName = $("div#sr_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 前往购物车 */
	$("div#sr_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#sr_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 前往收藏页面 */
	$("div#sr_personal").on("click", "a#collection", function() {
		var userName = $("div#sr_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 退出登录 */
	$("div#sr_personal").on("click", "a#outLogin", function() {
		$.ajax({
			url: "${pageContext.request.contextPath }/users/logout",
			type: "put",
			success: function() {}
		});
	});
});

/*添加翻页点击事件*/
$(document).ready(function() {
	$("ul.pagination").on("click", "li", function() {
		var nowClickPage = $(this).find("span").text(); //现在点击的页数
		var nowActivePage = $(this).closest("ul.pagination").find("li.active"); //现在活跃的页
		var prevLi = $(this).closest("ul.pagination").find("li.prev"); //按钮：上一页
		var nextLi = $(this).closest("ul.pagination").find("li.next"); //按钮：下一页
		var keyword = $(":text").val(data.keyword);
		if ($(this).hasClass("prev")) {
			if ($(this).next().text() == nowActivePage.text()) {
				prevLi.addClass("disabled")
			} else {
				nowActivePage.prev().addClass("active");
				nowActivePage.removeClass("active");

				var prevPage = nowActivePage.prev().text();
				/*提交翻页获取数据*/
				var product_text = {
					"offset": Number(prevPage),
					"limit": 40,
					"searchText": keyword
				};
				var search_url = "${pageContext.request.contextPath }/search/{" + keyword + "}/{" + Number(prevPage) + "}/{" +
					40 + "}";
				$.ajax({
					url: search_url,
					type: "get",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(product_text),
					success: function(data, status) {
						$("div#sr_wares").find("div.bd").remove();
						for (var i = 0; i < JSON.parse(data).productList.length; i++) {
							var product_id = JSON.parse(data).productList[i].id;
							var product_name = JSON.parse(data).productList[i].productName;
							var product_price = JSON.parse(data).productList[i].shopPrice;
							var product_img = JSON.parse(data).productList[i].image;
							var wares_name = product_name + "<br>¥<b>" + product_price + "</b>";
							var htmlText = "<div class='col-sm-3 col-xs-3 a3 bd fade'><a href='javascript:;' product_id='" +
								product_id +
								"'><img src='" + product_img +
								"' class='waresImg mgImg hvImg'><br><br><p class='textFont'>" + wares_name + "</p></a></div>";
							var $new = $(htmlText);
							$("div#sr_wares").last().append($new);
						}
					},
					error: function() {

					}
				});
			}
		} else if ($(this).hasClass("next")) {
			if ($(this).prev().text() == nowActivePage.text()) {
				nextLi.addClass("disabled");
			} else {
				nowActivePage.next().addClass("active")
				nowActivePage.removeClass("active");

				var nextPage = nowActivePage.next().text();
				/*提交翻页获取数据*/
				var product_text = {
					"offset": Number(nextPage),
					"limit": 40,
					"searchText": keyword
				};
				var search_url = "${pageContext.request.contextPath }/search/{" + keyword + "}/{" + Number(nextPage) + "}/{" +
					40 + "}";
				$.ajax({
					url: search_url,
					type: "get",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(product_text),
					success: function(data, status) {
						$("div#sr_wares").find("div.bd").remove();
						for (var i = 0; i < JSON.parse(data).productList.length; i++) {
							var product_id = JSON.parse(data).productList[i].id;
							var product_name = JSON.parse(data).productList[i].productName;
							var product_price = JSON.parse(data).productList[i].shopPrice;
							var product_img = JSON.parse(data).productList[i].image;
							var wares_name = product_name + "<br>¥<b>" + product_price + "</b>";
							var htmlText = "<div class='col-sm-3 col-xs-3 a3 bd fade'><a href='javascript:;' product_id='" +
								product_id +
								"'><img src='" + product_img +
								"' class='waresImg mgImg hvImg'><br><br><p class='textFont'>" + wares_name + "</p></a></div>";
							var $new = $(htmlText);
							$("div#sr_wares").last().append($new);
						}
					},
					error: function() {

					}
				});
			}
		} else if (nowClickPage != nowActivePage.text()) {
			$(this).addClass("active");
			nowActivePage.removeClass("active");
			/*提交翻页获取数据*/
			var product_text = {
				"offset": Number(nowClickPage),
				"limit": 40,
				"searchText": keyword
			};
			var search_url = "${pageContext.request.contextPath }/search/{" + keyword + "}/{" + Number(nowClickPage) + "}/{" +
				40 + "}";
			$.ajax({
				url: search_url,
				type: "get",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(product_text),
				success: function(data, status) {
					$("div#sr_wares").find("div.bd").remove();
					for (var i = 0; i < JSON.parse(data).productList.length; i++) {
						var product_id = JSON.parse(data).productList[i].id;
						var product_name = JSON.parse(data).productList[i].productName;
						var product_price = JSON.parse(data).productList[i].shopPrice;
						var product_img = JSON.parse(data).productList[i].image;
						var wares_name = product_name + "<br>¥<b>" + product_price + "</b>";
						var htmlText = "<div class='col-sm-3 col-xs-3 a3 bd fade'><a href='javascript:;' product_id='" +
							product_id +
							"'><img src='" + product_img +
							"' class='waresImg mgImg hvImg'><br><br><p class='textFont'>" + wares_name + "</p></a></div>";
						var $new = $(htmlText);
						$("div#sr_wares").last().append($new);
					}
				},
				error: function() {

				}
			});
		}

		var nowPage = $(this).closest("ul.pagination").find("li.active").text();

		if (prevLi.next().text() == nowPage) {
			prevLi.addClass("disabled");
			nextLi.removeClass("disabled");
		} else if (nextLi.prev().text() == nowPage) {
			nextLi.addClass("disabled");
			prevLi.removeClass("disabled");
		} else {
			prevLi.removeClass("disabled");
			nextLi.removeClass("disabled");
		}
		prevLi.children("span").attr("title", nowPage);
		nextLi.children("span").attr("title", nowPage);
	});
});

/*添加商品展示动画效果*/
$(window).scroll(function() {
	var scrollT = document.documentElement.scrollTop || document.body.scrollTop;
	var backTop = $("#sr_wares").offset().top - $(window).height() / 2;
	if (scrollT > backTop) {
		$("#sr_wares .a3").addClass("animated bounceInUp show").removeClass("fade");
	}
});

/*设置页面框架元素高度*/
$(document).ready(function() {
	var num = $("#sr_wares").children("div").length;
	if (eval(num % 4) != 0) {
		num = parseInt(num / 4);
		num++;
	} else {
		num = parseInt(num / 4);
	}
	if ($("#sr_failed").css("display") == "none") {
		if (num < 4) {
			$("#sr_center_wares").height(eval(1500 + 100));
			$("#sr_left").height(eval(1500 + 250));
			$("#sr_center").height(eval(1500 + 250));
			$("#sr_right").height(eval(1500 + 250));
			$("#sr_container").height(eval(1500 + 420));
		} else {
			$("#sr_center_wares").height(eval(500 * num + 100));
			$("#sr_left").height(eval(500 * num + 250));
			$("#sr_center").height(eval(500 * num + 250));
			$("#sr_right").height(eval(500 * num + 250));
			$("#sr_container").height(eval(500 * num + 420));
		}

	} else {
		$("#sr_center_wares").height(eval(600 + 100));
		$("#sr_left").height(eval(600 + 250));
		$("#sr_center").height(eval(600 + 250));
		$("#sr_right").height(eval(600 + 250));
		$("#sr_container").height(eval(600 + 420));
	}
});
