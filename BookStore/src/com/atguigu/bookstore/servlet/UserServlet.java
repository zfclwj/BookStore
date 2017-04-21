package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.model.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.serviceimpl.UserServiceImpl;
import com.atguigu.bookstore.webutils.ParameterToBean;
import com.google.code.kaptcha.Constants;

@WebServlet("*.User")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 10000000000000000L;
	private UserService userService = new UserServiceImpl();

	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		User user = ParameterToBean.getBean(User.class, request.getParameterMap());

		String imgToken = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (imgToken == null || !code.equals(imgToken)) {
			request.setAttribute("message", "验证码错误!");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			return;
		} else {
			if (userService.getNameCount(user.getUsername()) == 1) {
				request.setAttribute("message", "用户名已存在!");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
				return;
			}
			userService.regist(user);
			request.getSession().setAttribute("name", user.getUsername());
			response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		boolean flag = userService.login(name, password);
		if (flag) {
			request.getSession().setAttribute("name", name);
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		} else {
			request.setAttribute("message", "用户名或密码错误!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("name");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void checkUserExties(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		long count = userService.getNameCount(name);
		String result = "用户名已注册!";
		if (count == 0) {
			result = "用户名可用^_^";
		}
		response.getWriter().write(result);
	}
}
