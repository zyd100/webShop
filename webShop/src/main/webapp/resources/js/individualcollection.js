/*页面加载时请求并填充数据*/
$(document).ready(function() {
	var starData = JSON.parse('${stars}');

	$("div#ic_personal").attr("userName", JSON.parse('${userName}'));
	var pageCount = 1;
	//添加收藏商品信息
	for (var i = 0; i < starData.data.orderItemList.length; i++) {
		var orderItem_id = starData.data.orderItemList[i].id;
		var product_id = starData.data.orderItemList[i].productId;
		var product_image = starData.data.productList[i].image;
		var product_name = starData.data.orderItemList[i].productName;
		var product_price = starData.data.orderItemList[i].price;
		var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
			product_id +
			"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
			"</p></td><td>￥<span id='price'>" + product_price +
			"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
		$("tr.titleTr").after($new);
	}

	if (Number(eval(data.productMaxCount % 10)) == 0) {
		pageCount = Number(eval(data.productMaxCount / 10));
	} else {
		pageCount = Number(eval(data.productMaxCount / 10) + 1);
	}
	//设置翻页数量
	for (var i = pageCount; i > 1; i--) {
		var pageHtml = "<li><span>" + i + "</span></li>";
		var $new = $(pageHtml);
		$("li.pageOne").after($new);
	}
});

