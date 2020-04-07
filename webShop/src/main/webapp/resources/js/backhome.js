/* 保存评论状态枚举 */
var accessStatus;

/* 保存订单状态枚举 */
var orderStatus;

/* 得到状态值 */
function getStateInfo(statetype, stateval) {
	if (statetype == "order") {
		switch (stateval) {
			case 0:
				return "已付款";
			case 1:
				return "已下单";
			case 2:
				return "已配送";
			case 3:
				return "已寄到";
			default:
				return "error";
		}
	} else if (statetype == "access") {
		switch (stateval) {
			case 1:
				return "审批中";
			case 2:
				return "批准";
			default:
				return "error";
		}
	}
}

/* 非空检查 */
function checkEmpty(value) {
	if (value.length == 0) {
		alert("存在未填项！！！");
		return false;
	}
	return true;
}

/* 数字类型,非空检查 */
function checkNumber(value) {
	if (value.length == 0) {
		alert("存在未填项！！！");
		return false;
	}
	if (isNaN(value)) {
		alert("数据类型有误！！！");
		return false;
	}
	return true;
}

/* 数字长度检查 */
function checkLength(value, obj) {
	if (value.length > 8) {
		obj.value = value.slice(0, 8);
	}
}

/* 数字整型检查 */
function checkInt(value) {
	if (value.length == 0) {
		alert("存在未填项！！！");
		return false;
	}
	if (parseInt(value) != value) {
		alert("数据类型有误！！！");
		return false;
	}
	return true;
}

/* 添加商品--数据获取与合法性检查 */
function getData() {
	var product = [];
	var result = true;
	product[0] = $("div#addProductDiv").find("input#productName").val();
	product[1] = $("div#addProductDiv").find("input#sortName").val();
	product[2] = $("div#addProductDiv").find("input#shopPrice").val();
	product[3] = $("div#addProductDiv").find("input#price").val();
	product[4] = $("div#addProductDiv").find("input#quantity").val();
	product[5] = $("div#addProductDiv").find("input#detail").val();
	for (var i = 0; i < product.length; i++) {
		if (i == 4) {
			result = checkInt(product[i]);
			if (result == false) {
				return false;
			}
		} else {
			result = checkEmpty(product[i]);
			if (result == false) {
				return false;
			}
		}
	}
	return true;
}

/* 添加商品--获取图片 */
function getImg() {
	var formdata = new FormData();
	var count = 1;
	$("div.addImage").find("input#image").each(function() {
		var imageName = "image" + (count++);
		formdata.append(imageName, $(this)[0].files[0]);
	});
	return formdata;
}

/* 商品管理--新增分类 数据获取与合法性检查 */
function getSortData() {
	var sortName = $("div#addSortDiv").find("input#sortName").val();
	var detail = $("div#addSortDiv").find("input#detail").val();
	var data;
	var result;
	result = checkEmpty(sortName);
	if (result == false) {
		return false;
	}
	result = checkEmpty(detail);
	if (result == false) {
		return false;
	}
	data = {
		"categoryName": sortName,
		"description": detail
	}
	return data;
}

/* 商品管理--修改商品模块初始化 */
function uModify(obj) {
	$("div.proModDiv").find("input#productId").val(obj.eq(0).text());
	$("div.proModDiv").find("input#sortName").val(obj.eq(2).text());
	$("div.proModDiv").find("input#detail").val(obj.eq(3).text());
	$("div.proModDiv").find("input#shopPrice").val(obj.eq(4).text());
	$("div.proModDiv").find("input#price").val(obj.eq(5).text());
	$("div.proModDiv").find("input#quantity").val(obj.eq(6).text());
	$("div.proModDiv div.addImages").find("input[type=file]").not("input.fisrtFile").remove();
}

/* 商品管理——商品图片 初始隐藏状态 */
function proImgHide() {
	$("div#product").find("tr:nth-of-type(odd)").not("tr.trTitle").each(function() {
		$(this).hide();
	});

}

/* 分类管理--修改 初始隐藏状态 */
function sortModHide() {
	$("div#sort").find("tr:nth-of-type(odd)").not("tr.trTitle").each(function() {
		$(this).hide();
	});
}

/* 用户管理--修改 初始隐藏状态 */
function userModHide() {
	$("div#userManagement").find("tr:nth-of-type(odd)").not("tr.trTitle").each(function() {
		$(this).hide();
	});
}

