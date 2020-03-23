<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script
			src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
		<script>
			function searchBtnClick(){
				
				window.location='${pageContext.request.contextPath }/homePage/search/'+$("#searchInput").val();
			}
		</script>
	</head>
	<body>
			
			<input id="searchInput" name="" type="text" style="width: 100px;height: 25px;"/>
			<button onclick="searchBtnClick()">输入电脑并查询</button>
		
		'${categories}'<br>

	</body>
</html>
