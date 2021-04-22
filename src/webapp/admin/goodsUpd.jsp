<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/admin/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/admin/css/amazeui.min.css" />
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">更新商品</strong><small></small></div>
    </div>
    <hr>

	  <div class="edit_content">
		<form action="${ctx}/GoodsServlet?action=updGoods" method="post" id="upd_form" style="background: none; width: 700px;">
			<div class="item1">
			
			<input type="text"  name="id" value="${goods.id}" style="display: none">
			
            <div>
                <span>商品名称：</span>
                <input type="text" class="am-form-field" name="name" value="${goods.name}">&nbsp;&nbsp;
            </div>
            <div>
                <span>商品价格：</span>
                <input type="text" class="am-form-field" name="price" value="${goods.price}">
            </div>

        </div>

        <div class="item1">
        	<div>
        		<span>所属分类：</span>
                 <select id="category_select" name="cid">
                 	<c:forEach items="${allCategory}" var="category">
                 	 <option value="${category.cid}">${category.cname}</option>
                 	</c:forEach>
                 </select>
                 &nbsp;
        	</div>
        	
        	<div>
        		<span>是否热门：</span>
                 <select id="isHot_select" name="is_hot">
                    <option value="0">否</option>
                     <option value="1">是</option>
                 </select>
        	</div>        
                 
        </div>

        <div class="item1">
                <span>商品图片：</span>
                <input type="file" name="image" value="${goods.image}"/>
        </div>
        
        <div class="item1 item_desc">
            <span>商品描述：</span>
         <textarea  id="desc" name="gdesc" rows="4" cols="50" >${goods.gdesc}</textarea>
        </div>
        
        <button class="am-btn am-btn-default" type="button" id="update">更新</button>
         &nbsp;&nbsp;&nbsp;&nbsp;
         <button class="am-btn am-btn-default" type="button" id="reset">重置</button>
		</form>
   </div>
</div>

<script src="${ctx}/admin/js/jquery.min.js"></script>

<script>

	//编辑分类下拉列表选中
	//让value值等于商品cid的option成为选中状态
	$("#category_select option[value=${goods.cid}]").prop("selected",true);
	
	//让value值等于商品is_hot的option成为选中状态
	$("#isHot_select option[value=${goods.is_hot}]").prop("selected",true);
	
    $("#update").click(function () {
       $("#upd_form").submit();
    });
    
   $("#reset").click(function () {
       $(window).attr('location','${ctx}/admin/goodsUpd.jsp');
    });
    
</script>
</body>
</html>