/* 板块数据填入事件 */
$(document).ready(function() {
	/* 获取订单状态枚举 */
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath }/admin/enums/commentaudit",
		success:function(data){
			if(JSON.parse(data).success){
				accessStatus=JSON.parse(data).data;
			}
		}
	});

	/* 获取评论状态枚举 */
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath }/admin/enums/orderstatus",
		success:function(data){
			if(JSON.parse(data).success){
				orderStatus=JSON.parse(data).data;
			}
		}
	});

	/* 打开主页载入商品数据 */
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath }/admin/products?offset=0&limit=10",
		success: function(data) {
			if (JSON.parse(data).success) {
				for (var i = 0; i < JSON.parse(data).data.length; i++) {
					var productId = JSON.parse(data).data[i].product.id;
					var productName = JSON.parse(data).data[i].product.productName;
					var categoryName = JSON.parse(data).data[i].categoryName;
					var explain = JSON.parse(data).data[i].product.explain;
					var shopPrice = JSON.parse(data).data[i].product.shopPrice;
					var price = JSON.parse(data).data[i].product.price;
					var quantity = JSON.parse(data).data[i].product.quantity;
					var showtrHtml = "<tr><td>" + productId + "</td><td>" + productName + "</td><td>" + categoryName +
						"</td><td>" +
						explain +
						"</td><td>" + shopPrice + "</td<td>" + price + "</td><td>" + quantity +
						"</td><td><button type='button' class='productImg'>图片</button></td>" +
						"<td><button type='button' class='productModify'>修改</button><button type='button' class='productDelete'>删除 </button></td></tr>";
					var imgtrHtml = "<tr><td colspan='9'>" + "</td></tr>";
					var trHtml = showtrHtml + imgtrHtml;
					var $new = $(trHtml);
					$("div#product table.table").find("tr.trTitle").after($new);
				}
				$("div#product div#paging").attr("page",1);
			}
		}
	});

	/* 分类 数据 */
	$("ul.nav").on("click", "li.sortLi", function() {
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/admin/categories?offset=0&limit=10",
			success: function(data) {
				if (JSON.parse(data).success) {
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var categoryId = JSON.parse(data).data[i].id;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].description;
						var showtrHtml = "<tr><td>" + categoryId + "</td><td>" + categoryName + "</td><td>" + explain +
							"</td><td>" +
							"<button type='button' class='sortModify'>修改</button></td></tr>";
						var modtrHtml =
							"<tr><td>类别名:<input type='text' class='form-control textRule' id='sortName' name='sortName' value='" +
							categoryName + "'></td>" +
							"<td colspan='2'>描述：<input type='text' class='form-control textRule' id='sortDetail' name='sortDetail' value='" +
							explain + "'></td>" +
							"<td><button type='button' class='saveChange' sortId='" + categoryId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#sort table.table").find("tr.trTitle").after($new);
					}
					$("div#sort div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 用户管理数据 */
	$("ul.nav").on("click", "li.userLi", function() {
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/admin/users?offset=0&limit=10",
			success: function(data) {
				if (JSON.parse(data).success) {
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var userId = JSON.parse(data).data[i].user.id;
						var nickName = JSON.parse(data).data[i].user.nickName;
						var userName = JSON.parse(data).data[i].user.userName;
						var registerTime = JSON.parse(data).data[i].registerTime
						var userchar = JSON.parse(data).data[i].roleState;
						if (userchar == "0") {
							userchar = "管理员";
						} else if (userchar == "1") {
							userchar = "用户";
						}
						var showtrHtml = "<tr><td>" + userId + "</td><td>" + nickName + "</td><td>" + userName + "</td><td>" +
							email + "</td><td>" +
							registerTime + "</td><td>" + userchar + "</td><td><button type='button' class='userModify'>修改</button>" +
							"<button class='userDelete' type='button'>删除</button></td></tr>";
						var modtrHtml =
							"<tr><td>昵称:<input type='text' class='form-control' id='uNickName' maxlength='8' name='uNickName' value='" +
							nickName + "'></td>" +
							"<td>名称:<input type='text' class='form-control' id='uName' required='required' maxlength='5' name='uName' value='" +
							userName + "'></td>" +
							"<td>邮箱:<input type='email' class='form-control' id='uEmail' name='uEmail' value='" + email + "'></td>" +
							"<td colspan='4'><button type='button' class='saveChange' userId='" + userId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#userManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#userManagement div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 订单管理数据 */
	$("ul.nav").on("click", "li.orderLi", function() {
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/admin/orders?offset=0&limit=10",
			success: function(data) {
				if (JSON.parse(data).success) {
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var orderNum = JSON.parse(data).data[i].orderInfo.orderNum;
						var price = JSON.parse(data).data[i].orderInfo.price;
						var userName = JSON.parse(data).data[i].orderInfo.userName;
						var createTime = JSON.parse(data).data[i].orderInfo.createTime;
						var status = JSON.parse(data).data[i].orderInfo.status;
						var trHtml = "<tr><td><a href='javascript:;'>" + orderNum + "</a></td><td>￥" + price + "</td><td>" +
							userName + "</td><td>" +
							createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" +
							status + "</td><td>" +
							"<select name='orderStatus' class='form-control'><option value='0'>已付款</option><option value='1'>" +
							"已下单</option><option value='2'>已配送</option><option value='3'>已寄到</option></select></td>" +
							"<td><button class='orderDelete' type='button'>删除</button></td>/tr>";
						var $new = $(trHtml);
						$("div#orderMangement table.table").find("tr.trTitle").after($new);
					}
					$("div#orderMangement div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 评论管理数据 */
	$("ul.nav").on("click", "li.accessLi", function() {
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/admin/comments?offset=0&limit=10",
			success: function(data) {
				if (JSON.parse(data).success) {
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].comment.productId;
						var userName = JSON.parse(data).data[i].comment.userName;
						var accessId = JSON.parse(data).data[i].comment.id;
						var content = JSON.parse(data).data[i].comment.content;
						var createTime = JSON.parse(data).data[i].comment.createTime;
						var stateInfo = JSON.parse(data).data[i].stateInfo;
						var trHtml = "<tr><td>" + productId + "</td><td>" + userName + "</td><td>" + accessId + "</td><td>" +
							content + "</td><td>" + createTime + "</td><td>" + stateInfo +
							"</td><td><select name='accessStatus' class='form-control'><option value='1'>审批中< /option>" +
							"<option value = '2' >批准< /option></select> </td> <td > < button type = 'button' class = 'accessDelete' > 删除 < /button></td ></tr>";
						var $new = $(trHtml);
						$("div#accessManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#accessManagement div#paging").attr("page", 1);
				}
			}
		});
	});
});

/* 板块数据新增功能实现 */
$(document).ready(function() {
	/* 商品管理--商品 继续添加图片 */
	$("div.addImages").on("click", "button#continueAddImage", function() {
		var addimgHtml = "<input type='file' class='form-control' name='otherImages' id='image' value='' />";
		var $new = $(addimgHtml);
		$("div.addImages").append($new);
	});

	/* 商品管理--增加商品 上传信息 */
	$("div#addProductDiv").on("click", "button#addProductSub", function() {
		var productInfo = getData();
		if (productInfo == false) {
			alert("请补充完整~");
			return;
		}
		var formdata = new FormData($("form#addProForm")[0]);
		$.ajax({
			url: "${pageContext.request.contextPath }/admin/products",
			data: formdata,
			type: "post",
			dataType: 'json',
			cache: false,
			processData: false,
			contentType: false,
			async: false,
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("新增成功");
				}
			}
		});
	});

	/* 商品管理--增加分类 上传信息 */
	$("div#addSortDiv").on("click", "button#addSortSub", function() {
		var sortInfo = getSortData();
		if (sortInfo == false) {
			alert("请补充完整~");
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath }/admin/categories",
			type: "post",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(sortInfo),
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("新增成功");
				}
			}
		});
	});

	/* 下拉框分类数据 */
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath }/admin/categories?offset=0&limit=1000",
		success: function(data) {
			if (JSON.parse(data).success) {
				for (var i = 0; i < JSON.parse(data).data.length; i++) {
					var categoryId = JSON.parse(data).data[i].id;
					var categoryName = JSON.parse(data).data[i].categoryName;
					var optionHtml = "<option value ='" + categoryId + "'>" + categoryName + "</option>";
					var $new = $(optionHtml);
					$("div#addProductDiv").find("select.categorySelect").append($new);
					$("div.proModDiv").find("select.categorySelect").append($new);
				}
			}
		}
	});
});

