package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {
	private String uri;
	private String url;
	private String contextPath;
	private String servletPath;
	
	public RequestWrapper(HttpServletRequest request,String uri,String url,String contextPath,String servletPath) {
		super(request);
		this.uri = uri;
		this.url = url;
		this.contextPath = contextPath;
		this.servletPath = servletPath;
	}

	@Override
	public String getRequestURI() {
		return this.uri;
	}

	@Override
	public StringBuffer getRequestURL() {
		return new StringBuffer(this.url);
	}

	@Override
	public String getContextPath() {
		return this.contextPath;
	}

	@Override
	public String getServletPath() {
		return this.servletPath;
	}
}
