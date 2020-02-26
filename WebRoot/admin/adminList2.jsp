<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员主页</title>
    <link rel="stylesheet" href="${ctx}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/admin/css/pageStyle.css">
</head>
<body style="background:white;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong></div>
    </div>
    <hr>

    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span>新增
                    </button>
                </div>
            </div>
        </div>

        <div class="am-u-sm-12 am-u-md-3"></div>

        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理员</li>
    </ul>

    <c:if test="${empty pageBean.adminList}">
        没有用户
    </c:if>

    <c:forEach items="${pageBean.adminList}" var="admin" varStatus="status">
        <ul class="list_goods_ul">
            <li>${status.index + 1}</li>
            <li style="font-size: 13px">${admin.username}</li>
            <li><a href="${ctx}/AdminServlet?action=updUI&id=${admin.id}">
                <img class="img_icon" src="${ctx}/admin/images/edit_icon.png"></a></li>
            <li><a href="${ctx}/AdminServlet?action=delAdmin&id=${admin.id}">
                <img class="img_icon" src="${ctx}/admin/images/delete_icon.png"></a></li>
        </ul>
    </c:forEach>


    <!--分页-->
    <div id="page" class="page_div">aaa</div>
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
            $(window).attr('location', '${ctx}/AdminServlet?action=getPageData&currentPage=' + num);
        }
    });

    $("#add").click(function () {
        $(window).attr('location', '${ctx}/AdminServlet?action=addUI');
    });
</script>
</body>
</html>