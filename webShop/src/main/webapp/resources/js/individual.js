function initUserInfo(infordata){
	// 添加个人信息数据 
	var portraitImg=ctxImg +infordata.user.image;
	$("div.headImg a").find("img.img-circle").attr("src", portraitImg);
	$("span#nickName").text(infordata.user.nickName);
	$("span#name").text(infordata.user.userName);
	$("span#inforEmail").text(infordata.user.email);
	$("span#inforuName").text(infordata.user.nickName);
	$("span#registerTime").text(infordata.user.registerTime);

}

function initOrderInfo(orderInfo){
	//添加历史订单信息数据
	$("div#order table").find("tr").not("tr.titleTr").remove();
	for (var i = 0; i < orderInfo.length; i++) {
		var product_id = orderInfo[i].orderItemList[0].productId;
		var product_image =  ctxImg +orderInfo[i].productList[0].image;
		var product_name = orderInfo[i].orderItemList[0].productName;
		var order_count = orderInfo[i].orderItemList[0].quantity;
		var order_price = orderInfo[i].orderItemList[0].price;
		var order_id = orderInfo[i].orderInfo.orderNum;
		var trHtml = "<tr><td><a class='pro' href='' product_id='" + product_id + "'><img src='" + product_image +
			"'></a><p>" +
			product_name + "</p></td><td><span id='count'>" + order_count + "</span></td><td>￥<span id='totalprice'>" +
			order_price + "</span></td><td><a class='ord' href='' order_id='" + order_id +
			"' title='订单详细信息'><span id='orderID'>" +
			order_id + "</span></a></td></tr>";
		var $new = $(trHtml);
		$("div#order").find("table").append($new);
	}
	$("div#i_paging").attr("page", 1);
	setPageHeight();
}

function initConInfo(contactInfo){
	$("div.inforShow").find("div.addressShow").remove();
	for (var i = 0; i < contactInfo.length; i++) {
		var id = contactInfo[i].id;
		var address = contactInfo[i].contactAddress;
		var tel = contactInfo[i].contactMobile;
		var name = contactInfo[i].contactName;
		var addressHtml =
			"<div class='addressShow'><p class='inforContent'>地址：<span id='inforAddress' class='contentFomat'>" +
			address +
			"</span><br>电话：<span id='inforTel' class='contentFomat'>" + tel +
			"</span>收货人：<span id='inforName' class='contentFomat'>" + name +
			"</span><a href='javascript:;' contact_id='" + id +
			"' id='editAddress' class='glyphicon glyphicon-edit' title='编辑'></a>" +
			"<a href='javascript:;' contact_id='" + id +
			"' id='deleteAddress' class='glyphicon glyphicon-minus-sign' title='删除'></a></p></div>";
		var $new = $(addressHtml);
		$("p.addressTitle").after($new);
	}
}

function setPageHeight(){
	var num = $("div#order table tr").length;
	if (num > 5) {
		$("#i_detail").height(eval((num - 1) * 200 + 200));
		$("#i_left").height(eval((num - 1) * 200 + 555));
		$("#i_center").height(eval((num - 1) * 200 + 555));
		$("#i_right").height(eval((num - 1) * 200 + 555));
		$("#i_container").height(eval((num - 1) * 200 + 745));
	}
}
/* 地址修改信息初始化 */
/*function ucontact(obj){
	$("div#modContactDiv").find("input#InputAddress").val(obj.find("span#inforAddress").text());
	$("div#modContactDiv").find("input#InputTel").val(obj.find("span#inforTel").text());
	$("div#modContactDiv").find("input#InputAddressName").val(obj.find("span#inforName").text());
}*/

