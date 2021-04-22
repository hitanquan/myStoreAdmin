<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 获取当前工程的名称，保存在当前页面域对象中 -->
<%
	String ctx = request.getContextPath();
	//System.out.println(ctx);
	pageContext.setAttribute("ctx",ctx);
 %>
