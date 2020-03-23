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
			function searchNextClick(){
				var data={};
				data.searchText='电脑';
				data.offset=1;
				data.limit=2;
				$.post('${pageContext.request.contextPath }/homePage/search',data,function(data,status){
					var result=JSON.parse(data);
					alert(result.error);
				});
			}
		</script>
	</head>
	<body>
'${products.productList}'<br>
<button onclick="searchNextClick()" style="width: auto;height: 20px;">查看offset=1，limit=2搜索出来的商品</button>
	</body>
</html>
