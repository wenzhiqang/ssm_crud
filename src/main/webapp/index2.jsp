<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<<jsp:forward page="/emps.do"></jsp:forward>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  
  <body>
   <form action="${pageContext.request.contextPath}/emps.do" method="get">
    	姓名：<input type="text" name="cmpName"><br>
    	性别：<input type="text" name="gttder"><br>
    	邮箱：<input type="text" name="email"><br>
    	部门编号：<input type="text" name="dId"><br>
    	<input type="submit" class="btn btn-info"  value="提交"><br>
    </form>
    <!-- <button class="btn btn-info">一般般</button> -->
    <script src="static/js/jquery-3.3.1.js"></script>
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  </body>
</html>