/*添加事件*/
$(document).ready(function() {
	/* 单项删除收藏商品 */
	$("#ic_wares").on("click", "button#deleteBtn", function() {
		var userName = $("div#c_personal").attr("userName");
		var delete_id = $(this).closest("tr").find("a").attr("product_id");
		var delete_orderId = $(this).closest("tr").find("a").attr("orderItem_id");

		var data = {
			"orderItemList": {
				"productId": delete_id,
				"id": delete_orderId,
				"userName": userName
			}
		};
		var delete_url = "${pageContext.request.contextPath }/user/{" + userName + "}/star";
		/*向后端提交删除数据*/
		$.ajax({
			type: "delete",
			url: delete_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#ic_wares table tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
						var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
						var product_id = JSON.parse(data).data.orderItemList[i].productId;
						var product_image = JSON.parse(data).data.productList[i].image;
						var product_name = JSON.parse(data).data.orderItemList[i].productName;
						var product_price = JSON.parse(data).data.orderItemList[i].price;
						var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
							product_id +
							"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
							"</p></td><td>￥<span id='price'>" + product_price +
							"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
						$("tr.titleTr").after($new);
					}
				}
			},
			error: function() {}
		});

	});

	/* 多项删除收藏商品 */
	$("div.checkedDelete").on("click", "button#checkedDeleteBtn", function() {
		var product_id = [];
		var userName = $("div#ic_personal").attr("userName");
		var index = 0;
		var data;
		var haveProduct = false;
		/* 记录选中商品id */
		$("input[name='checkedOne']:checkbox").each(function() {
			if ($(this).prop("checked")) {
				var delete_id = $(this).closest("tr").find("a").attr("product_id");
				var delete_orderId = $(this).closest("tr").find("a").attr("orderItem_id");

				product_id[index++] = {
					"productId": delete_id,
					"id": delete_orderId,
					"userName": userName
				};
				haveProduct = true;
			}
		});
		data = {
			"orderItemList": product_id
		};
		var delete_url = "${pageContext.request.contextPath }/user/{" + userName + "}/star";
		/*向后端提交删除数据*/
		if (haveProduct == true) {
			$.ajax({
				type: "delete",
				url: delete_url,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data, status) {
					if (JSON.parse(data).success) {
						$("#ic_wares table tr").not("tr.titleTr").remove();
						for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
							var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
							var product_id = JSON.parse(data).data.orderItemList[i].productId;
							var product_image = JSON.parse(data).data.productList[i].image;
							var product_name = JSON.parse(data).data.orderItemList[i].productName;
							var product_price = JSON.parse(data).data.orderItemList[i].price;
							var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
								product_id +
								"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
								"</p></td><td>￥<span id='price'>" + product_price +
								"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
							$("tr.titleTr").after($new);
						}
					}
				},
				error: function() {}
			});
		}
	});

	/* 查看商品详情 */
	$("div#ic_wares table tr").on("click", "td a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).attr("href", search_url);
	});

	/* 实现全选效果 */
	$("#ic_wares").on("click", "#allChecked", function() {
		if (this.checked) {
			$("input[name='checkedOne']:checkbox").each(function() {
				$(this).prop("checked", true)
			});
		} else {
			$("input[name='checkedOne']:checkbox").each(function() {
				$(this).prop("checked", false)
			});
		}
	});

	/* 打开个人页面 */
	$("div#ic_personal").on("click", "a#personal", function() {
		var userName = $("div#ic_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开购物车 */
	$("div#ic_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#ic_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开收藏页面 */
	$("div#ic_personal").on("click", "a#collection", function() {
		var userName = $("div#ic_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 退出登录 */
	$("div#ic_personal").on("click", "a#outLogin", function() {
		$.ajax({
			url: "${pageContext.request.contextPath }/users/logout",
			type: "put",
			success: function() {}
		});
	});
});

/*添加页面翻页事件*/
$(document).ready(function() {
	$("ul.pagination").on("click", "li", function() {
		var userName = $("div#ic_personal").attr("userName");

		var nowClickPage = $(this).find("span").text(); //现在点击的页数
		var nowActivePage = $(this).closest("ul.pagination").find("li.active"); //现在活跃的页
		var prevLi = $(this).closest("ul.pagination").find("li.prev"); //按钮：上一页
		var nextLi = $(this).closest("ul.pagination").find("li.next"); //按钮：下一页

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
					"limit": 12
				};
				var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars/{" + Number(prevPage) +
					"}/{" + 12 +
					"}";
				$.ajax({
					url: search_url,
					type: "get",
					data: product_text,
					success: function(data) {
						if (JSON.parse(data).success) {
							$("#ic_wares table tr").not("tr.titleTr").remove();
							for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
								var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
								var product_id = JSON.parse(data).data.orderItemList[i].productId;
								var product_image = JSON.parse(data).data.productList[i].image;
								var product_name = JSON.parse(data).data.orderItemList[i].productName;
								var product_price = JSON.parse(data).data.orderItemList[i].price;
								var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
									product_id +
									"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
									"</p></td><td>￥<span id='price'>" + product_price +
									"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
								$("tr.titleTr").after($new);
							}
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
					"limit": 12
				};
				var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars/{" + Number(nextPage) +
					"}/{" + 12 +
					"}";
				$.ajax({
					url: search_url,
					type: "get",
					data: product_text,
					success: function(data) {
						if (JSON.parse(data).success) {
							$("#ic_wares table tr").not("tr.titleTr").remove();
							for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
								var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
								var product_id = JSON.parse(data).data.orderItemList[i].productId;
								var product_image = JSON.parse(data).data.productList[i].image;
								var product_name = JSON.parse(data).data.orderItemList[i].productName;
								var product_price = JSON.parse(data).data.orderItemList[i].price;
								var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
									product_id +
									"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
									"</p></td><td>￥<span id='price'>" + product_price +
									"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
								$("tr.titleTr").after($new);
							}
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
				"limit": 12
			};
			var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars/{" + Number(nowClickPage) +
				"}/{" +
				12 + "}";
			$.ajax({
				url: search_url,
				type: "get",
				data: product_text,
				success: function(data) {
					if (JSON.parse(data).success) {
						$("#ic_wares table tr").not("tr.titleTr").remove();
						for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
							var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
							var product_id = JSON.parse(data).data.orderItemList[i].productId;
							var product_image = JSON.parse(data).data.productList[i].image;
							var product_name = JSON.parse(data).data.orderItemList[i].productName;
							var product_price = JSON.parse(data).data.orderItemList[i].price;
							var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
								product_id +
								"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
								"</p></td><td>￥<span id='price'>" + product_price +
								"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
							$("tr.titleTr").after($new);
						}
					}
				},
				error: function() {
					alert(1);
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

/*设置页面框架元素高度*/
$(document).ready(function() {
	var num = $("#ic_wares table tr").length;
	$("#ic_wares").height(eval((num - 1) * 200 + 280));
	$("#ic_left").height(eval((num - 1) * 200 + 500));
	$("#ic_center").height(eval((num - 1) * 200 + 500));
	$("#ic_right").height(eval((num - 1) * 200 + 500));
	$("#ic_container").height(eval((num - 1) * 200 + 685));
});
