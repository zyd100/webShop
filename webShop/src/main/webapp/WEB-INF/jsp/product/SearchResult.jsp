<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>搜索结果页面</title>

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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/searchresult.css" />
		<script src="${pageContext.request.contextPath }/resources/js/searchresult.js" type="text/javascript" charset="utf-8"></script>

		<style type="text/css">
			@media screen and (max-width:1600px) {
				#sr_left {
					display: none;
				}

				#sr_personal {
					display: none;
				}
			}
		</style>

	</head>
	<body>
		<div id="sr_container" class="container-fluid">
			<div class="row">
				<div id="sr_top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-2 col-xs-2"></div>
						<div id="sr_logo" class="col-sm-4 col-xs-4">
							<a href="Home.html" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
						<div class="col-sm-1 col-xs-1"></div>
						<div id="sr_search" class="col-sm-4 col-xs-4">
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
				<div id="sr_left" class="col-sm-1 col-xs-1"></div>
				<div id="sr_center" class="col-sm-10 col-xs-10">
					<div id="sr_center_wares">
						<div id="sr_failed" class="col-sm-12 col-xs-12">
							<p>什么也没找到！！</p>
						</div>
						<div id="sr_wares" class="row col-sm-12 col-xs-12">
						</div>
						<div id="sr_paging">
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
					<div id="sr_center_footer">
						<h3>我是有底线的</h3>
					</div>
				</div>
				<div id="sr_right" class="col-sm-1 col-xs-1">
					<div id="sr_personal">
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
						<a href="javasript:;" id="sr_topBtn"><img src="${pageContext.request.contextPath }/resources/images/backTop.jpg"></a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			window.onload = function() {
				//1、获取页面中的按钮
				var topBtn = document.getElementById("sr_topBtn");
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
				var searchInfo = JSON.parse('${searchResult}');
				$(":text").val('${searchText}');
				
				$("div#sr_personal").attr("userName",'${sessionScope.loginUserName}');
				
				initSeaResult(searchInfo);
			});
		</script>
	</body>
</html>