/* 页面加载时请求并填充数据 */
/*$(document).ready(function() {
	var pageCount = 1;
	// 添加个人信息数据 
	$("div.headImg img.img-circle").attr("src", infordata.user.image);
	$("span#nickName").text(infordata.user.nickName);
	$("span#name").text(infordata.user.userName);
	$("span#inforEmail").text(infordata.user.email);
	$("span#inforuName").text(infordata.user.nickName);
	$("span#registerTime").text(infordata.user.registerTime);

	
	//添加历史订单信息数据
	for (var i = 0; i < orderInfo.orderInfos.length; i++) {
		var product_id = orderInfo.orderInfos[i].product_id;
		var product_image = orderInfo.orderInfos[i].product_image;
		var product_name = orderInfo.orderInfos[i].product_name;
		var order_count = orderInfo.orderInfos[i].order_count;
		var order_price = orderInfo.orderInfos[i].order_price;
		var order_id = orderInfo.orderInfos[i].order_id;
		var trHtml = "<tr><td><a class='pro' href='' product_id='" + product_id + "'><img src='" + product_image +
			"'></a><p>" +
			product_name + "</p></td><td><span id='count'>" + order_count + "</span></td><td>￥<span id='totalprice'>" +
			order_price + "</span></td><td><a class='ord' href='' order_id='" + order_id +
			"' title='订单详细信息'><span id='orderID'>" +
			order_id + "</span></a></td></tr>";
		var $new = $(trHtml);
		$("tr.titleTr").after($new);
	}
	if (Number(eval(data.orderMaxCount % 10)) == 0) {
		pageCount = parseInt(eval(data.orderMaxCount / 10));
	} else {
		pageCount = parseInt(eval(data.orderMaxCount / 10) + 1);
	}
	//设置翻页数量
	for (var i = pageCount; i > 1; i--) {
		var pageHtml = "<li><span>" + i + "</span></li>";
		var $new = $(pageHtml);
		$("li.pageOne").after($new);
	}
});*/

/* 添加收货地址信息 */
/*$(document).ready(function() {
	var userName = $("div#i_personal").attr("userName");
	var address_url = ctx+"/users/" + userName + "/contacts";
	$.ajax({
		type:"get",
		url: address_url,
		success: function(data) {
			if (JSON.parse(data).success) {
				for (var i = 0; i < JSON.parse(data).contacts.length; i++) {
					var id = JSON.parse(data).contacts[i].id;
					var address = JSON.parse(data).contacts[i].contactAddress;
					var tel = JSON.parse(data).contacts[i].contactMobile;
					var name = JSON.parse(data).contacts[i].contactName;
					var addressHtml =
						"<div class='addressShow'><p class='inforContent'>地址：<span id='inforAddress' class='contentFomat'>" +
						address +
						"</span><br>电话：<span id='inforTel' class='contentFomat'>" + tel +
						"</span>收货人：<span id='inforName' class='contentFomat'>" + name +
						"</span><a href='javascript:;' contact_id='" + id +
						"' id='editAddress' class='glyphicon glyphicon-edit' title='编辑'></a>" +
						"<a href='javascript:;' contact_id='" + id +
						"' id='deleteAddress' class='glyphicon glyphicon-minus-sign' title='删除'></a></p></div>";
					var $new = $(addressHtml);
					$("p.addressTitle").after($new);
				}
			}
		},
		error: function() {

		}
	});
});*/

