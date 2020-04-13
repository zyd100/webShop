function initOrder(ordlidata){
	//添加购买商品信息
	$("div#op_wares table").find("tr").not("tr.titleTr").remove();
	for (var i = 0; i < ordlidata.orderItemList.length; i++) {
		var product_id = ordlidata.orderItemList[i].productId;
		var product_image =  ctxImg +ordlidata.productList[i].image;
		var product_name = ordlidata.productList[i].productName;
		var product_quantity = ordlidata.orderItemList[i].quantity;
		var product_price = ordlidata.productList[i].shopPrice;
		var trHtml = "<tr><td><a href='' product_id='" + product_id + "'><img src='" + product_image + "'></a><p>" +
			product_name + "</p></td><td><span id='count'>" + product_quantity + "</span></td><td>￥<span id='totalprice'>" +
			eval(product_price * product_quantity) + "</span></td><td>￥<span id='postage'>0</span></td></tr>";
		var $new = $(trHtml);
		$("tr.titleTr").after($new);
	}
	setPageHeight();
	calPrice();
}

function setPageHeight(){
	var num = $("#op_wares table tr").length;
	$("#op_wares").height(eval((num - 1) * 200 + 250));
	$("#op_left").height(eval((num - 1) * 200 + 470));
	$("#op_center").height(eval((num - 1) * 200 + 470));
	$("#op_right").height(eval((num - 1) * 200 + 470));
	$("#op_container").height(eval((num - 1) * 200 + 650));
}

function calPrice(){
	var price = 0;
	var postage = 0;
	$("div#op_wares").find("span#totalprice").each(function() {
		price += Number($(this).text());
	});
	$("div#op_wares").find("span#postage").each(function() {
		postage += Number($(this).text());
	});
	$("#nhr").text(eval(price + postage));
}

function getContacts(userName){
	var address_url = ctx+"/users/" + userName + "/contacts";
	$.ajax({
		type:"get",
		url: address_url,
		success: function(data) {
			if(JSON.parse(data).success){
				$("div.addressSlected select").find("option").remove();
				for (var i = 0; i < JSON.parse(data).data.length; i++) {
					var id = JSON.parse(data).data[i].id;
					var address = JSON.parse(data).data[i].contactAddress;
					var tel = JSON.parse(data).data[i].contactMobile;
					var name = JSON.parse(data).data[i].contactName;
					//地址选择下拉框 
					var addressHtml = "<option value='" + id + "'><p>地址：" + address + "  电话：" + tel + "  收货人：" + name +
					"</p></option>";
					var $new = $(addressHtml);
					if(i==0){
						$("div.addressSlected").children("select").val(id);
					}
					$("div.addressSlected").children("select").append($new);
					// 记录选择的地址信息用作提交 
					var addressDivHtml = "<div id='" + id + "' style='display: none;'><p>地址：<span id='address'>" + address +
						"</span></p><p>电话：<span id='tel'>" + tel + "</span></p><p>联系人：<span id='name'>" + name + "</span></p></div>";
					$new = $(addressDivHtml);
					$("div.addressSlected").append($new);
				}
			}
		}
	});
}

/* 页面加载是请求并填入数据 */
/*$(document).ready(function() {
	//添加购买商品信息
	for (var i = 0; i < ordlidata.orderItemList.length; i++) {
		var product_id = ordlidata.orderItemList[i].productId;
		var product_image = ordlidata.orderItemList[i].image;
		var product_name = ordlidata.orderItemList[i].productName;
		var product_quantity = ordlidata.orderItemList[i].quantity;
		var product_price = ordlidata.orderItemList[i].price;
		var trHtml = "<tr><td><a href='' product_id='" + product_id + "'><img src='" + product_image + "'></a><p>" +
			product_name + "</p></td><td><span id='count'>" + product_quantity + "</span></td><td>￥<span id='totalprice'>" +
			eval(product_price * product_quanity) + "</span></td><td>￥<span id='postage'>1</span></td></tr>";
		var $new = $(trHtml);
		$("tr.titleTr").after($new);
	}

});*/

