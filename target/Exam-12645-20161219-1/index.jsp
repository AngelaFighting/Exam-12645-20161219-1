<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页</title>
	<link href="css/public.css" rel="stylesheet"/>
</head>
<body>	
	<div class="container text-center">
		<div class="row">
			<div class="col-xs-6">
			<%if(session.getAttribute("login_user")==null){ %>
				<a href="login.jsp">
					<button class="btn btn-info">登录</button>
				</a>
			<%}else{ %>
				<a href="<%=request.getContextPath()%>/LogoutServlet">
					<button class="btn btn-info">退出</button>
				</a>
			<%} %>
			</div>
			
			<div class="col-xs-6">
				<a href="list.jsp">
					<button class="btn btn-info">查看电影</button>
				</a>
			</div>
		</div>
	</div>		
</body>
</html>