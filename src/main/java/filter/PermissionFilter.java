package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PermissionFilter
 */
public class PermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String servletPath=req.getServletPath();
		HttpSession session=req.getSession();
		String login_user=(String)session.getAttribute("login_user");
		if(servletPath!=null&&(servletPath.equals("/login.jsp")
				||servletPath.equals("/index.jsp")
				||servletPath.equals("/LoginServlet"))){
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else{
			if(login_user!=null){
				chain.doFilter(request, response);
			}else{
				req.setAttribute("login_msg", "您尚未登陆！");
				RequestDispatcher rd=req.getRequestDispatcher("/login.jsp");
				rd.forward(req, resp);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
