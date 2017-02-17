package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ModifyResponseFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ModifyResponseFilter() {
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		CachedResponseWrapper wrapper = new CachedResponseWrapper(httpResponse);
		System.out.println("********************"+httpResponse);
		System.out.println("********************"+httpRequest.getServletPath());
		System.out.println("********************"+httpRequest.getRequestURI());
		System.out.println("********************"+httpRequest.getRequestURL());
		System.out.println("********************"+httpRequest.getContextPath());
		
		RequestWrapper requestWrapper = new RequestWrapper(httpRequest,"/filterWeb/RedectServlet","http://localhost:8080/filterWeb/RedectServlet","/RedectServlet","/RedectServlet");
		//request.getRequestDispatcher("/RedectServlet").forward(requestWrapper, wrapper);
		//chain.doFilter(requestWrapper, wrapper);
		System.out.println("********************");
		if(wrapper.getStatus()==HttpServletResponse.SC_OK) {
			// 对响应进行处理，这里是进行GZip压缩:
			//byte[] data = GZipUtil.gzip(wrapper.getResponseData());
			byte[] data = wrapper.getResponseData();
			String content = new String(data);
			content = "您好"+content+"这是我套的！";
			httpResponse.setContentType(wrapper.getContentType());
			httpResponse.setContentLength(content.getBytes().length);
			httpResponse.setHeader("Content-Encoding", "content");
			ServletOutputStream output = response.getOutputStream();
			output.write(content.getBytes());
			output.flush();
			}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
