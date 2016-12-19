package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.MySQLUtil;
import model.Film;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Film film=new Film();
		film.setId(Integer.parseInt(request.getParameter("id")));
		film.setTitle(request.getParameter("title"));
		film.setDescription(request.getParameter("description"));
		film.setLanguage_id(Integer.parseInt(request.getParameter("language_id")));
		MySQLUtil sql=new MySQLUtil();
		boolean result=sql.update(film);
		if(result)
			request.setAttribute("success", "更新成功");
		else
			request.setAttribute("fail", "更新失败");
		RequestDispatcher rd=request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