/* 管理类功能实现 */
$(document).ready(function() {
	/* 商品管理——商品 修改 准备 */
	$("div#product table.table").on("click", "button.productModify", function() {
		uModify($(this).closest("tr").children());
	})

	/* 商品管理——商品 修改 提交 */
	$("div.proModDiv").on("click", "button#proModSub", function() {
		var productId = $(this).closest("tr").children().eq(0).text();
		var formdata = new FormData($("form#proModForm")[0]);
		var promod_url = "${pageContext.request.contextPath }/admin/products/{" + productId + "}";
		$.ajax({
			url: promod_url,
			data: formdata,
			type: "post",
			dataType: 'json',
			cache: false,
			processData: false,
			contentType: false,
			async: false,
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("修改成功");
				}
			}
		});
	});

	/* 商品管理——商品 删除 */
	$("div#product table.table").on("click", "button.productDelete", function() {
		var productId = $(this).closest("tr").children().eq(0);
		var productName = $(this).closest("tr").children().eq(1);
		var data = {
			"productId": productId,
			"productName": productName
		};
		if (confirm("确认要删除吗？")) {
			$.ajax({
				type: "delete",
				url: "${pageContext.request.contextPath }/admin/products",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data) {
					if (JSON.parse(data).success) {
						$(this).closest("tr").remove();
						alert("删除成功！！！");
					}
				}
			});
		}
	});

	/* 商品管理——商品图片 状态切换操作 */
	$("div#product table.table").on("click", "button.productImg", function() {
		$(this).closest("tr").next().toggle(300);
	});

	/* 商品管理——分类 修改 状态切换操作*/
	$("div#sort table.table").on("click", "button.sortModify", function() {
		$(this).closest("tr").next().toggle(300);
	});

	/* 商品管理——分类 修改信息 */
	$("div#sort table.table").on("click", "button.saveChange", function() {
		var sortId = $(this).attr("sortId");
		var sortName = $(this).closest("tr").find("input#sortName").val();
		var sortDetail = $(this).closest("tr").find("input#sortDetail").val();
		var data = {
			"id": sortId,
			"categoryName": sortName,
			"description": sortDetail
		}
		$.ajax({
			type: "put",
			url: "${pageContext.request.contextPath }/admin/categories",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("修改成功！！！");
				}
			}
		});
	});

	/* 用户管理 修改用户信息 */
	$("div#userManagement table.table").on("click", "button.saveChange", function() {
		var userId = $(this).attr("userId");
		var nickName = $(this).closest("tr").find("input#uNickName").val();
		var userName = $(this).closest("tr").find("input#uName").val();
		var email = $(this).closest("tr").find("input#uEmail").val();
		var data = {
			"userId": userId,
			"userName": userName,
			"nickName": nickName,
			"email": email
		}
		$.ajax({
			type: "put",
			url: "${pageContext.request.contextPath }/admin/users",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function(data) {
				if (JSON.parse(data).success) {
					alert("修改成功！！！");
				}
			}
		});
	});

	/* 用户管理 删除 */
	$("div#userManagement table.table").on("click", "button.userDelete", function() {
		var id = $(this).closest("tr").children().eq(0).text();
		var name = $(this).closest("tr").children().eq(2).text();
		var data = {
			"userId": id,
			"userName": name
		};
		if (confirm("确认要删除吗？")) {
			$.ajax({
				type: "delete",
				url: "${pageContext.request.contextPath }/admin/users",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data) {
					if (JSON.parse(data).success) {
						$(this).closest("tr").next().remove();
						$(this).closest("tr").remove();
						alert("删除成功！！！");
					}
				}
			});
		}
	});

	/* 用户管理--修改 状态切换操作*/
	$("div#userManagement table.table").on("click", "button.userModify", function() {
		$(this).closest("tr").next().toggle(300);
	});

	/* 订单管理 删除 */
	$("div#orderMangement table.table").on("click", "button.orderDelete", function() {
		var orderNum = $(this).closest("tr").children().eq(0).text();
		var data = {
			"orderNum": orderNum
		};
		if (confirm("确认要删除吗？")) {
			$.ajax({
				type: "delete",
				url: "${pageContext.request.contextPath }/admin/orders",
				data: data,
				success: function(data) {
					if (JSON.parse(data).success) {
						$(this).closest("tr").remove();
						alert("删除成功！！！");
					}
				}
			});

		}
	});

	/* 订单管理状态审核 */
	$("div#orderMangement table.table").on("change", "select.form-control", function() {
		var status = $(this).val();
		var orderNum = $(this).closest("tr").children().eq(0).text();
		var data = {
			"orderNum": orderNum,
			"status": status
		}
		$.ajax({
			type: "put",
			url: "${pageContext.request.contextPath }/admin/orders",
			data: data,
			success: function(data) {
				if (JSON.parse(data).success) {
					var updatedStatus = JSON.parse(data).data.orderInfo.status;
					var statusText = getStateInfo("order", updatedStatus);
					$(this).closest("tr").children().eq(8).text(statusText);
					alert("修改成功！！！");
				}
			}
		});
	});

	/* 评论管理 删除 */
	$("div#accessManagement table.table").on("click", "button.accessDelete", function() {
		var accessId = $(this).closest("tr").children().eq(2).text();
		var data = {
			"id": accessId
		};
		if (confirm("确认要删除吗？")) {
			$.ajax({
				type: "delete",
				url: "${pageContext.request.contextPath }/admin/comments",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(data),
				success: function(data) {
					if (JSON.parse(data).success) {
						$(this).closest("tr").remove();
						alert("删除成功！！！");
					}
				}
			});

		}
	});

	/* 评论管理状态审核 */
	$("div#accessManagement table.table").on("change", "select.form-control", function() {
		var status = $(this).val();
		var accessId = $(this).closest("tr").children().eq(2).text();
		var data = {
			"id": accessId,
			"auditState": status
		};
		$.ajax({
			type: "put",
			url: "${pageContext.request.contextPath }/admin/comments",
			data: data,
			success: function(data) {
				if (JSON.parse(data).success) {
					var updatedStatus = JSON.parse(data).data[0].stateInfo;
					$(this).closest("tr").children().eq(5).text(updatedStatus);
					alert("修改成功！！！");
				}
			}
		});
	});
});

