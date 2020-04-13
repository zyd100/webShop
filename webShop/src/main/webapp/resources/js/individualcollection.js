function initStar(starData){
	var pageCount = 1;
	//添加收藏商品信息
	$("div#ic_wares").find("tr").not("tr.titleTr").remove();
	for (var i = 0; i < starData.data.orderItemList.length; i++) {
		var orderItem_id = starData.data.orderItemList[i].id;
		var product_id = starData.data.orderItemList[i].productId;
		var product_image = ctxImg +starData.data.productList[i].image;
		var product_name = starData.data.orderItemList[i].productName;
		var product_price = starData.data.orderItemList[i].price;
		var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
			product_id +
			"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
			"</p></td><td>￥<span id='price'>" + product_price +
			"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
		var $new=$(trHtml);
		$("tr.titleTr").after($new);
	}
	$("div#ic_paging").attr("page", 1);
	
	setPageHeight();
}

function setPageHeight(){
	var num = $("#ic_wares table tr").length;
	$("#ic_wares").height(eval((num - 1) * 200 + 280));
	$("#ic_left").height(eval((num - 1) * 200 + 500));
	$("#ic_center").height(eval((num - 1) * 200 + 500));
	$("#ic_right").height(eval((num - 1) * 200 + 500));
	$("#ic_container").height(eval((num - 1) * 200 + 685));
}

/*页面加载时请求并填充数据*/
/*$(document).ready(function() {
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
});*/

