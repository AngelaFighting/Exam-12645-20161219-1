<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link href="css/login.css" rel="stylesheet"/>
</head>
<body>
	<div id="login-container">		
		<div id="login-header">
			<h3>登录</h3>
		</div>
		<div id="login-content">
			<form class="form-horizontal" 
			action="<%=request.getContextPath()%>/LoginServlet" 
			method="get">
				<div class="form-group">				
					<label class="control-label col-xs-2">用户名</label>
				    <div class="col-xs-10">
				    	<input type="text" name="name" class="form-control" 
				    	placeholder="请输入用户名" required/>
				    </div>
			    </div>
			    <%if(request.getAttribute("login_msg")!=null){ %>
			    <div class="alert alert-danger">
			    <%=request.getAttribute("login_msg") %>
			    </div>
				<%} %>
				<div class="form-group">
					<div class="col-xs-6 text-center">
						<button type="submit" class="btn btn-info">登录</button>
					</div>
					<div class="col-xs-6 text-center">
						<a href="index.jsp">
						<input type="button" class="btn btn-default" value="返回">
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>			
</body>
</html>