/* 添加收货地址信息 */
/*$(document).ready(function() {
	var userName = $("#op_personal").attr("userName");
	var address_url = ctx+"/users/" + userName + "/contacts";
	$.ajax({
		type:"get",
		url: address_url,
		success: function(data) {
			if(JSON.parse(data).success){
				$("div.addressSlected select").find("option").remove();
				for (var i = 0; i < JSON.parse(data).data.length; i++) {
					var id = JSON.parse(data).data[i].id;
					var address = JSON.parse(data).data[i].contactAddress;
					var tel = JSON.parse(data).data[i].contactMobile;
					var name = JSON.parse(data).data[i].contactName;
					//地址选择下拉框 
					var addressHtml = "<option value='" + id + "'><p>地址：" + address + "  电话：" + tel + "  收货人：" + name +
					"</p></option>";
					var $new = $(addressHtml);
					$("div.addressSlected").children("select").append($new);
					// 记录选择的地址信息用作提交 
					var addressDivHtml = "<div id='" + id + "' style='display: none;'><p>地址：<span id='address'>" + address +
						"</span></p><p>电话：<span id='tel'>" + tel + "</span></p><p>联系人：<span id='name'>" + name + "</span></p></div>";
					$new = $(addressDivHtml);
					$("div.addressSlected").append($new);
				}
			}
		}
	});
});*/

/* 添加事件 */
$(document).ready(function() {
	/* 查看订单商品详情 */
	$('div#op_wares table').on("click", "td a", function() {
		var product_id = $(this).attr("product_id");
		var search_url = ctx+"/products/" + product_id;
		$(this).attr("href", search_url);
		$(this).attr("target", "_blank");
	});

	/* 立即支付点击事件 */
	$("div#op_wares").on("click", "button#payNow", function() {
		var address_id = $("div.addressSlected").children("select").val();
		var userName = $("div#op_personal").attr("userName");
		var product = [];
		var index = 0;
		var data;
		//保存商品信息
		$("div#op_wares table").find("td a").each(function() {
			var product_id = $(this).attr("product_id");
			var product_count = Number($(this).closest("tr").find("span#count").text());
			product[index++] = {
				"productId": product_id,
				"quantity": product_count
			};
		});
		//保存地址信息
		var addressInfor = $("div.addressSlected").find("div#" + address_id);
		var address = addressInfor.find("span#address").text();
		var tel = addressInfor.find("span#tel").text();
		var name = addressInfor.find("span#name").text();
		var contact = {
			"contactAddress": address,
			"contactMobile": tel,
			"contactName": name,
			"userName": userName,
			"message": ""
		};
		data = {
			"orderItemList": product,
			"orderInfo": contact
		}
		var order_url = ctx+"/users/" + userName + "/order";
		/*向后端提交数据*/
		$.ajax({
			type: "post",
			url: order_url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success == true) {
					var orderNum = JSON.parse(data).data.orderNum;
					var stateInfo = JSON.parse(data).data.stateInfo;
					alert("订单" + orderNum + stateInfo + "  详情请到历史订单页面查看");
					window.close();
				}
			}
		});
	});

	/* 打开个人页面 */
	$("div#op_personal").on("click", "a#personal", function() {
		var userName = $("div#op_personal").attr("userName");
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
	$("div#op_personal").on("click", "a#shoppingCart", function() {
		var userName = $("div#op_personal").attr("userName");
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
	$("div#op_personal").on("click", "a#collection", function() {
		var userName = $("div#op_personal").attr("userName");
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
	$("div#op_personal").on("click", "a#outLogin", function() {
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
/*		地址选择
var obj=$("div.addressSlected").children("select").val();*/

/* 计算支付金额 */
$(document).ready(function() {
	calPrice();
});

/* 设置页面高度 */
$(document).ready(function() {
	setPageHeight();
});
