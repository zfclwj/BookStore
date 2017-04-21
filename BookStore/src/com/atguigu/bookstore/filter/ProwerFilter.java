package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.model.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.serviceimpl.UserServiceImpl;

@WebFilter("/pages/*")
public class ProwerFilter implements Filter {
	private FilterConfig filterConfig = null;
	private UserService userService = new UserServiceImpl();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String thisPage = request.getServletPath();
		String unCheckedPage = filterConfig.getServletContext().getInitParameter("UnChecked");
		List<String> pages = Arrays.asList(unCheckedPage.split(","));
		if (!pages.contains(thisPage)) {
			String name = (String) request.getSession().getAttribute("name");
			if (name == null) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				User user = userService.getUser(name);
				if(user.getStatus()==0){
					request.getSession().setAttribute("userPrower", "common");
				}else{
					request.getSession().setAttribute("userPrower", "admin");
				}
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
