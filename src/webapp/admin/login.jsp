<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common.jsp" %>
<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>码蚁商城后台管理系统</title>
		<link href="favicon.ico" rel="shortcut icon">
		<link rel="stylesheet" href="${ctx}/admin/css/style.css" type="text/css" />
		
		<script type="text/javascript">
			/* function checkForm(){
				//获取表单参数
				var username = document.getElementById("username");
				var password = document.getElementById("password");
				
				//非空校验
				if(username.value==""){
					alert("管理员名称不能为空！")
					return;
				}
			} */
		</script>
	</head>

	<body>
		<div id="container">
			<form action="${ctx}/AdminServlet?action=login" method="post">
				<div class="login">码蚁商城后台管理系统
				 	<span style="color:red;font-size: 14px">${errorMessage}</span>
				</div>
				<div class="username-text">用户名:</div>                                        
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input id="username" type="text" name="username" placeholder="请输入用户名" />
				</div>
				<div class="password-field">
					<input id="password" type="password" name="password" placeholder="请输入密码" />
				</div>
				<!-- <input type="checkbox" name="remember-me" id="remember-me" />
				<label for="remember-me">记住管理员名称</label> -->
				<div  class="zhuce-button">
					<input type="button"  class="zhuce-button" id="regist" value="免费注册">
				</div>      
				<div class="forgot-usr-pwd"></div>
				<input type="submit" name="submit" value="登录" onclick="checkForm()"/>
			</form>
		</div>

        <script src="${ctx}/admin/js/jquery.min.js"/>
	<script>
        $("#regist").click(function () {
            alert("注册按钮被触发了");
            $(window).attr('location', '${ctx}/admin/regist.jsp');
    	});
	</script>
</body>
</html>