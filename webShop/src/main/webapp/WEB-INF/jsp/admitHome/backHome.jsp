<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>后台管理</title>

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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/backhome.css" />
		<script src="${pageContext.request.contextPath }/resources/js/backhome.js" type="text/javascript" charset="utf-8"></script>

	</head>
	<body>
		<div id="container" class="container-fluid">
			<div class="row">
				<div id="top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-1 col-xs-1"></div>
						<div id="logo" class="col-sm-4 col-xs-4">
							<a href="javascript:;" title="logo"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
						<div class="col-sm-9 col-xs-9">
							<a href="${pageContext.request.contextPath }/admin/logoff;" id="outLogin" title="退出登录"><span class="glyphicon glyphicon-off "
								 style="font-size: 30px;color: #5F9EA0 ;"></span></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div id="manageContent" class="col-sm-12 col-xs-12">
					<div>

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#productManagement" aria-controls="productManagement" role="tab"
								 data-toggle="tab">商品管理</a></li>
							<li role="presentation" class="userLi"><a href="#userManagement" aria-controls="userManagement" role="tab"
								 data-toggle="tab">用户管理</a></li>
							<li role="presentation" class="orderLi"><a href="#orderManagement" aria-controls="orderManagement" role="tab"
								 data-toggle="tab">订单管理</a></li>
							<li role="presentation" class="accessLi"><a href="#accessManagement" aria-controls="accessManagement" role="tab"
								 data-toggle="tab">评论管理</a></li>
							<li role="presentation" class="disabled"><a href="#essayManagement" aria-controls="essayManagement" role="tab"
								 data-toggle="tab">文章管理</a></li>
							<li role="presentation" class="disabled"><a href="#adManagement" aria-controls="adManagement" role="tab"
								 data-toggle="tab">广告管理</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="productManagement">
								<div>

									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist">
										<li role="presentation" class="active"><a href="#product" aria-controls="product" role="tab" data-toggle="tab">商品</a></li>
										<li role="presentation" class="sortLi"><a href="#sort" aria-controls="sort" role="tab" data-toggle="tab">分类</a></li>
									</ul>

									<!-- Tab panes -->
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane fade in active" id="product">
											<div class="productSearch">
												<div class="input-group input-group-size">
													<input type="text" class="form-control textRule" name="searchText" placeholder="搜索商品">
													<span class="input-group-btn">
														<button type="submit" class="btn btn-default btn-default glyphicon glyphicon-search" id="searchProductBtn">搜索</button>
													</span>
												</div>
											</div>
											<button type="button" class="addProduct btn btn-default" data-toggle="modal" data-target="#addProductDiv">新增商品<span
												 class="glyphicon glyphicon-plus-sign"></span></button>
											<table class="table table-striped table-hover">
												<tr class="trTitle">
													<th>id</th>
													<th>商品名</th>
													<th>类别名</th>
													<th>描述</th>
													<th>店内价格</th>
													<th>市场价格</th>
													<th>库存</th>
													<th>图片</th>
													<th>管理</th>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>沃尔克基</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>666</td>
													<td>66</td>
													<td>6</td>
													<td><button type="button" class="productImg">图片</button></td>
													<td>
														<button type="button" class="productModify" data-toggle="modal" data-target=".proModDiv">修改</button>
														<button type="button" class="productDelete">删除 </button>
													</td>
												</tr>
												<tr>
													<td colspan="9">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
													</td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>沃尔克基</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>666</td>
													<td>66</td>
													<td>7</td>
													<td><button type="button" class="productImg">图片</button></td>
													<td>
														<button type="button" class="productModify" data-toggle="modal" data-target=".proModDiv">修改</button>
														<button type="button" class="productDelete">删除 </button>
													</td>
												</tr>
												<tr>
													<td colspan="9">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
													</td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>沃尔克基</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>666</td>
													<td>66</td>
													<td>8</td>
													<td><button type="button" class="productImg">图片</button></td>
													<td>
														<button type="button" class="productModify" data-toggle="modal" data-target=".proModDiv">修改</button>
														<button type="button" class="productDelete">删除 </button>
													</td>
												</tr>
												<tr>
													<td colspan="9">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
														<img src="${pageContext.request.contextPath }/resources/images/protrait.png">
													</td>
												</tr>
											</table>
											<div id="paging">
												<nav aria-label="Page navigation">
													<ul class="pager pager-lg">
														<li class="prevPage"><a href="javascript:;">Previous</a></li>
														<li class="nextPage"><a href="javascript:;">Next</a></li>
													</ul>
												</nav>
											</div>
										</div>
										<div role="tabpanel" class="tab-pane fade" id="sort">
											<div class="sortSearch">
												<div class="input-group input-group-size">
													<input type="text" class="form-control textRule" name="searchText" placeholder="搜索商品类别">
													<span class="input-group-btn">
														<button type="submit" class="btn btn-default btn-default glyphicon glyphicon-search" id="searchSortBtn">搜索</button>
													</span>
												</div>
											</div>
											<button type="button" class="addSort btn btn-default" data-toggle="modal" data-target="#addSortDiv">新增类别<span
												 class="glyphicon glyphicon-plus-sign"></span></button>
											<table class="table table-striped table-hover">
												<tr class="trTitle">
													<th>id</th>
													<th>类别名</th>
													<th>描述</th>
													<th>管理</th>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
												<tr>
													<td>12345678</td>
													<td>workic</td>
													<td>这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌这是一首简单的小情歌</td>
													<td>
														<button type="button" class="sortModify">修改</button>
													</td>
												</tr>
												<tr>
													<td>类别名:<input type="text" class="form-control textRule" id="sortName" name="sortName"></td>
													<td colspan="2">描述：<input type="text" class="form-control textRule" id="sortDetail" name="sortDetail"></td>
													<td><button type="button" class="saveChange">保存</button></td>
												</tr>
											</table>
											<div id="paging">
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
							<div role="tabpanel" class="tab-pane fade" id="userManagement">
								<div class="userSearch">
									<div class="input-group input-group-size">
										<input type="text" class="form-control textRule" name="searchText" placeholder="搜索用户">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-default btn-default glyphicon glyphicon-search" id="searchUserBtn">搜索</button>
										</span>
									</div>
								</div>
								<table class="table table-striped table-hover">
									<tr class="trTitle">
										<th>id</th>
										<th>昵称</th>
										<th>名称</th>
										<th>邮箱</th>
										<th>注册时间</th>
										<th>用户类型</th>
										<th>管理</th>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>管理员</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>用户</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>管理员</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>用户</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>管理员</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>用户</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>管理员</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>用户</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>管理员</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>workic</td>
										<td>沃尔克基</td>
										<td>12345678@qq.com</td>
										<td>2020-03-24 11:52:50</td>
										<td>用户</td>
										<td>
											<button type="button" class="userModify">修改</button>
											<button class="userDelete" type="button">删除</button>
										</td>
									</tr>
									<tr>
										<td>昵称:<input type="text" class="form-control" id="uNickName" maxlength="8" name="uNickName"></td>
										<td>名称:<input type="text" class="form-control" id="uName" required="required" maxlength="5" name="uName"></td>
										<td>邮箱:<input type="email" class="form-control" id="uEmail" name="uEmail"></td>
										<td colspan="4"><button type="button" class="saveChange">保存</button></td>
									</tr>
								</table>
								<div id="paging">
									<nav aria-label="Page navigation">
										<ul class="pager pager-lg">
											<li class="prevPage"><a href="javascript:;">Previous</a></li>
											<li class="nextPage"><a href="javascript:;">Next</a></li>
										</ul>
									</nav>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="orderManagement">
								<div class="orderSearch">
									<div class="input-group input-group-size">
										<input type="text" class="form-control textRule" name="searchText" placeholder="搜索订单">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-default btn-default glyphicon glyphicon-search" id="searchOrderBtn">搜索</button>
										</span>
									</div>
								</div>
								<table class="table table-striped table-hover">
									<tr class="trTitle">
										<th>订单号</th>
										<th>付款金额</th>
										<th>买家名称</th>
										<th>创建时间</th>
										<th>支付时间</th>
										<th>发货时间</th>
										<th>确认收货时间</th>
										<th>订单状态</th>
										<th>修改状态</th>
										<th>管理</th>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
									<tr>
										<td><a href="#nowhere">12345678</a></td>
										<td>￥999.99</td>
										<td>沃尔克基</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>2020-03-24 11:52:50</td>
										<td>待评价</td>
										<td>
											<select name="orderStatus" class="form-control">
												<option value="0">已付款</option>
												<option value="1">已下单</option>
												<option value="2">已配送</option>
												<option value="3">已寄到</option>
											</select>
										</td>
										<td><button class="orderDelete" type="button">删除</button></td>
									</tr>
								</table>
								<div id="paging">
									<nav aria-label="Page navigation">
										<ul class="pager pager-lg">
											<li class="prevPage"><a href="javascript:;">Previous</a></li>
											<li class="nextPage"><a href="javascript:;">Next</a></li>
										</ul>
									</nav>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="accessManagement">
								<table class="table table-striped table-hover">
									<tr class="trTitle">
										<th>商品id</th>
										<th>用户名称</th>
										<th>评论id</th>
										<th>评论内容</th>
										<th>评论时间</th>
										<th>评论状态</th>
										<th>修改状态</th>
										<th>管理</th>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
									<tr>
										<td>12345678</td>
										<td>沃尔克基</td>
										<td>1234567</td>
										<td>宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意宝贝太好了很满意</td>
										<td>2020-03-24 11:52:50</td>
										<td>已通过</td>
										<td>
											<select name="accessStatus" class="form-control">
												<option value="1">审批中</option>
												<option value="2">批准</option>
											</select>
										</td>
										<td><button type="button" class="accessDelete">删除</button></td>
									</tr>
								</table>
								<div id="paging">
									<nav aria-label="Page navigation">
										<ul class="pager pager-lg">
											<li class="prevPage"><a href="javascript:;">Previous</a></li>
											<li class="nextPage"><a href="javascript:;">Next</a></li>
										</ul>
									</nav>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="essayManagement">文章管理（无）</div>
							<div role="tabpanel" class="tab-pane fade" id="adManagement">广告管理（无）</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div id="addProductDiv" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<form id="addProForm" enctype="multipart/form-data">
						<div class="form-group">
							<label for="InputProductName">&nbsp;商品名</label>
							<input type="text" class="form-control" maxlength="20" required="required" id="productName" name="productName"
							 placeholder="商品名(必填)">
						</div>
						<div class="form-group">
							<label for="InputSortName">&nbsp;类别名</label>
							<select name="categoryId" class="categorySelect form-control">
								
							</select>
						</div>
						<div class="form-group">
							<label for="InputShopPrice">&nbsp;店内价格</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="shopPrice"
							 name="shopPrice" placeholder="店内价格(必填)">
						</div>
						<div class="form-group">
							<label for="InputPrice">&nbsp;市场价格</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="price"
							 name="price" placeholder="市场价格(必填)">
						</div>
						<div class="form-group">
							<label for="InputQuantity">&nbsp;库存</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="quantity"
							 name="quantity" placeholder="库存(必填)">
						</div>
						<div class="form-group">
							<label for="InputDetail">&nbsp;描述</label>
							<input type="text" class="form-control" maxlength="150" required="required" id="detail" name="explain" placeholder="描述(必填)">
						</div>
						<div class="addImage form-group">
							<label for="InputImage">&nbsp;添加图片</label>&nbsp;&nbsp;&nbsp;
							<input type="file" class="form-control" name="imageFile" id="image" accept="image/*" value="" />
						</div>
						<button type="button" id="addProductSub" class="btn btn-default">上传</button>
					</form>
				</div>
			</div>
		</div>

		<div id="addSortDiv" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<form id="addSortForm">
						<div class="form-group">
							<label for="InputSortName">&nbsp;类别名</label>
							<input type="text" class="form-control" maxlength="10" required="required" id="sortName" name="sortName" placeholder="类别名(必填)">
						</div>
						<div class="form-group">
							<label for="InputQuantity">&nbsp;描述</label>
							<input type="text" class="form-control" maxlength="150" required="required" id="detail" name="detail" placeholder="描述(必填)">
						</div>
						<button type="button" id="addSortSub" class="btn btn-default">上传</button>
					</form>
				</div>
			</div>
		</div>

		<div class="proModDiv modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<form id="proModForm" enctype="multipart/form-data">
						<div class="form-group">
							<label for="InputProductId">&nbsp;id</label>
							<input type="text" class="form-control" maxlength="20" required="required" readonly="readonly" id="productId" name="id"
							 value="">
						</div>
						<div class="form-group">
							<label for="InputSortName">&nbsp;类别名</label>
							<select name="categoryId" class="categorySelect form-control">

							</select>
						</div>
						<div class="form-group">
							<label for="InputShopPrice">&nbsp;店内价格</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="shopPrice"
							 name="shopPrice" placeholder="店内价格(必填)">
						</div>
						<div class="form-group">
							<label for="InputPrice">&nbsp;市场价格</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="price"
							 name="price" placeholder="市场价格(必填)">
						</div>
						<div class="form-group">
							<label for="InputQuantity">&nbsp;库存</label>
							<input type="number" class="form-control" oninput="checkLength(value, this);" min="0" required="required" id="quantity"
							 name="quantity" placeholder="库存(必填)">
						</div>
						<div class="form-group">
							<label for="InputDetail">&nbsp;描述</label>
							<input type="text" class="form-control" maxlength="150" required="required" id="detail" name="explain" placeholder="描述(必填)">
						</div>
						<div class="addImage form-group">
							<label for="InputImage">&nbsp;更改主图片</label>&nbsp;&nbsp;&nbsp;
							<input type="file" class="form-control" name="imageFile" id="image" accept="image/*" value="" />
						</div>
						<div class="addImages form-group">
							<label for="InputImages">&nbsp;更改图片</label>&nbsp;&nbsp;&nbsp;
							<button type="button" id="continueAddImage">继续添加</button>
							<input type="file" class="fisrtFile form-control" name="otherImages" id="image" accept="image/*" value="" />
						</div>
						<button type="button" id="proModSub" class="btn btn-default">上传</button>
					</form>
				</div>
			</div>
		</div>
		<script>
			var ctx = "${pageContext.request.contextPath }";
			var imgpath="/resources/images/";
			var ctxImg=ctx+imgpath;

			var adminName='${sessionScope.adminUserName}'
		</script>
	</body>
</html>