/*添加事件*/
$(document).ready(function() {
	/* 单项删除收藏商品 */
	$("#ic_wares").on("click", "button#deleteBtn", function() {
		var userName = $("div#ic_personal").attr("userName");
		if(userName==""){
			alert("请先登录！！！");
			return;
		}

		var delete_id = $(this).closest("tr").find("a").attr("product_id");
		var delete_orderId = $(this).closest("tr").find("a").attr("orderItem_id");
		
		var data = {
			"orderItemList": {
				"productId": delete_id,
				"id": delete_orderId,
				"userName": userName
			}
		};
		var delete_url = ctx+"/users/" + userName + "/stars";
		/*向后端提交删除数据*/
		$.ajax({
			url: delete_url,
			type: "delete",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("删除成功！！！");
					$("#ic_wares table tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
						var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
						var product_id = JSON.parse(data).data.orderItemList[i].productId;
						var product_image =  ctxImg +JSON.parse(data).data.productList[i].image;
						var product_name = JSON.parse(data).data.orderItemList[i].productName;
						var product_price = JSON.parse(data).data.orderItemList[i].price;
						var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
							product_id +
							"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
							"</p></td><td>￥<span id='price'>" + product_price +
							"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
						var $new=$(trHtml);
						$("tr.titleTr").after($new);
					}
					$("div#ic_paging").attr("page", 1);
					
					setPageHeight();
				}
			}
		});
	});

	/* 多项删除收藏商品 */
	$("div.checkedDelete").on("click", "button#checkedDeleteBtn", function() {
		var userName = $("div#ic_personal").attr("userName");
		if(userName==""){
			alert("请先登录！！！");
			return;
		}
		var product_id = [];
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
		var delete_url = ctx+"/users/" + userName + "/stars";
		/*向后端提交删除数据*/
		if (haveProduct == true) {
			$.ajax({
				type: "delete",
				url: delete_url,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data) {
					if (JSON.parse(data).success) {
						alert("删除成功！！！");
						$("#ic_wares table tr").not("tr.titleTr").remove();
						for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
							var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
							var product_id = JSON.parse(data).data.orderItemList[i].productId;
							var product_image =  ctxImg +JSON.parse(data).data.productList[i].image;
							var product_name = JSON.parse(data).data.orderItemList[i].productName;
							var product_price = JSON.parse(data).data.orderItemList[i].price;
							var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
								product_id +
								"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
								"</p></td><td>￥<span id='price'>" + product_price +
								"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
							var $new=$(trHtml);
							$("tr.titleTr").after($new);
						}
						$("div#ic_paging").attr("page", 1);
						
						setPageHeight();
					}
				}
			});
		}else{
			alert("没有选中商品呢~");
		}
	});

	/* 查看商品详情 */
	$("div#ic_wares table").on("click", "td a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = ctx+"/products/" + product_id;
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
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

	/* 打开购物车 */
	$("div#ic_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#ic_personal").attr("userName");
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

	/* 打开收藏页面 */
	$("div#ic_personal").on("click", "a#collection", function() {
		var userName = $("div#ic_personal").attr("userName");
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
	$("div#ic_personal").on("click", "a#outLogin", function() {
		/*$.ajax({
			url: ctx+"/users/logout",
			type: "post",
			success: function() {}
		});*/
		var out_url=ctx+"/users/logout";
		$("form#logOutForm").attr("action",out_url);
		$("form#logOutForm").submit();
	});
});

/*添加页面翻页事件*/
$(document).ready(function() {
	/* 向前翻页 */
	$("div#ic_paging nav").on("click", "li.prevPage a", function() {
		var userName = $("div#ic_personal").attr("userName");
		var nowPage = $("div#ic_paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var offset=(--nowPage - 1)*12;
		var product_text = {
				"offset": offset,
				"limit": 12
			};
		var prev_url = ctx+"/users/" + userName + "/stars/" + offset +"/12";
		$.ajax({
			type: "get",
			url: prev_url,
			data: product_text,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#ic_wares tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
						var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
						var product_id = JSON.parse(data).data.orderItemList[i].productId;
						var product_image = ctxImg +JSON.parse(data).data.productList[i].image;
						var product_name = JSON.parse(data).data.orderItemList[i].productName;
						var product_price = JSON.parse(data).data.orderItemList[i].price;
						var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
							product_id +
							"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
							"</p></td><td>￥<span id='price'>" + product_price +
							"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
						var $new=$(trHtml);
						$("tr.titleTr").after($new);
					}
					$("div#ic_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});

	/* 向后翻页 */
	$("div#ic_paging nav").on("click", "li.nextPage a", function() {
		var userName = $("div#ic_personal").attr("userName");
		var nowPage = $("div#ic_paging").attr("page");
		var offset=(++nowPage - 1)*12;
		var product_text = {
				"offset": offset,
				"limit": 12
			};
		var next_url = ctx+"/users/" + userName + "/stars/" + offset +"/12";
		$.ajax({
			type: "get",
			url: next_url,
			data: product_text,
			success: function(data) {
				if (JSON.parse(data).success) {
					if(JSON.parse(data).data.orderItemList.length==0){
						alert("已经是最后一页了~");
						$("div#ic_paging").attr("page", (--nowPage));
						return;
					}
					$("#ic_wares tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.orderItemList.length; i++) {
						var orderItem_id = JSON.parse(data).data.orderItemList[i].id;
						var product_id = JSON.parse(data).data.orderItemList[i].productId;
						var product_image =  ctxImg +JSON.parse(data).data.productList[i].image;
						var product_name = JSON.parse(data).data.orderItemList[i].productName;
						var product_price = JSON.parse(data).data.orderItemList[i].price;
						var trHtml = "<tr><td><input type='checkbox' name='checkedOne' /></td><td><a href='' product_id = '" +
							product_id +
							"' orderItem_id='" + orderItem_id + "'><img src='" + product_image + "'></a><p>" + product_name +
							"</p></td><td>￥<span id='price'>" + product_price +
							"</span></td><td><button type='button' id='deleteBtn' class='glyphicon glyphicon-trash'>删除</button></td></tr>";
						var $new=$(trHtml);
						$("tr.titleTr").after($new);
					}
					$("div#ic_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});
});

/*设置页面框架元素高度*/
$(document).ready(function() {
	setPageHeight();
});
