<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>分类管理</title>
    <link rel="stylesheet" href="${ctx}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/admin/css/pageStyle.css">
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span>添加分类
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>

    <c:if test="${empty pageBean.categoryList}">
        没有分类信息
    </c:if>

    <c:forEach items="${pageBean.categoryList}" var="category" varStatus="status">
        <ul class="list_goods_ul">
            <li>${status.index + 1}</li>
            <li style="font-size: 13px">${category.cname}</li>
            <li><a href="${ctx}/CategoryServlet?action=updCategoryUI&id=${category.cid}">
                <img class="img_icon" src="${ctx}/admin/images/edit_icon.png" alt="修改分类"></a></li>
            <li><a href="${ctx}/CategoryServlet?action=delCategory&id=${category.cid}">
                <img class="img_icon" src="${ctx}/admin/images/delete_icon.png" alt="删除分类"></a></li>
        </ul>
    </c:forEach>

    <!--分页-->
    <div id="page" class="page_div">aaa</div>

    <%--静态数据--%>
    <%--<ul class="list_goods_ul">
        <li>01</li>
        <li>分类名称</li>
        <li><a href="#"><img class="img_icon" src="images/edit_icon.png" alt=""></a></li>
        <li><a href="#"><img class="img_icon" src="images/delete_icon.png" alt=""></a></li>
    </ul>--%>

</div>

<script src="${ctx}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/admin/js/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:${pageBean.currentPage},
        totalPage:${pageBean.totalPage},
        totalSize:${pageBean.totalCount},
        callback: function (num) {
            //alert(num);
            $(window).attr('location', '${ctx}/CategoryServlet?action=getPageData&currentPage=' + num);
        }
    });
</script>

<div id="modal_view"></div>

<div id="modal_content">
    <div id="close"><img src="${ctx}/admin/images/delete_icon.png" alt="取消"></div>
    <div class="edit_content">

        <div class="item1">
            <div>
                <span>添加分类：</span>
            </div>
        </div>
        <form action="${ctx}/CategoryServlet?action=addCategory" method="post" id="add_form">
            <div class="item1">
                <div>
                    <span>分类名称：</span>
                    <input type="text" class="am-form-field" name="cateName">&nbsp;&nbsp;
                </div>
            </div>
            <div class="item1">
                <button class="am-btn am-btn-default" type="submit">添加</button>
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
    });

    <%--$("#add_form").click(function () {--%>
    <%--//alert("hh")--%>
    <%--//$("#add_form2").submit();--%>
    <%--//$(window).attr('location','${ctx}/CategoryServlet?action=addCategory');--%>
    <%--});--%>
</script>
</body>
</html>