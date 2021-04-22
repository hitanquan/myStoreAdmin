<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/admin/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户注册</title>
	<link rel="stylesheet" href="${ctx}/admin/css/style.css" type="text/css" />
</head>

<body>
	<div id="container">

	<form action="${ctx}/AdminServlet?action=regist" method="post" id="regist_form">
				<div class="login">管理员注册
				 	<%-- <span style="color:red;font-size: 14px">${errorMessage}</span> --%>
				</div>
				<div class="username-text">用户名:</div>                                        
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input id="username" type="text" name="username" placeholder="请输入用户名" />
				</div>
				<div class="password-field">
					<input id="password" type="password" name="password" placeholder="请输入密码" />
				</div>
			
				<div>
					<input type="button"  class="zhuce-button2" id="admin_regist" value="立即注册">
				</div>      
			</form>
		</div>
		
	
	<script src="${ctx}/admin/js/jquery.min.js"></script>
	
	<script>
   		 $("#admin_regist").click(function () {
        	$("#regist_form").submit();
    	});
	</script>
  </body>
</html>
