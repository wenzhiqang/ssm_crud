<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>员工信息展示</title>
	<%//获取到的相对路径是以/开始不以/结束
		pageContext.setAttribute("App_Path", request.getContextPath());
		String a=request.getContextPath();
	 %>
	<!--web路径
	不以/开头的是相当路径，找资源的路径为基准，经常容易出问题
	以/开始的相对路径，找资源，以服务器路径为标准（http://localhost:8080）需要加上项目名称
	  -->
	<link href="${App_Path }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
	<body>
		<!-- 搭建显示页面 -->
		<div class="container">
			<!-- 标题 -->
			<div class="row">
				<div class="col-md-12">
					<h1>ssm_crud</h1>
				</div>
			</div>
			<!-- 按钮 -->
			<div class="row">
				 <div class="col-md-4 col-md-offset-8">
				 	<button class="btn btn-info">新增</button>
				 	<button class="btn btn-danger">删除</button>
				 </div>
			</div>
			<!-- 表格数据 -->
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${pageInfo.list}" var="emp">
						<tr>
							<th>${emp.cmpId}</th>
							<th>${emp.cmpName}</th>
							<th>${emp.gttder}</th>
							<th>${emp.email}</th>
							<th>${emp.dept.deptName}</th>
							<th>
								<button class="btn btn-info btn-sm">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑</button>
								<button class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								删除</button>
							</th>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- 显示分页信息 -->
			<div class="row">
				<!-- 分页文字信息 -->
				<div class="col-md-6">
					当前记录数${pageInfo.pageNum}, 总${pageInfo.pages }页, 总${pageInfo.total}条记录
					
				</div>
				<!-- 分页条信息 -->
				<div class="col-md-6">
					<nav aria-label="Page navigation">
					  <ul class="pagination">
					  	<li><a href="${App_Path}/emps.do?pn=${1}">首页</a></li>
					  	<c:if test="${pageInfo.hasPreviousPage}">
					  		<li>
						      <a href="${App_Path}/emps.do?pn=${pageInfo.pageNum-1}" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
					  	</c:if>
					  	
					    
					    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
						    <c:if test="${page_Num==pageInfo.pageNum}">
						    	<li class="active"><a href="#">${page_Num}</a></li>
						    </c:if>
					    	<c:if test="${page_Num!=pageInfo.pageNum}">
						    	<li><a href="${App_Path}/emps.do?pn=${page_Num}">${page_Num}</a></li>
						    </c:if>
					     </c:forEach>
					     <c:if test="${pageInfo.hasNextPage}">
						    <li>
						      <a href="${App_Path}/emps.do?pn=${pageInfo.pageNum+11}" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
					    </c:if>
					    <li><a href="${App_Path}/emps.do?pn=${pageInfo.pages}">末页</a></li>
					  </ul>
					</nav>
				</div>
			</div>
		</div> 
		<script src="${App_Path}/ssm_curd/static/js/jquery-3.3.1.js"></script>
	    <script src="${App_Path }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	</body>
</html>