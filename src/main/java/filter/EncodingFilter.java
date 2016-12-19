package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	private String encoding=null;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		if(!encoding.equals(request.getCharacterEncoding())){
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding=config.getInitParameter("encoding");
		if(encoding==null){
			throw new ServletException("EncodingFilter中的编码设置为空！！！");
		}		
	}

}
