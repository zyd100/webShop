<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录后台</title>
		
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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/backlogin.css" />
		<script src="" type="text/javascript" charset="utf-8"></script>
		
		<style type="text/css">
			@media screen and (max-width:1600px) {
				#h_left {
					display: none;
				}
		
				#h_personal {
					display: none;
				}
			}
		</style>
		
	</head>
	<body>
		<div id="container">
			<div class="row">
				<div id="top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-1 col-xs-1">
						</div>
						<div id="logo" class="col-sm-4 col-xs-4">
							<a href="javascript:;" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
					</div>
				</div>
			</div>
			<div id="loginDiv" class="animated zoomIn">
				<h3>后台登录</h3>
				<form name="loginForm" id="loginForm" action="${pageContext.request.contextPath }/admin/login" method="post">
					<div class="form-group">
						<label for="InputNameLogin">名字</label>
						<input type="text" class="form-control" maxlength="5" required="required" id="LoginName" name="userName" placeholder="名字">
					</div>
					<div class="form-group">
						<label for="InputPasswordLogin">密码</label>
						<input type="password" class="form-control" maxlength="13" required="required" id="LoginPassword" name="password" placeholder="密码">
					</div>
					<button type="submit" id="loginSub" class="btn btn-default">登录</button>
				</form>
				<br>
			</div>
		</div>
	</body>
</html>
