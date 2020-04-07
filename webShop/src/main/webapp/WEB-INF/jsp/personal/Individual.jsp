<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>个人中心</title>

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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/swiper.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/individual.css" />
		<script src="${pageContext.request.contextPath }/resources/js/swiper.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath }/resources/js/individual.js" type="text/javascript" charset="utf-8"></script>

		<style type="text/css">
			@media screen and (max-width:1600px) {
				#i_left {
					display: none;
				}

				#i_personal {
					display: none;
				}
			}
		</style>

	</head>
	<body>
		<div id="i_container" class="container-fluid">
			<div class="row">
				<div id="i_top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-2 col-xs-2"></div>
						<div id="i_logo" class="col-sm-4 col-xs-4">
							<a href="${pageContext.request.contextPath }/home" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
						<div class="col-sm-1 col-xs-1"></div>
						<div id="i_search" class="col-sm-4 col-xs-4">
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
				<div id="i_left" class="col-sm-1 col-xs-1"></div>
				<div id="i_center" class="col-sm-10 col-xs-10">
					<div id="i_portrait">
						<div class="row">
							<div class="col-sm-1 col-xs-1 headImg">
								<a href="" title="更改头像" data-toggle="modal" data-target="#modPortraitDiv"><img src="${pageContext.request.contextPath }/resources/images/protrait.png"
									 class="img-circle "></a>
							</div>
							<div class="col-sm-4 col-xs-4 inforDetial ">
								<span id="nickName">昵称昵称昵称</span><br>
								<span id="name">名字名字名字</span>
							</div>
						</div>
					</div>
					<div id="i_detail">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#personsalInfor" aria-controls="personsalInfor" role="tab"
								 data-toggle="tab">个人信息</a></li>
							<li role="presentation"><a href="#order" aria-controls="order" role="tab" data-toggle="tab">历史订单</a></li>
							<li class="collect" role="presentation"><a href="#collect">我的收藏</a></li>
							<li class="cart" role="presentation"><a href="#cart">购物车</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="personsalInfor">
								<div class="row">
									<div class="inforShow" class="col-sm-12 col-xs-12">
										<p class="inforTitle">我的信息<a href="#nowhere" id="editName" class="glyphicon glyphicon-edit" data-toggle="modal"
											 data-target="#userInfoDiv" title="编辑"></a></p>
										<p class="inforContent">邮箱：<span id="inforEmail" class="contentFomat">1378056503@qq.com</span><br>
											昵称：<span id="inforuName" class="contentFomat">喜刷刷</span><br>
											注册时间：<span id="registerTime" class="contentFomat">2020-1-1</span>
										</p>
									</div>
									<div class="inforShow" class="col-sm-12 col-xs-12">
										<p class="inforTitle addressTitle">收货地址<a href="#nowhere" id="addAddress" class="glyphicon glyphicon-plus-sign"
											 data-toggle="modal" data-target="#addContactDiv" title="新增"></a></p>
										<div class="addressShow">
											<p class="inforContent">地址：<span id="inforAddress" class="contentFomat">广东省清远市清城区高基里</span><br>
												电话：<span id="inforTel" class="contentFomat">123456789</span>
												收货人：<span id="inforName" class="contentFomat">喜刷刷1</span>
												<a href="#nowhere" id="editAddress" class="glyphicon glyphicon-edit" title="编辑"></a>
												<a href="#nowhere" id="deleteAddress" class="glyphicon glyphicon-minus-sign" title="删除"></a>
											</p>
										</div>
										<div class="addressShow">
											<p class="inforContent">地址：<span id="inforAddress" class="contentFomat">广东省清远市清城区高基里</span><br>
												电话：<span id="inforTel" class="contentFomat">123456789</span>
												收货人：<span id="inforName" class="contentFomat">喜刷刷2</span>
												<a href="#nowhere" id="editAddress" class="glyphicon glyphicon-edit" title="编辑"></a>
												<a href="#nowhere" id="deleteAddress" class="glyphicon glyphicon-minus-sign" title="删除"></a>
											</p>
										</div>
										<div class="addressShow">
											<p class="inforContent">地址：<span id="inforAddress" class="contentFomat">广东省清远市清城区高基里</span><br>
												电话：<span id="inforTel" class="contentFomat">123456789</span>
												收货人：<span id="inforName" class="contentFomat">喜刷刷3</span>
												<a href="#nowhere" id="editAddress" class="glyphicon glyphicon-edit" title="编辑"></a>
												<a href="#nowhere" id="deleteAddress" class="glyphicon glyphicon-minus-sign" title="删除"></a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="order">
								<table >
									<tr class="titleTr">
										<th width="900px">商品信息</th>
										<th width="200px">数量</th>
										<th width="200px">金额</th>
										<th width="200px">订单编号</th>
									</tr>
									<tr>
										<td>
											<a class="pro" href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
											<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
										</td>
										<td><span id="count">1</span></td>
										<td>￥<span id="totalprice">199</span></td>
										<td><a class="ord" href="" title="订单详细信息"><span id="orderID">0123456789</span></a></td>
									</tr>
									<tr>
										<td>
											<a class="pro" href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
											<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
										</td>
										<td><span id="count">1</span></td>
										<td>￥<span id="totalprice">199</span></td>
										<td><a class="ord" href="" title="订单详细信息"><span id="orderID">0123456789</span></a></td>
									</tr>
									<tr>
										<td>
											<a class="pro" href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
											<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
										</td>
										<td><span id="count">1</span></td>
										<td>￥<span id="totalprice">199</span></td>
										<td><a class="ord" href="" title="订单详细信息"><span id="orderID">0123456789</span></a></td>
									</tr>
									<tr>
										<td>
											<a class="pro" href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
											<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
										</td>
										<td><span id="count">1</span></td>
										<td>￥<span id="totalprice">199</span></td>
										<td><a class="ord" href="" title="订单详细信息"><span id="orderID">0123456789</span></a></td>
									</tr>
									<tr>
										<td>
											<a class="pro" href=""><img src="${pageContext.request.contextPath }/resources/images/protrait.png"></a>
											<p>Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
										</td>
										<td><span id="count">1</span></td>
										<td>￥<span id="totalprice">199</span></td>
										<td><a class="ord" href="" title="订单详细信息"><span id="orderID">0123456789</span></a></td>
									</tr>
								</table>
								<div id="i_paging">
									<nav aria-label="Page navigation">
										<ul class="pagination pagination-lg">
											<li class="prev">
												<span>
													<span aria-hidden="true">&laquo;</span>
												</span>
											</li>
											<li class="active pageOne"><span>1</span></li>
											<li class="next">
												<span>
													<span aria-hidden="true">&raquo;</span>
												</span>
											</li>
										</ul>
									</nav>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="collect">我的收藏</div>
							<div role="tabpanel" class="tab-pane fade" id="cart">购物车</div>
						</div>
					</div>
					<div id="i_center_footer">
						<h3>我是有底线的</h3>
					</div>
				</div>
				<div id="i_right" class="col-sm-1 col-xs-1">
					<div id="i_personal">
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
						</div>
					</div>
					<div class="backTop animated fadeInUp">
						<a href="javasript:;" id="i_topBtn"><img src="${pageContext.request.contextPath }/resources/images/backTop.jpg"></a>
					</div>
				</div>
			</div>
		</div>

		<div id="modPortraitDiv" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<form id="modProtraitForm" enctype="multipart/form-data">
						<div class="addImage form-group">
							<label for="InputImage">&nbsp;更改头像</label>&nbsp;&nbsp;&nbsp;
							<input type="file" class="form-control" name="image" id="image" accept="image/*" value="" />
						</div>
						<button type="button" id="modProtraitSub" class="btn btn-default">上传</button>
					</form>
				</div>
			</div>
		</div>

		<div id="userInfoDiv" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="editInfor">
						<div class="form-group">
							<label for="InputNickNameEdit">昵称</label>
							<input type="text" class="form-control" id="InputEditNickName" maxlength="8" name="uNickName" placeholder="不超过8个字">
						</div>
						<div class="form-group">
							<label for="InputNameEdit">名字</label>
							<input type="text" class="form-control" id="InputEditName" required="required" maxlength="5" name="uName"
							 placeholder="名字">
						</div>
						<div class="form-group">
							<label for="InputEmailEdit">邮箱</label>
							<input type="email" class="form-control" id="InputEditEmail" name="uEmail" placeholder="邮箱">
						</div>
						<button type="button" id="editNameSub" class="btn btn-default">保存</button>
					</div>
				</div>
			</div>
		</div>

		<div id="addContactDiv" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="addAddress">
						<div class="form-group">
							<label for="InputAddressRegister">地址</label>
							<input type="text" class="form-control" id="InputAddress" name="address" placeholder="地址">
						</div>
						<div class="form-group">
							<label for="InputReTelgister">电话</label>
							<input type="text" class="form-control" id="InputTel" name="tel" placeholder="电话">
						</div>
						<div class="form-group">
							<label for="InputNameRegister">收货人</label>
							<input type="text" class="form-control" id="InputAddressName" name="addressName" placeholder="名字">
						</div>
						<button type="button" id="addAddressSub" class="btn btn-default">保存</button>
					</div>
				</div>
			</div>
		</div>

		<!--<div id="modContactDiv" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="form-group">
						<label for="InputAddressRegister">地址</label>
						<input type="text" class="form-control" id="InputAddress" name="address" placeholder="地址">
					</div>
					<div class="form-group">
						<label for="InputReTelgister">电话</label>
						<input type="text" class="form-control" id="InputTel" name="tel" placeholder="电话">
					</div>
					<div class="form-group">
						<label for="InputNameRegister">收货人</label>
						<input type="text" class="form-control" id="InputAddressName" name="addressName" placeholder="名字">
					</div>
					<button type="button" id="addAddressSub" class="btn btn-default">保存</button>
				</div>
			</div>
		</div>-->

		<script type="text/javascript">
			window.onload = function() {
				//1、获取页面中的按钮
				var topBtn = document.getElementById("i_topBtn");
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
		</script>
	</body>
</html>
