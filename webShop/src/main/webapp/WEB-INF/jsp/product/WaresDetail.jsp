<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品详情页面</title>

		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<!-- IE将使用最新的引擎渲染网页 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
		 crossorigin="anonymous">

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
		 crossorigin="anonymous">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		 crossorigin="anonymous"></script>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/animate.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/swiper.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/waresDetail.css" />
		<script src="${pageContext.request.contextPath }/resources/js/swiper.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath }/resources/js/waresDetail.js" type="text/javascript" charset="utf-8"></script>

		<style type="text/css">
			@media screen and (max-width:1600px) {
				#wd_left {
					display: none;
				}

				#wd_personal {
					display: none;
				}
			}
		</style>

	</head>
	<body>
		<div id="wd_container" class="container-fluid">
			<div class="row">
				<div id="wd_top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-2 col-xs-2"></div>
						<div id="wd_logo" class="col-sm-4 col-xs-4">
							<a href="${pageContext.request.contextPath }/home" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
						<div class="col-sm-1 col-xs-1"></div>
						<div id="wd_search" class="col-sm-4 col-xs-4">
							<form action="${pageContext.request.contextPath }/search" method="get" id="searchForm" name="searchForm">
								<div class="input-group input-group-size">
									<input type="text" class="form-control textRule" name="searchText" placeholder="  大家都在搜">
									<span class="input-group-btn">
										<button type="submit" class="btn btn-default btn-lg glyphicon glyphicon-search" id="searchBtn">搜索</button>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div id="wd_left" class="col-sm-1 col-xs-1"></div>
				<div id="wd_center" class="col-sm-10 col-xs-10">
					<div id="wd_center_infor">
						<div class="row">
							<div class="infor_img col-sm-6 col-xs-6">
								<div>
									<img src="${pageContext.request.contextPath }/resources/images/y.jpg" class="bigImg">
									<div class="smallImgDiv">
										<img src="src/image/x.jpg" bigImageUrl="${pageContext.request.contextPath }/resources/images/x.jpg" class="smallImg">
										<img src="src/image/j.jpg" bigImageUrl="${pageContext.request.contextPath }/resources/images/j.jpg" class="smallImg">
										<img src="src/image/zys.png" bigImageUrl="${pageContext.request.contextPath }/resources/images/zys.png" class="smallImg">
									</div>
									<div class="img4load" style="display: none;">

									</div>
								</div>
							</div>
							<div class="infor_text col-sm-6 col-xs-6">
								<div>
									<p class="productName">Hisense/海信 LED40EC520UA 40英寸4K智能平板液晶电视机WIFI网络</p>
									<div id="floatSection">
										<p class="char">天天低价 聚划算</p>
									</div>
									<div id="priceDiv">
										<ul>
											<li>市场价 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥<i><del><span class="oldPrice">29999</span></del></i></li>
											<li>店内价格 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥<span class="newPrice">19999</span></li>
										</ul>
									</div>
									<div class="volaccess">
										<p>销量&nbsp;&nbsp;<span>666</span></p>
									</div>
									<div class="volaccess">
										<p>评价&nbsp;&nbsp;<span>666</span></p>
									</div>
									<div id="storeDiv">
										<span>数量：</span>
										<input type="number" name="" id="buyCount" min="1" value="1" />
										<span>&nbsp;&nbsp;&nbsp;&nbsp;库存：</span><span id="storeCount">66</span>
									</div>
									<div class="serviceNode">
										<p>服务承诺 正品保证 极速退款 赠运费险 七天无理由退换</p>
									</div>
									<div class="buyBtns">
										<a href=""><button type="button" id="buyBtn">立即下单</button></a>
										<button type="button" id="addCart">加入购物车</button>
										<button type="button" id="addCollect">添加收藏</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="wd_center_intro">
						<div>
							<div class="col-sm-1 col-xs-1"> </div>
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#waresDetail" aria-controls="waresDetail" role="tab"
									 data-toggle="tab">商品详情</a></li>
								<li role="presentation"><a href="#access" aria-controls="access" role="tab" data-toggle="tab">评价</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane fade in active introImage" id="waresDetail">
									<img src="${pageContext.request.contextPath }/resources/images/x.jpg">
									<img src="${pageContext.request.contextPath }/resources/images/j.jpg">
									<img src="${pageContext.request.contextPath }/resources/images/y.jpg">
								</div>
								<div role="tabpanel" class="tab-pane fade introAccess" id="access">
									<div class="accessView">
										<div class="accessContent">
											很好
										</div>
										<div class="acessDate">
											2000-01-01
										</div>
										<div id="accessName">
											无痕
										</div>
									</div>
									<div class="accessView">
										<div class="accessContent">
											很好
										</div>
										<div class="acessDate">
											2000-01-01
										</div>
										<div id="accessName">
											无痕
										</div>
									</div>
									<div class="accessView">
										<div class="accessContent">
											很好
										</div>
										<div class="acessDate">
											2000-01-01
										</div>
										<div id="accessName">
											无痕
										</div>
									</div>
									<div class="accessView">
										<div class="accessContent">
											很好
										</div>
										<div class="acessDate">
											2000-01-01
										</div>
										<div id="accessName">
											无痕
										</div>
									</div>
									<div id="wd_paging" page="3">
										<nav aria-label="Page navigation">
											<ul class="pager pager-lg">
												<li class="prevPage"><a href="javascript:;">Previous</a></li>
												<li class="nextPage"><a href="javascript:;">Next</a></li>
											</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="wd_center_footer">
						<h3>我是有底线的</h3>
					</div>
				</div>
				<div id="wd_right" class="col-sm-1 col-xs-1">
					<div id="wd_personal">
						<div>
							<a href="javascript:;" id="personal" title="个人中心"><span class="glyphicon glyphicon-user " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div>
							<a href="javascript:;" id="shoppingCart" title="购物车"><span class="glyphicon glyphicon-shopping-cart " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div>
							<a href="javascript:;" id="collection" title="收藏"><span class="glyphicon glyphicon-heart " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
						<div>
							<a href="javascript:;" id="outLogin" title="退出登录"><span class="glyphicon glyphicon-off " style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
					</div>
					<div class="backTop animated fadeInUp">
						<a href="javasript:;" id="wd_topBtn"><img src="${pageContext.request.contextPath }/resources/images/backTop.jpg"></a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			window.onload = function() {
				//1、获取页面中的按钮
				var topBtn = document.getElementById("wd_topBtn");
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
