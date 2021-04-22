<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
</head>
<style>
    .admin_top{
        height: 70px;
        background: #1cc09f;
        text-align: right;
        padding-right: 70px;
        color: white;
        position: relative;
    }

    .admin_top span{
        position: absolute;
        right: 25px;
        top: 30px;

    }
    .admin_top img{
        margin-top: 20px;
        margin-right: 25px;
    }
    .admin_top .top_left{
        width: 250px;
        height: 70px;
        background:#283643;
        float: left;
        color: white;
        font-weight: bold;
        text-align: center;
        padding-top: 20px;
        font-size: 20px;

    }
    .admin_top .top_left:hover{
        background: #000;
    }

</style>
<body style="background:blue">

<div class="admin_top">
    <div class="top_left">
        码蚁商城后台管理系统
    </div>
    
    
    
    
    <div class="h_top_right">
    	<c:if test="${!empty admin}">
        	<img src="images/user_icon.png" alt="">
      		<span>${admin.username}，欢迎您</span>
     	</c:if>
    </div>
</div>

</body>
</html>