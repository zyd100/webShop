<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>下单支付页面</title>

		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<!-- IE将使用最新的引擎渲染网页 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" data-integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
		 data-crossorigin="anonymous">

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" data-integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
		 data-crossorigin="anonymous">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" data-integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		 data-crossorigin="anonymous"></script>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/animate.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/orderpayment.css" />
		<script src="${pageContext.request.contextPath }/resources/js/orderpayment.js" type="text/javascript" charset="utf-8"></script>

		<style type="text/css">
			@media screen and (max-width:1600px) {
				#op_left {
					display: none;
				}

				#op_personal {
					display: none;
				}
			}
		</style>
	</head>
	<body>
		<div id="op_container" class="container-fluid">
			<div class="row">
				<div id="op_top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-2 col-xs-2"></div>
						<div id="op_logo" class="col-sm-4 col-xs-4">
							<a href="${pageContext.request.contextPath }/home" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
						<div class="col-sm-1 col-xs-1"></div>
						<div id="ho_search" class="col-sm-4 col-xs-4">
							<form action="${pageContext.request.contextPath }/search" method="get">
								<div class="input-group input-group-size">
									<input type="text" class="form-control textRule" name="searchText" placeholder="  大家都在搜">
									<span class="input-group-btn">
										<button type="submit" class="btn btn-default btn-lg glyphicon glyphicon-search" type="button">搜索</button>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div id="op_left" class="col-sm-1 col-xs-1"></div>
				<div id="op_center" class="col-sm-10 col-xs-10">
					<div id="op_wares">
						<p class="detailTitle">确认订单信息</p>
						<table>
							<tr class="titleTr">
								<th width="900px">商品信息</th>
								<th width="200px">数量</th>
								<th width="200px">金额</th>
								<th width="200px">邮费</th>
							</tr>
							<tr>
								<td>
									<a href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
									<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
								</td>
								<td><span id="count">2</span></td>
								<td>￥<span id="totalprice">199</span></td>
								<td>￥<span id="postage">1</span></td>
							</tr>
							<tr>
								<td>
									<a href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
									<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
								</td>
								<td><span id="count">3</span></td>
								<td>￥<span id="totalprice">199</span></td>
								<td>￥<span id="postage">1</span></td>
							</tr>
							<tr>
								<td>
									<a href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
									<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
								</td>
								<td><span id="count">1</span></td>
								<td>￥<span id="totalprice">199</span></td>
								<td>￥<span id="postage">1</span></td>
							</tr>
						</table>
						<div class="addressSlected">
							<select class="form-control " name="addressSelection">
								<option value="1">
									<p>地址：广东省清远市清城区高基里  电话：123456789 收货人：喜刷刷1</p>
								</option>
								<option value="2">
									<div class="addressShow">
										<p class="inforContent">地址：<span id="inforEmail" class="contentFomat">广东省清远市清城区高基里</span><br>
											电话：<span id="inforTel" class="contentFomat">123456789</span>
											收货人：<span id="inforName" class="contentFomat">喜刷刷2</span>
										</p>
									</div>
								</option>
								<option value="3">
									<div class="addressShow">
										<p class="inforContent">地址：<span id="inforEmail" class="contentFomat">广东省清远市清城区高基里</span><br>
											电话：<span id="inforTel" class="contentFomat">123456789</span>
											收货人：<span id="inforName" class="contentFomat">喜刷刷3</span>
										</p>
									</div>
								</option>
								<option value="4">
									<div class="addressShow">
										<p class="inforContent">地址：<span id="inforEmail" class="contentFomat">广东省清远市清城区高基里</span><br>
											电话：<span id="inforTel" class="contentFomat">123456789</span>
											收货人：<span id="inforName" class="contentFomat">喜刷刷4</span>
										</p>
									</div>
								</option>
							</select>
						</div>
						<p>实付<span id="nhr"></span>元</p>
						<div class="row">
							<div class="col-sm-10 col-xs-10"></div>
							<div class="col-sm-2 col-xs-2">
								<button type="button" id="payNow">立即支付</button>
							</div>
						</div>
					</div>
					<div id="op_center_footer">
						<h3>我是有底线的</h3>
					</div>
				</div>
				<div id="op_right" class="col-sm-1 col-xs-1">
					<div id="op_personal">
						<div>
							<a href="javascript:;" id="personal" title="个人中心"><span class="glyphicon glyphicon-user " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div>
							<a href="javascript:;" id="shoppingCart" title="购物车"><span class="glyphicon glyphicon-shopping-cart " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div class="">
							<a href="javascript:;" id="collection" title="收藏"><span class="glyphicon glyphicon-heart " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div>
							<a href="javascript:;" id="outLogin" title="退出登录"><span class="glyphicon glyphicon-off " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
							<form id="logOutForm" method="post"></form>
						</div>
					</div>
					<div class="backTop animated fadeInUp">
						<a href="javasript:;" id="op_topBtn"><img src="${pageContext.request.contextPath }/resources/images/backTop.jpg"></a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			window.onload = function() {
				//1、获取页面中的按钮
				var topBtn = document.getElementById("op_topBtn");
				topBtn.style.display = "none";

				var timer = null;
				//2、给按钮绑定点击事件
				topBtn.onclick = function() {
					//周期性定时
					timer = setInterval(function() {
						//3、获取滑动条距离浏览器顶端的距离
						var backTop = document.documentElement.scrollTop || document.body.scrollTop;

						//滚动速率
						var speedTop = backTop / 5;

						document.documentElement.scrollTop = backTop - speedTop;

						if (backTop == 0) {
							clearInterval(timer);
						}
					}, 30);
				}

				//设置临界值
				var pageHeight = 700;
				//设置按键是否显示
				window.onscroll = function() {
					var backTop = document.documentElement.scrollTop || document.body.scrollTop;

					if (backTop > pageHeight) {
						topBtn.style.display = "block";
					} else {
						topBtn.style.display = "none";
					}
				}
			}
			
			var ctx="${pageContext.request.contextPath }";
			var imgpath="/resources/images/";
			//var ctxImg=ctx+imgpath;
			var ctxImg="/image/";
			$(document).ready(function(){
				var ordlidata = JSON.parse('${orderExecution}');

				$("div#op_personal").attr("userName", '${sessionScope.loginUserName}');
				
				initOrder(ordlidata);
				getContacts($("div#op_personal").attr("userName"));
			});
		</script>
	</body>
</html>
