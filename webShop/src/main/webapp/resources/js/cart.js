/*页面加载时请求并填充数据*/
$(document).ready(function() {
	var cartData = JSON.parse('${carts}');

	$("div#c_personal").attr("userName", JSON.parse('${userName}'));
	//添加购物车商品
	for (var i = 0; i < cartData.data.orderItemList.length; i++) {
		var orderItem_id = cartData.data.orderItemList[i].id;
		var product_id = cartData.data.orderItemList[i].productId;
		var product_image = cartData.data.productList[i].image;
		var product_name = cartData.data.orderItemList[i].productName;
		var product_quanity = cartData.data.orderItemList[i].quantity;
		var product_price = cartData.data.orderItemList[i].price;
		var trHtml = "<tr><td><input type='checkbox' name='checkedOne' id='' value='' /></td><td><a href='' product_id='" +
			product_id +
			"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
			"</p></td><td>￥<span id='price'>" + product_price +
			"</span></td><td><input type='number' id='count' min='1' value='" + Number(product_quanity) +
			"' contenteditable='false' /></td><td>￥<span id='totalprice'></span></td><td>" +
			"<button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
		var $new = $(trHtml);
		$("tr.titleTr").after($new);
	}
});

/*添加事件*/
$(document).ready(function() {
	/*单项删除购物车商品*/
	$("#c_wares").on("click", "button#deleteBtn", function() {
		/*计算购物车金额*/
		if ($(this).closest("tr").find("input[name='checkedOne']:checkbox").prop("checked")) {
			var totalprice = Number($(this).closest("tr").find("span#totalprice").text());
			var count = Number($(this).closest("tr").find("input#count").val());
			var nowCount = Number($("span#waresCount").text());
			var nowfee = Number($("span#fee").text());
			$("span#waresCount").text(eval(nowCount - count));
			$("span#fee").text(eval(nowfee - totalprice));
		}
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
		var delete_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		/*向后端提交删除数据*/
		$.ajax({
			type: "delete",
			url: delete_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#c_wares table tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
						var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
						var product_id = JSON.parse(data).data.orderItemList[i].productId;
						var product_image = JSON.parse(data).data.productList[i].image;
						var product_name = JSON.parse(data).data.orderItemList[i].productName;
						var product_quanity = JSON.parse(data).data.orderItemList[i].quantity;
						var product_price = JSON.parse(data).data.orderItemList[i].price;
						var trHtml =
							"<tr><td><input type='checkbox' name='checkedOne' id='' value='' /></td><td><a href='' product_id='" +
							product_id +
							"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
							"</p></td><td>￥<span id='price'>" + product_price +
							"</span></td><td><input type='number' id='count' min='1' value='" + Number(product_quanity) +
							"' contenteditable='false' /></td><td>￥<span id='totalprice'></span></td><td>" +
							"<button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
						var $new = $(trHtml);
						$("tr.titleTr").after($new);
					}
				}
			},
			error: function() {}
		});

	});

	/*多项删除购物车商品*/
	$("div.checkedDelete").on("click", "button#checkedDeleteBtn", function() {
		var product_id = [];
		var userName = $("div#c_personal").attr("userName");
		var index = 0;
		var data;
		var haveProduct = false;
		/*记录选中的商品id	计算购物车金额*/
		$("input[name='checkedOne']:checkbox").each(function() {
			if ($(this).prop("checked")) {
				var totalprice = Number($(this).closest("tr").find("span#totalprice").text());
				var count = Number($(this).closest("tr").find("input#count").val());
				var nowCount = Number($("span#waresCount").text());
				var nowfee = Number($("span#fee").text());
				$("span#waresCount").text(eval(nowCount - count));
				$("span#fee").text(eval(nowfee - totalprice));

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
		var delete_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		/*向后端提交删除数据*/
		if (haveProduct == true) {
			$.ajax({
				type: "delete",
				url: delete_url,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data) {
					if (JSON.parse(data).success) {
						$("#c_wares table tr").not("tr.titleTr").remove();
						for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
							var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
							var product_id = JSON.parse(data).data.orderItemList[i].productId;
							var product_image = JSON.parse(data).data.productList[i].image;
							var product_name = JSON.parse(data).data.orderItemList[i].productName;
							var product_quanity = JSON.parse(data).data.orderItemList[i].quantity;
							var product_price = JSON.parse(data).data.orderItemList[i].price;
							var trHtml =
								"<tr><td><input type='checkbox' name='checkedOne' id='' value='' /></td><td><a href='' product_id='" +
								product_id +
								"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
								"</p></td><td>￥<span id='price'>" + product_price +
								"</span></td><td><input type='number' id='count' min='1' value='" + Number(product_quanity) +
								"' contenteditable='false' /></td><td>￥<span id='totalprice'></span></td><td>" +
								"<button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
							var $new = $(trHtml);
							$("tr.titleTr").after($new);
						}
					}
				},
				error: function() {}
			});
		}
	});

	/*选中单项时计算购物车金额*/
	$("#c_wares").on("click", "input[name='checkedOne']:checkbox", function() {
		var totalprice = Number($(this).closest("tr").find("span#totalprice").text());
		var count = Number($(this).closest("tr").find("input#count").val());
		var nowCount = Number($("span#waresCount").text());
		var nowfee = Number($("span#fee").text());
		if ($(this).prop("checked")) {
			$("span#waresCount").text(eval(nowCount + count));
			$("span#fee").text(eval(nowfee + totalprice));
		} else {
			$("span#waresCount").text(eval(nowCount - count));
			$("span#fee").text(eval(nowfee - totalprice));
		}
	});

	/*全选时计算购物车金额*/
	$("#c_wares").on("click", "#allChecked", function() {
		if (this.checked) {
			$("input[name='checkedOne']:checkbox").each(function() {
				var totalprice = Number($(this).closest("tr").find("span#totalprice").text());
				var count = Number($(this).closest("tr").find("input#count").val());
				var nowCount = Number($("span#waresCount").text());
				var nowfee = Number($("span#fee").text());
				if ($(this).prop("checked") == false) {
					$("span#waresCount").text(eval(nowCount + count));
					$("span#fee").text(eval(nowfee + totalprice));
				}
				$(this).prop("checked", true)
			});
		} else {
			$("input[name='checkedOne']:checkbox").each(function() {
				var totalprice = Number($(this).closest("tr").find("span#totalprice").text());
				var count = Number($(this).closest("tr").find("input#count").val());
				var nowCount = Number($("span#waresCount").text());
				var nowfee = Number($("span#fee").text());
				$(this).prop("checked", false)
				$("span#waresCount").text(eval(nowCount - count));
				$("span#fee").text(eval(nowfee - totalprice));
			});
		}
	});

	/*计算每项商品的购买金额*/
	$("#totalprice").each(function() {
		var price = Number($(this).closest("tr").find("span#price").text());
		var count = $(this).closest("tr").find("input#count").val();
		$(this).text(eval(price * count));
	});

	/*立即支付点击事件*/
	$("div.waresCount").on("click", "button#orderNow", function() {
		var product = [];
		var userName = $("div#c_personal").attr("userName");
		var index = 0;
		var data;
		var haveProduct = false;
		/*记录所选商品id及购买数量*/
		$("input[name='checkedOne']:checkbox").each(function() {
			if ($(this).prop("checked")) {
				var checked_id = $(this).closest("tr").find("a").attr("product_id");
				var checked_orderid = $(this).closest("tr").find("a").attr("orderItem_id");
				var checked_count = $(this).closest("tr").find("input#count").val();
				product[index++] = {
					"id": checked_orderid,
					"productId": checked_id,
					"quantity": checked_count,
					"userName": userName
				};
				haveProduct = true;
			}
		});
		data = {
			"userName": userName,
			"orderItemList": product
		};
		var order_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts/order";
		/*向后端提交数据*/
		if (haveProduct == true) {
			$.ajax({
				type: "get",
				url: order_url,
				data: data,
				success: function(data, status) {},
				error: function() {}
			});
		}

	});

	/*购物车每项商品数量的点击事件		计算该项商品金额*/
	$("#c_wares").on("click", "input#count", function() {
		var price = Number($(this).closest("tr").find("span#price").text());
		var count = $(this).val();
		var totalprice = price * count;
		$(this).closest("tr").find("span#totalprice").text(totalprice);
	});

	/*查看商品详情*/
	$("div#c_wares table tr").on("click", "td a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = "${pageContext.request.contextPath }/products/{" + product_id + "}";
		$(this).attr("href", search_url);
	});

	/* 打开个人页面 */
	$("div#c_personal").on("click", "a#personal", function() {
		var userName = $("div#c_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开购物车 */
	$("div#c_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#c_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/carts";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 打开收藏页面 */
	$("div#c_personal").on("click", "a#collection", function() {
		var userName = $("div#c_personal").attr("userName");
		var search_url = "${pageContext.request.contextPath }/users/{" + userName + "}/stars";
		$.ajax({
			url: search_url,
			type: "get",
			success: function() {

			}
		});
	});

	/* 退出登录 */
	$("div#c_personal").on("click", "a#outLogin", function() {
		$.ajax({
			url: "${pageContext.request.contextPath }/users/logout",
			type: "put",
			success: function() {}
		});
	});
});

/*设置页面框架元素高度*/
$(document).ready(function() {
	var num = $("#c_wares table tr").length;
	$("#c_wares").height(eval((num - 1) * 200 + 280));
	$("#c_left").height(eval((num - 1) * 200 + 500));
	$("#c_center").height(eval((num - 1) * 200 + 500));
	$("#c_right").height(eval((num - 1) * 200 + 500));
	$("#c_container").height(eval((num - 1) * 200 + 685));
});