/* 搜索功能实现 */
$(document).ready(function() {
	/* 搜索商品 */
	$("div.productSearch").on("click", "button#searchProductBtn", function() {
		var searchText = $(this).closest("div").find(":text").val();
		if (searchText == "") {
			return;
		}
		var searchPro_url = "${pageContext.request.contextPath }/admin/products?offset=0&limit=10&searchText=" +
			searchText;
		$.ajax({
			type: "get",
			url: searchPro_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#product table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].product.id;
						var productName = JSON.parse(data).data[i].product.productName;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].product.explain;
						var shopPrice = JSON.parse(data).data[i].product.shopPrice;
						var price = JSON.parse(data).data[i].product.price;
						var quantity = JSON.parse(data).data[i].product.quantity;
						var showtrHtml = "<tr><td>" + productId + "</td><td>" + productName + "</td><td>" + categoryName +
							"</td><td>" +
							explain +
							"</td><td>" + shopPrice + "</td<td>" + price + "</td><td>" + quantity +
							"</td><td><button type='button' class='productImg'>图片</button></td>" +
							"<td><button type='button' class='productModify'>修改</button><button type='button' class='productDelete'>删除 </button></td></tr>";
						var imgtrHtml = "<tr><td colspan='9'>" + "</td></tr>";
						var trHtml = showtrHtml + imgtrHtml;
						var $new = $(trHtml);
						$("div#product table.table").find("tr.trTitle").after($new);
					}
					$("div#product div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 搜索分类 */
	$("div.sortSearch").on("click", "button#searchSortBtn", function() {
		var searchText = $(this).closest("div").find(":text").val();
		if (searchText == "") {
			return;
		}
		var searchSort_url = "${pageContext.request.contextPath }/admin/categories?offset=0&limit=10&searchText=" +
			searchText;
		$.ajax({
			type: "get",
			url: searchSort_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#sort table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var categoryId = JSON.parse(data).data[i].id;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].description;
						var showtrHtml = "<tr><td>" + categoryId + "</td><td>" + categoryName + "</td><td>" + explain +
							"</td><td>" +
							"<button type='button' class='sortModify'>修改</button></td></tr>";
						var modtrHtml =
							"<tr><td>类别名:<input type='text' class='form-control textRule' id='sortName' name='sortName'></td>" +
							"<td colspan='2'>描述：<input type='text' class='form-control textRule' id='sortDetail' name='sortDetail'></td>" +
							"<td><button type='button' class='saveChange' sortId='" + categoryId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#sort table.table").find("tr.trTitle").after($new);
					}
					$("div#sort div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 搜索用户 */
	$("div.userSearch").on("click", "button#searchUserBtn", function() {
		var searchText = $(this).closest("div").find(":text").val();
		if (searchText == "") {
			return;
		}
		var searchUser_url = "${pageContext.request.contextPath }/admin/users?offset=0&limit=10&searchText=" + searchText;
		$.ajax({
			type: "get",
			url: searchUser_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#userManagement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var userId = JSON.parse(data).data[i].user.id;
						var nickName = JSON.parse(data).data[i].user.nickName;
						var userName = JSON.parse(data).data[i].user.userName;
						var registerTime = JSON.parse(data).data[i].registerTime
						var userchar = JSON.parse(data).data[i].roleState;
						if (userchar == "0") {
							userchar = "管理员";
						} else if (userchar == "1") {
							userchar = "用户";
						}
						var showtrHtml = "<tr><td>" + userId + "</td><td>" + nickName + "</td><td>" + userName + "</td><td>" +
							email + "</td><td>" +
							registerTime + "</td><td>" + userchar + "</td><td><button type='button' class='userModify'>修改</button>" +
							"<button class='userDelete' type='button'>删除</button></td></tr>";
						var modtrHtml =
							"<tr><td>昵称:<input type='text' class='form-control' id='uNickName' maxlength='8' name='uNickName'></td>" +
							"<td>名称:<input type='text' class='form-control' id='uName' required='required' maxlength='5' name='uName'></td>" +
							"<td>邮箱:<input type='email' class='form-control' id='uEmail' name='uEmail'></td>" +
							"<td colspan='4'><button type='button' class='saveChange' userId='" + userId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#userManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#userManagement div#paging").attr("page", 1);
				}
			}
		});
	});

	/* 搜索订单 */
	$("div.orderSearch").on("click", "button#searchOrderBtn", function() {
		var searchText = $(this).closest("div").find(":text").val();
		if (searchText == "") {
			return;
		}
		var searchOrder_url = "${pageContext.request.contextPath }/admin/orders?offset=0&limit=10&searchText=" +
			searchText;
		$.ajax({
			type: "get",
			url: searchOrder_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#orderMangement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var orderNum = JSON.parse(data).data[i].orderInfo.orderNum;
						var price = JSON.parse(data).data[i].orderInfo.price;
						var userName = JSON.parse(data).data[i].orderInfo.userName;
						var createTime = JSON.parse(data).data[i].orderInfo.createTime;
						var status = JSON.parse(data).data[i].orderInfo.status;
						var trHtml = "<tr><td><a href='javascript:;'>" + orderNum + "</a></td><td>￥" + price + "</td><td>" +
							userName + "</td><td>" +
							createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" +
							status + "</td><td>" +
							"<select name='orderStatus' class='form-control'><option value='0'>已付款</option><option value='1'>" +
							"已下单</option><option value='2'>已配送</option><option value='3'>已寄到</option></select></td>" +
							"<td><button class='orderDelete' type='button'>删除</button></td>/tr>";
						var $new = $(trHtml);
						$("div#orderMangement table.table").find("tr.trTitle").after($new);
					}
					$("div#orderMangement div#paging").attr("page", 1);
				}
			}
		});
	});
});

/* 翻页功能实现 */
$(document).ready(function() {
	/* 商品管理——商品 向前翻页 */
	$("div#product nav").on("click", "li.prevPage a", function() {
		var nowPage = $("div#product div#paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var url_str = "?offset=" + (--nowPage - 1) + "&limit=10";
		var prev_url = "${pageContext.request.contextPath }/admin/products" + url_str;
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#product table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].product.id;
						var productName = JSON.parse(data).data[i].product.productName;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].product.explain;
						var shopPrice = JSON.parse(data).data[i].product.shopPrice;
						var price = JSON.parse(data).data[i].product.price;
						var quantity = JSON.parse(data).data[i].product.quantity;
						var showtrHtml = "<tr><td>" + productId + "</td><td>" + productName + "</td><td>" + categoryName +
							"</td><td>" +
							explain +
							"</td><td>" + shopPrice + "</td<td>" + price + "</td><td>" + quantity +
							"</td><td><button type='button' class='productImg'>图片</button></td>" +
							"<td><button type='button' class='productModify'>修改</button><button type='button' class='productDelete'>删除 </button></td></tr>";
						var imgtrHtml = "<tr><td colspan='9'>" + "</td></tr>";
						var trHtml = showtrHtml + imgtrHtml;
						var $new = $(trHtml);
						$("div#product table.table").find("tr.trTitle").after($new);
					}
					$("div#product div#paging").attr("page", nowPage);
				}
			}
		});
	});

	/* 商品管理——商品 向后翻页 */
	$("div#product nav").on("click", "li.nextPage a", function() {
		var nowPage = $("div#product div#paging").attr("page");
		var url_str = "?offset=" + (++nowPage - 1) + "&limit=10";
		var next_url = "${pageContext.request.contextPath }/admin/products" + url_str;
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#product table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].product.id;
						var productName = JSON.parse(data).data[i].product.productName;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].product.explain;
						var shopPrice = JSON.parse(data).data[i].product.shopPrice;
						var price = JSON.parse(data).data[i].product.price;
						var quantity = JSON.parse(data).data[i].product.quantity;
						var showtrHtml = "<tr><td>" + productId + "</td><td>" + productName + "</td><td>" + categoryName +
							"</td><td>" +
							explain +
							"</td><td>" + shopPrice + "</td<td>" + price + "</td><td>" + quantity +
							"</td><td><button type='button' class='productImg'>图片</button></td>" +
							"<td><button type='button' class='productModify'>修改</button><button type='button' class='productDelete'>删除 </button></td></tr>";
						var imgtrHtml = "<tr><td colspan='9'>" + "</td></tr>";
						var trHtml = showtrHtml + imgtrHtml;
						var $new = $(trHtml);
						$("div#product table.table").find("tr.trTitle").after($new);
					}
					$("div#product div#paging").attr("page", nowPage);
				} else {
					alert("已经是最后一页了~");
					$("div#product div#paging").attr("page", (--nowPage));
				}
			}
		});
	});

	/* 商品管理——分类 向前翻页 */
	$("div#sort nav").on("click", "li.prevPage a", function() {
		var nowPage = $("div#sort div#paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var url_str = "?offset=" + (--nowPage - 1) + "&limit=10";
		var prev_url = "${pageContext.request.contextPath }/admin/categories" + url_str;
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#sort table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var categoryId = JSON.parse(data).data[i].id;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].description;
						var showtrHtml = "<tr><td>" + categoryId + "</td><td>" + categoryName + "</td><td>" + explain +
							"</td><td>" +
							"<button type='button' class='sortModify'>修改</button></td></tr>";
						var modtrHtml =
							"<tr><td>类别名:<input type='text' class='form-control textRule' id='sortName' name='sortName'></td>" +
							"<td colspan='2'>描述：<input type='text' class='form-control textRule' id='sortDetail' name='sortDetail'></td>" +
							"<td><button type='button' class='saveChange' sortId='" + categoryId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#sort table.table").find("tr.trTitle").after($new);
					}
					$("div#sort div#paging").attr("page", nowPage);
				}
			}
		});
	});

	/* 商品管理——分类 向后翻页 */
	$("div#sort nav").on("click", "li.nextPage a", function() {
		var nowPage = $("div#sort div#paging").attr("page");
		var url_str = "?offset=" + (++nowPage - 1) + "&limit=10";
		var next_url = "${pageContext.request.contextPath }/admin/categories" + url_str;
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#sort table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var categoryId = JSON.parse(data).data[i].id;
						var categoryName = JSON.parse(data).data[i].categoryName;
						var explain = JSON.parse(data).data[i].description;
						var showtrHtml = "<tr><td>" + categoryId + "</td><td>" + categoryName + "</td><td>" + explain +
							"</td><td>" +
							"<button type='button' class='sortModify'>修改</button></td></tr>";
						var modtrHtml =
							"<tr><td>类别名:<input type='text' class='form-control textRule' id='sortName' name='sortName'></td>" +
							"<td colspan='2'>描述：<input type='text' class='form-control textRule' id='sortDetail' name='sortDetail'></td>" +
							"<td><button type='button' class='saveChange' sortId='" + categoryId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#sort table.table").find("tr.trTitle").after($new);
					}
					$("div#sort div#paging").attr("page", nowPage);
				} else {
					alert("已经是最后一页了~");
					$("div#sort div#paging").attr("page", (--nowPage));
				}
			}
		});
	});

	/* 用户管理 向前翻页 */
	$("div#userManagement nav").on("click", "li.prevPage a", function() {
		var nowPage = $("div#userManagement div#paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var url_str = "?offset=" + (--nowPage - 1) + "&limit=10";
		var prev_url = "${pageContext.request.contextPath }/admin/users" + url_str;
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#userManagement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var userId = JSON.parse(data).data[i].user.id;
						var nickName = JSON.parse(data).data[i].user.nickName;
						var userName = JSON.parse(data).data[i].user.userName;
						var registerTime = JSON.parse(data).data[i].registerTime
						var userchar = JSON.parse(data).data[i].roleState;
						if (userchar == "0") {
							userchar = "管理员";
						} else if (userchar == "1") {
							userchar = "用户";
						}
						var showtrHtml = "<tr><td>" + userId + "</td><td>" + nickName + "</td><td>" + userName + "</td><td>" +
							email + "</td><td>" +
							registerTime + "</td><td>" + userchar + "</td><td><button type='button' class='userModify'>修改</button>" +
							"<button class='userDelete' type='button'>删除</button></td></tr>";
						var modtrHtml =
							"<tr><td>昵称:<input type='text' class='form-control' id='uNickName' maxlength='8' name='uNickName'></td>" +
							"<td>名称:<input type='text' class='form-control' id='uName' required='required' maxlength='5' name='uName'></td>" +
							"<td>邮箱:<input type='email' class='form-control' id='uEmail' name='uEmail'></td>" +
							"<td colspan='4'><button type='button' class='saveChange' userId='" + userId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#userManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#userManagement div#paging").attr("page", nowPage);
				}
			}
		});
	});

	/* 用户管理——分类 向后翻页 */
	$("div#userManagement nav").on("click", "li.nextPage a", function() {
		var nowPage = $("div#userManagement div#paging").attr("page");
		var url_str = "?offset=" + (++nowPage - 1) + "&limit=10";
		var next_url = "${pageContext.request.contextPath }/admin/users" + url_str;
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#userManagement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var userId = JSON.parse(data).data[i].user.id;
						var nickName = JSON.parse(data).data[i].user.nickName;
						var userName = JSON.parse(data).data[i].user.userName;
						var registerTime = JSON.parse(data).data[i].registerTime
						var userchar = JSON.parse(data).data[i].roleState;
						if (userchar == "0") {
							userchar = "管理员";
						} else if (userchar == "1") {
							userchar = "用户";
						}
						var showtrHtml = "<tr><td>" + userId + "</td><td>" + nickName + "</td><td>" + userName + "</td><td>" +
							email + "</td><td>" +
							registerTime + "</td><td>" + userchar + "</td><td><button type='button' class='userModify'>修改</button>" +
							"<button class='userDelete' type='button'>删除</button></td></tr>";
						var modtrHtml =
							"<tr><td>昵称:<input type='text' class='form-control' id='uNickName' maxlength='8' name='uNickName'></td>" +
							"<td>名称:<input type='text' class='form-control' id='uName' required='required' maxlength='5' name='uName'></td>" +
							"<td>邮箱:<input type='email' class='form-control' id='uEmail' name='uEmail'></td>" +
							"<td colspan='4'><button type='button' class='saveChange' userId='" + userId + "'>保存</button></td></tr>";
						var trHtml = showtrHtml + modtrHtml;
						var $new = $(trHtml);
						$("div#userManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#userManagement div#paging").attr("page", nowPage);
				} else {
					alert("已经是最后一页了~");
					$("div#userManagement div#paging").attr("page", (--nowPage));
				}
			}
		});
	});

	/* 订单管理 向前翻页 */
	$("div#orderMangement nav").on("click", "li.prevPage a", function() {
		var nowPage = $("div#orderMangement div#paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var url_str = "?offset=" + (--nowPage - 1) + "&limit=10";
		var prev_url = "${pageContext.request.contextPath }/admin/orders" + url_str;
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#orderMangement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var orderNum = JSON.parse(data).data[i].orderInfo.orderNum;
						var price = JSON.parse(data).data[i].orderInfo.price;
						var userName = JSON.parse(data).data[i].orderInfo.userName;
						var createTime = JSON.parse(data).data[i].orderInfo.createTime;
						var status = JSON.parse(data).data[i].orderInfo.status;
						var trHtml = "<tr><td><a href='javascript:;'>" + orderNum + "</a></td><td>￥" + price + "</td><td>" +
							userName + "</td><td>" +
							createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" +
							status + "</td><td>" +
							"<select name='orderStatus' class='form-control'><option value='0'>已付款</option><option value='1'>" +
							"已下单</option><option value='2'>已配送</option><option value='3'>已寄到</option></select></td>" +
							"<td><button class='orderDelete' type='button'>删除</button></td>/tr>";
						var $new = $(trHtml);
						$("div#orderMangement table.table").find("tr.trTitle").after($new);
					}
					$("div#orderMangement div#paging").attr("page", nowPage);
				}
			}
		});
	});

	/* 订单管理 向后翻页 */
	$("div#orderMangement nav").on("click", "li.nextPage a", function() {
		var nowPage = $("div#orderMangement div#paging").attr("page");
		var url_str = "?offset=" + (++nowPage - 1) + "&limit=10";
		var next_url = "${pageContext.request.contextPath }/admin/orders" + url_str;
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#orderMangement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var orderNum = JSON.parse(data).data[i].orderInfo.orderNum;
						var price = JSON.parse(data).data[i].orderInfo.price;
						var userName = JSON.parse(data).data[i].orderInfo.userName;
						var createTime = JSON.parse(data).data[i].orderInfo.createTime;
						var status = JSON.parse(data).data[i].orderInfo.status;
						var trHtml = "<tr><td><a href='javascript:;'>" + orderNum + "</a></td><td>￥" + price + "</td><td>" +
							userName + "</td><td>" +
							createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" + createTime + "</td><td>" +
							status + "</td><td>" +
							"<select name='orderStatus' class='form-control'><option value='0'>已付款</option><option value='1'>" +
							"已下单</option><option value='2'>已配送</option><option value='3'>已寄到</option></select></td>" +
							"<td><button class='orderDelete' type='button'>删除</button></td>/tr>";
						var $new = $(trHtml);
						$("div#orderMangement table.table").find("tr.trTitle").after($new);
					}
					$("div#orderMangement div#paging").attr("page", nowPage);
				} else {
					alert("已经是最后一页了~");
					$("div#orderMangement div#paging").attr("page", (--nowPage));
				}
			}
		});
	});

	/* 评论管理 向前翻页 */
	$("div#accessManagement nav").on("click", "li.prevPage a", function() {
		var nowPage = $("div#accessManagement div#paging").attr("page");
		if (nowPage == 1) {
			alert("已经是第一页了~");
			return;
		}
		var url_str = "?offset=" + (--nowPage - 1) + "&limit=10";
		var prev_url = "${pageContext.request.contextPath }/admin/comments" + url_str;
		$.ajax({
			type: "get",
			url: prev_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#accessManagement table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].comment.productId;
						var userName = JSON.parse(data).data[i].comment.userName;
						var accessId = JSON.parse(data).data[i].comment.id;
						var content = JSON.parse(data).data[i].comment.content;
						var createTime = JSON.parse(data).data[i].comment.createTime;
						var stateInfo = JSON.parse(data).data[i].stateInfo;
						var trHtml = "<tr><td>" + productId + "</td><td>" + userName + "</td><td>" + accessId + "</td><td>" +
							content + "</td><td>" + createTime + "</td><td>" + stateInfo +
							"</td><td><select name='accessStatus' class='form-control'><option value='1'>审批中< /option>" +
							"<option value = '2' >批准< /option></select> </td> <td > < button type = 'button' class = 'accessDelete' > 删除 < /button></td ></tr>";
						var $new = $(trHtml);
						$("div#accessManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#accessManagement div#paging").attr("page", nowPage);
				}
			}
		});
	});

	/* 评论管理 向后翻页 */
	$("div#accessManagement nav").on("click", "li.nextPage a", function() {
		var nowPage = $("div#accessManagement div#paging").attr("page");
		var url_str = "?offset=" + (++nowPage - 1) + "&limit=10";
		var next_url = "${pageContext.request.contextPath }/admin/comments" + url_str;
		$.ajax({
			type: "get",
			url: next_url,
			success: function(data) {
				if (JSON.parse(data).success) {
					$("#accessManagemen-+.t table tr").not("tr.trTitle").remove();
					for (var i = 0; i < JSON.parse(data).data.length; i++) {
						var productId = JSON.parse(data).data[i].comment.productId;
						var userName = JSON.parse(data).data[i].comment.userName;
						var accessId = JSON.parse(data).data[i].comment.id;
						var content = JSON.parse(data).data[i].comment.content;
						var createTime = JSON.parse(data).data[i].comment.createTime;
						var stateInfo = JSON.parse(data).data[i].stateInfo;
						var trHtml = "<tr><td>" + productId + "</td><td>" + userName + "</td><td>" + accessId + "</td><td>" +
							content + "</td><td>" + createTime + "</td><td>" + stateInfo +
							"</td><td><select name='accessStatus' class='form-control'><option value='1'>审批中< /option>" +
							"<option value = '2' >批准< /option></select> </td> <td > < button type = 'button' class = 'accessDelete' > 删除 < /button></td ></tr>";
						var $new = $(trHtml);
						$("div#accessManagement table.table").find("tr.trTitle").after($new);
					}
					$("div#accessManagement div#paging").attr("page", nowPage);
				} else {
					alert("已经是最后一页了~");
					$("div#accessManagement div#paging").attr("page", (--nowPage));
				}
			}
		});
	});
});

/* 状态切换初始化 */
$(document).ready(function() {
	proImgHide();
	sortModHide();
	userModHide();
});
