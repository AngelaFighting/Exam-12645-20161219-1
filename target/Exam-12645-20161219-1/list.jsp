<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.MySQLUtil,model.Film,java.util.List,java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电影列表</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">	
<link href="css/public.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<a href="<%=request.getContextPath()%>/index.jsp">
		<button class="btn btn-default pull-right">返回</button>
		</a>
		<a href="<%=request.getContextPath()%>/film.jsp">
		<button class="btn btn-success pull-right">新增</button>
		</a>		
		<%if(request.getAttribute("fail")!=null){ %>
	    <div class="alert alert-danger">
	    <%=request.getAttribute("fail") %>
	    </div>
	    <%} %>
	    <%if(request.getAttribute("success")!=null){ %>
	    <div class="alert alert-success">
	    <%=request.getAttribute("success") %>
	    </div>
	    <%} %>
		<h3>电影列表</h3>				
		<table class="table table-bordered">			
			<thead>					
				<tr>
					<th>电影ID</th>
					<th>标题</th>
					<th>描述</th>
					<th>语言</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<%
			MySQLUtil sql=new MySQLUtil();
			List<Film> films=sql.list();
			Map<Integer,String> languages=sql.getLanguage();
			for(Film film: films){
			%>
				<tr>
					<td><%=film.getId() %></td>
					<td><%=film.getTitle() %></td>
					<td><%=film.getDescription() %></td>
					<td><%=languages.get(film.getLanguage_id()) %></td>
					<td>
						<a href="<%=request.getContextPath()%>
						/film.jsp?film_id=<%=film.getId()%>">
							<button class="btn btn-info">编辑</button>
						</a>
						<a href="<%=request.getContextPath()%>
						/DeleteServlet?film_id=<%=film.getId()%>">
							<button class="btn btn-danger">删除</button>
						</a>
					</td>
				</tr>
				<%} %>
			</tbody>				
		</table>	
	</div>
</body>
</html>