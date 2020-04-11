/*注册框显示隐藏*/
function registerSH() {
	$("#registerDiv").show();
	$("#loginDiv").hide();
}

/* 登录框显示隐藏 */
function loginSH() {
	$("#registerDiv").hide();
	$("#loginDiv").show();
}

/* 表单提交 */
$(document).ready(function() {
	$("form#registerForm").on("click", "button#registerSub", function() {
		var nickName = $(this).closest("form").find("input#InputNickName").val();
		var name = $(this).closest("form").find("input#InputName").val();
		var email = $(this).closest("form").find("input#InputEmail").val();
		var password = $(this).closest("form").find("input#InputPassword").val();
		var data = {
			nickName: nickName,
			userName: name,
			email: email,
			password: password
		}
		$.ajax({
			type: "post",
			url: cxt+"/users",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success:function(data){
				if(JSON.parse(data).success){
					location.reload();
					alert("注册成功~");
				}
			}
		});
	});
});
