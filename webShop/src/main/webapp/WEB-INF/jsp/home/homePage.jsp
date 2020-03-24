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
			function putclick() {
				var data={
						productList :[{id:1,productName:"putp"},{id:2,productName:"putp"},{id:3,productName:"putp"}],
						"productMaxCount":5,
						"categoryName":"puta"
				};
				//data.productList=[{id:1,productName:"putp"},{id:2,productName:"putp"},{id:3,productName:"putp"}];
				$.ajax({
				  url: "${pageContext.request.contextPath }/homePage/put",
				  type: "put",//这里表明是put post delete get
				  contentType: "application/json; charset=utf-8",
				  data: JSON.stringify(data),
				success: function (data,status) {
					alert("第一个产品名:"+JSON.parse(data).productList[0].productName);
				}
				});
			}
			function deleteclick() {
				//由于后端控制器无法自动匹配List<>所以想要批量操作（例如一次性下单买多个产品）就只有两种方法
				//这里展示第一种方法使用ajax将数组装进一个json内传递给后台进行操作
				//第二种使用表单的name匹配https://blog.csdn.net/whut2010hj/article/details/84582143
				//想要在表单上使用put post delete patch 请求，看https://www.cnblogs.com/hamawep789/p/10896030.html
				
				//这里的data对象对应ProductExecution,前往web层查看HomeController的testDelete函数的参数了解更多
				var data={
						productList :[{id:1,productName:"putp"},{id:2,productName:"putp"},{id:3,productName:"putp"}],
						"productMaxCount":5,
						"categoryName":"puta"
				};
				$.ajax({
				  url: "${pageContext.request.contextPath }/homePage/put", 
				  type: "delete",//这里表明是put post delete get
				  contentType: "application/json; charset=utf-8",//必须要有使用了这个表明你传给我的data是完整的一个对象而不是k，v对
				  data: JSON.stringify(data),//必须使用JSON.stringify()转换data
				success: function (data,status) {
					//这里测试例后台返回的是一个productExecution对象的json转换后的json字符串
					//
					alert("第一个产品名:"+JSON.parse(data).productList[0].productName);
				}
				});
			}
			function patchclick() {
				//由于后端控制器无法自动匹配List<>所以想要批量操作（例如一次性下单买多个产品）就只有两种方法
				//这里展示第一种方法使用ajax将数组装进一个json内传递给后台进行操作
				//第二种使用表单的name匹配https://blog.csdn.net/whut2010hj/article/details/84582143
				var data={
						productList :[{id:1,productName:"putp"},{id:2,productName:"putp"},{id:3,productName:"putp"}],
						"productMaxCount":5,
						"categoryName":"puta"
				};
				$.ajax({
				  url: "${pageContext.request.contextPath }/homePage/put", 
				  type: "patch",//这里表明是put post delete get
				  contentType: "application/json; charset=utf-8",//必须要有
				  data: JSON.stringify(data),//必须使用JSON.stringify()转换data
				success: function (data,status) {
					//这里测试例后台返回的是一个productExecution对象的json转换后的json字符串
					//
					alert("第一个产品名:"+JSON.parse(data).productList[0].productName);
				}
				});
			}
		</script>
	</head>
	<body>
			
			<input id="searchInput" name="" type="text" style="width: 100px;height: 25px;"/>
			<button onclick="searchBtnClick()">框内输入电脑并查询</button>
		
		'${categories}'<br>
		<button onclick="putclick()">使用ajax完成 put 测试</button>
		<button onclick="deleteclick()">使用ajax完成 delete 测试</button>
		<button onclick="patchclick()">使用ajax完成patch 测试</button>
	</body>
</html>
