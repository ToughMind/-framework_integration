package lq.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 公用类：用户设置当前web环境上下文，用于方便如JSP页面使用。
 * 
 * @author 刘泉
 * @date 2016年11月8日 下午7:27:43
 */
public class ContextPathFilter implements Filter {

	private final String CONTEXT_PATH = "ctx";

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String contextPath = ((HttpServletRequest) request).getContextPath();
		request.setAttribute(CONTEXT_PATH, contextPath);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
