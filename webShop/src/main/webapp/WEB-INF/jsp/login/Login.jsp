<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录注册页面</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

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

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/login.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/animate.min.css" />
		<script src="${pageContext.request.contextPath }/resources/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div id="l_container" class="container-fluid">
			<div class="row">
				<div id="l_top" class="col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-sm-1 col-xs-1">
						</div>
						<div id="l_logo" class="col-sm-4 col-xs-4">
							<a href="${pageContext.request.contextPath }/home" title="返回首页"><img src="${pageContext.request.contextPath }/resources/images/logo.png"></a>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div id="l_left" class="col-sm-6 col-xs-6 bgColor">
					<div id="registerDiv" class="animated zoomIn">
						<h3>注册</h3>
						<form name="registerForm" id="registerForm" action="${pageContext.request.contextPath }/users" method="post" accept-charset="utf-8">
							<input type="hidden" name="_method" value="post" />
							<div class="form-group">
								<label for="InputNickNameRegister">昵称</label>
								<input type="text" class="form-control" id="InputNickName" maxlength="8" name="uNickName" placeholder="不超过8个字(非必填)">
							</div>
							<div class="form-group">
								<label for="InputNameRegister">名字</label>
								<input type="text" class="form-control" id="InputName" required="required" maxlength="5" name="uName" placeholder="名字">
							</div>
							<div class="form-group">
								<label for="InputEmailRegister">邮箱</label>
								<input type="email" class="form-control" id="InputEmail" name="uEmail" placeholder="邮箱(非必填)">
							</div>
							<div class="form-group">
								<label for="InputPasswordRegister">密码</label>
								<input type="password" class="form-control" id="InputPassword" required="required" maxlength="13" name="uPassword"
								 placeholder="8~13个字符">
							</div>
							<button type="submit" id="registerSub" class="btn btn-default">注册</button>
						</form>
						<br>
						<button type="button" class="btn btn-default" onclick="loginSH()">返回登录</button>
					</div>
				</div>
				<div id="l_right" class="col-sm-6 col-xs-6 bgColor">
					<div id="loginDiv" class="animated zoomIn">
						<h3>登录</h3>
						<form name="loginForm" id="loginForm" action="${pageContext.request.contextPath }/users/login" method="get">
							<input type="hidden" name="_method" value="get">
							<div class="form-group">
								<label for="InputNameLogin">名字</label>
								<input type="text" class="form-control" maxlength="5" required="required" id="LoginName" name="uName" placeholder="名字">
							</div>
							<div class="form-group">
								<label for="InputPasswordLogin">密码</label>
								<input type="password" class="form-control" maxlength="13" required="required" id="LoginPassword" name="uPassword" placeholder="密码">
							</div>
							<button type="submit" id="loginSub" class="btn btn-default">登录</button>
						</form>
						<br>
						<button type="button" class="btn btn-default" onclick="registerSH();">前往注册</button>
					</div>
				</div>
			</div>
			<div id="foot" class="col-sm-12 col-xs-12">
				<h3>欢迎加入我们</h3>
			</div>
		</div>
	</body>
</html>
