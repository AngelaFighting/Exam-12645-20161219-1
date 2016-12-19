<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.MySQLUtil,model.Film,java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<% 
String film_idStr=request.getParameter("film_id");
MySQLUtil sql=new MySQLUtil();
Film film=null;
if(film_idStr!=null){
	int film_id=Integer.parseInt(film_idStr);
	film=sql.get(film_id);
}
%>
		<%if(film==null){ %>
		新增电影
		<%}else{ %>
		修改电影
		<%} %>
</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">	
<link href="css/public.css" rel="stylesheet"/>
</head>
<body>
	<div class="container text-center">
		<form method="post" action="<%=request.getContextPath()%>
		<%if(film==null){%>
		/InsertServlet
		<%}else{%>
		/UpdateServlet
		<%}%>
		">
		<%if(film!=null){ %><input name="id" type="hidden" value="<%=film.getId()%>"/><%} %>
			<div class="form-group">
		    	<label class="col-xs-2 control-label">标题</label>
		    	<div class="col-xs-10">
		      	<input type="text" class="form-control" name="title" 
		      		placeholder="请输入标题" value="<%if(film!=null){ 
		      			out.print(film.getTitle()); }%>" required>
		    	</div>
		  	</div>
		  	<br /><br />
		  	<div class="form-group">
		    	<label class="col-xs-2 control-label">描述</label>
		    	<div class="col-xs-10">
		    		<textarea rows="5" class="form-control" name="description">	
		      		<%if(film!=null){ 
		      			out.print(film.getDescription()); }%>	      			
		      		</textarea>
		    	</div>
		  	</div>
		  	<br /><br />
		  	<div class="form-group">
		    	<label class="col-xs-2 control-label">语言</label>
		    	<div class="col-xs-10">	
		    	<%
					Map<Integer,String> languages=sql.getLanguage();
				%>
		    		<select name="language_id" class="form-control">
		    		<% 
					for(Map.Entry<Integer,String> me: languages.entrySet()){
					%>
		    			<option value="<%=me.getKey() %>" 
		    			<%if(film!=null&&me.getKey()==film.getLanguage_id()){out.print("selected");} %>
		    			><%=me.getValue() %></option>
		    		<%} %>
		    		</select>
		      	</div>
		  	</div>
		  	<br /><br />
		  	<div class="form-group">
		    	<div class="col-xs-6">
			      	<button type="submit" class="btn btn-success">
				      	<%if(film==null){ %>
						新增
						<%}else{ %>
						修改
						<%} %>
					</button>
			    </div>
			    <div class="col-xs-6">
			      	<a href="<%=request.getContextPath()%>/list.jsp">
			      		<input type="button" class="btn btn-default" value="返回">
			      	</a>
			    </div>
		    </div>
		</form>
	</div>
</body>
</html>