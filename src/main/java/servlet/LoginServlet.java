package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.MySQLUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		RequestDispatcher rd=null;
		if(name==null||name.trim()==""){
			request.setAttribute("login_msg", "用户名不能为空");
			rd=request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}else{
			MySQLUtil sql=new MySQLUtil();
			if(!sql.login(name)){
				request.setAttribute("login_msg", "用户名输入错误");
				rd=request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}else{
				request.getSession().setAttribute("login_user", name);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