/* 添加事件 */
$(document).ready(function() {
	/* 查看历史订单商品详情 */
	$("div#order table").on("click", "td a.pro", function() {
		var product_id = $(this).attr("product_id");
		var search_url = ctx+"/products/" + product_id;
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 查看该历史订单详情 */
	$("div#order table").on("click", "td a.ord", function() {
		var order_id = $(this).attr("order_id");
		var userName = $("div#i_personal").attr("userName");
		var search_url = ctx+"/users/" + userName + "/orderHistory/" + order_id;
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 打开个人收藏页面 */
	$("div#i_detail ul").on("click", "li.collect", function() {
		var userName = $("div#i_personal").attr("userName");
		var collect_url = ctx+"/users/" + userName + "/stars";
		$(this).find("a").attr("href", collect_url);
		$(this).find("a").attr("target", "_blank");
	});

	/* 打开购物车 */
	$("div#i_detail ul").on("click", "li.cart", function() {
		var userName = $("div#i_personal").attr("userName");
		var cart_url = ctx+"/users/" + userName + "/carts";
		$(this).find("a").attr("href", cart_url);
		$(this).find("a").attr("target", "_blank");
	});

	/* 打开个人页面 */
	$("div#i_personal").on("click", "a#personal", function() {
		var userName = $("div#i_personal").attr("userName");
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
	$("div#i_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#i_personal").attr("userName");
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
	$("div#i_personal").on("click", "a#collection", function() {
		var userName = $("div#i_personal").attr("userName");
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
	$("div#i_personal").on("click", "a#outLogin", function() {
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

/* 修改信息事件 */
$(document).ready(function() {
	/* 更改头像 */
	$("div#modPortraitDiv").on("click", "button#modProtraitSub", function() {
		var formdata = new FormData($("#modProtraitForm")[0]);
		var userName = $("div#i_personal").attr("userName");
		var modProtrait_url =ctx+"/users/" + userName + "/images";
		$.ajax({
			url: modProtrait_url,
			data: formdata,
			type: "post",
			dataType: "json",
			cache: false,
			processData: false,
			contentType: false,
			async: false,
			success: function(data) {
				if (JSON.parse(data).success) {
					location.reload();
					alert("修改成功");
				}
			}
		});
	});

	/* 修改信息原始数据填充 */
	$("div#personsalInfor").on("click", "a#editName", function() {
		$("input#InputEditNickName").val($("span#inforuName").text());
		$("input#InputEditName").val($("span#name").text());
		$("input#InputEditEmail").val($("span#inforEmail").text());
	});

	/* 更改个人信息 */
	$("div.editInfor").on("click", "button#editNameSub", function() {
		var userName = $("div#i_personal").attr("userName");

		var nickname = $("input#InputEditNickName").val();
		var name = $("input#InputEditName").val();
		var email = $("input#InputEditEmail").val();
		var data = {
			"userName": name,
			"nickName": nickname,
			"email": email
		}
		var update_url = ctx+"/users/" + userName;
		$.ajax({
			type: "put",
			url: update_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					$("span#nickName").text(JSON.parse(data).data.user.nickName);
					$("span#name").text(JSON.parse(data).data.user.userName);
					$("span#inforEmail").text(JSON.parse(data).data.user.email);
					$("span#inforuName").text(JSON.parse(data).data.user.nickName);
				}

			},
			error: function() {

			}
		});
	});

	/* 新增地址信息 */
	$("div.addAddress").on("click", "button#addAddressSub", function() {
		var userName = $("div#i_personal").attr("userName");

		var address = $("#InputAddress").val();
		var tel = $("#InputTel").val();
		var name = $("#InputAddressName").val();
		var data = {
			"userName": userName,
			"contactName": name,
			"contactAddress": address,
			"contactMobile": tel
		}
		var add_url = ctx+"/users/" + userName + "/contacts";
		$.ajax({
			type: "post",
			url: add_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					var id = JSON.parse(data).data.id;
					var address = JSON.parse(data).data.contactAddress;
					var tel = JSON.parse(data).data.contactMobile;
					var name = JSON.parse(data).data.contactName;
					var addressHtml =
						"<div class='addressShow'><p class='inforContent'>地址：<span id='inforAddress' class='contentFomat'>" +
						address +
						"</span><br>电话：<span id='inforTel' class='contentFomat'>" + tel +
						"</span>收货人：<span id='inforName' class='contentFomat'>" + name +
						"</span><a href='javascript:;' contact_id='" + id +
						"' id='editAddress' class='glyphicon glyphicon-edit' title='编辑'></a>" +
						"<a href='javascript:;' contact_id='" + id +
						"' id='deleteAddress' class='glyphicon glyphicon-minus-sign' title='删除'></a></p></div>";
					var $new = $(addressHtml);
					$("p.addressTitle").after($new);
				}
			}
		});
		$("#InputAddress").val("");
		$("#InputTel").val("");
		$("#InputAddressName").val("");
	});

	/* 更改地址信息 */
	/*$("div.addressShow").on("click","a#editAddress",function(){
		ucontact($(this).closest("div.addressShow"));
		
		var contact_id=$(this).attr("contact_id");
	});*/

	/* 删除地址信息 */
	$(".inforShow").on("click", "a#deleteAddress", function() {
		var userName = $("div#i_personal").attr("userName");
		var divObj=$(this).closest("div.addressShow");
		var address_id = $(this).attr("contact_id");
		var data = {
			"id": address_id,
			"userName": userName
		}
		var delete_url = ctx+"/users/" + userName + "/contacts";
		$.ajax({
			type: "delete",
			url: delete_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					divObj.remove();
					alert("删除成功");
				}
			}
		});
	});

});

/* 历史订单翻页事件 */
$(document).ready(function() {
	/* 向前翻页 */
	$("div#i_paging nav").on("click", "li.prevPage a", function() {
		var userName = $("div#i_personal").attr("userName");
		var nowPage = $("div#i_paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var product_text = {
				"offset": (--nowPage - 1)*10,
				"limit": 10
			};
		var prev_url = ctx+"/users/" + userName + "/orderHistory";
		$.ajax({
			type: "get",
			url: prev_url,
			data:product_text,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("div#order table").find("tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var product_id =JSON.parse(data).data[i].orderItemList[0].productId;
						var product_image =  ctxImg +JSON.parse(data).data[i].productList[0].image;
						var product_name = JSON.parse(data).data[i].orderItemList[0].productName;
						var order_count = JSON.parse(data).data[i].orderItemList[0].quantity;
						var order_price = JSON.parse(data).data[i].orderItemList[0].price;
						var order_id = JSON.parse(data).data[i].orderInfo.orderNum;
						var trHtml = "<tr><td><a class='pro' href='' product_id='" + product_id + "'><img src='" + product_image +
							"'></a><p>" +
							product_name + "</p></td><td><span id='count'>" + order_count +
							"</span></td><td>￥<span id='totalprice'>" +
							order_price + "</span></td><td><a class='ord' href='' order_id='" + order_id +
							"' title='订单详细信息'><span id='orderID'>" +
							order_id + "</span></a></td></tr>";
						var $new = $(trHtml);
						$("div#order").find("table").append($new);
					}
					$("div#i_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});

	/* 向后翻页 */
	$("div#i_paging nav").on("click", "li.nextPage a", function() {
		var userName = $("div#i_personal").attr("userName");
		var nowPage = $("div#i_paging").attr("page");
		var product_text = {
				"offset": (++nowPage - 1)*10,
				"limit": 10
			};
		var next_url = ctx+"/users/" + userName + "/orderHistory";
		$.ajax({
			type: "get",
			url: next_url,
			data:product_text,
			success: function(data) {
				if (JSON.parse(data).success) {
					if(JSON.parse(data).data.length==0){
						alert("已经是最后一页了~");
						$("div#i_paging").attr("page", (--nowPage));
						return;
					}
					$("div#order table").find("tr").not("tr.titleTr").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var product_id =JSON.parse(data).data[i].orderItemList[0].productId;
						var product_image =  ctxImg +JSON.parse(data).data[i].productList[0].image;
						var product_name = JSON.parse(data).data[i].orderItemList[0].productName;
						var order_count = JSON.parse(data).data[i].orderItemList[0].quantity;
						var order_price = JSON.parse(data).data[i].orderItemList[0].price;
						var order_id = JSON.parse(data).data[i].orderInfo.orderNum;
						var trHtml = "<tr><td><a class='pro' href='' product_id='" + product_id + "'><img src='" + product_image +
							"'></a><p>" +
							product_name + "</p></td><td><span id='count'>" + order_count +
							"</span></td><td>￥<span id='totalprice'>" +
							order_price + "</span></td><td><a class='ord' href='' order_id='" + order_id +
							"' title='订单详细信息'><span id='orderID'>" +
							order_id + "</span></a></td></tr>";
						var $new = $(trHtml);
						$("div#order").find("table").append($new);
					}
					$("div#i_paging").attr("page", nowPage);
					setPageHeight();
				}
			}
		});
	});
});

/* 设置页面高度 */
$(document).ready(function() {
	setPageHeight();
});
