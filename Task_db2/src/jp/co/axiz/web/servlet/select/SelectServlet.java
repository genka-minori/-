package jp.co.axiz.web.servlet.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserService;
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class selectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("userName");
		String telephone = request.getParameter("tel");

		UserService userService = new UserService();

		if (ParamUtil.isNullOrEmpty(name) && ParamUtil.isNullOrEmpty(telephone)) {

			List<UserInfo> user = userService.selectAll();
			request.setAttribute("user", user);
			System.out.print(user);

			request.getRequestDispatcher("selectResult.jsp").forward(request, response);

		}

		else if (!(ParamUtil.isNullOrEmpty(name)) && !(ParamUtil.isNullOrEmpty(telephone))) {

			List<UserInfo> user = userService.listup(name, telephone);

			if (user == null || user.isEmpty()) {
				request.setAttribute("msg", "入力されたデータは見つかりませんでした");
				request.getRequestDispatcher("select.jsp").forward(request, response);
			} else {
				request.setAttribute("user", user);
				System.out.print(user);

				request.getRequestDispatcher("selectResult.jsp").forward(request, response);
			}

		} else if (!(ParamUtil.isNullOrEmpty(name)) && ParamUtil.isNullOrEmpty(telephone)) {
			List<UserInfo> user = userService.listupByName(name);

			if (user == null || user.isEmpty()) {
				request.setAttribute("msg", "入力されたデータは見つかりませんでした");
				request.getRequestDispatcher("select.jsp").forward(request, response);
			} else {
				request.setAttribute("user", user);
				System.out.print(user);

				request.getRequestDispatcher("selectResult.jsp").forward(request, response);
			}
		} else if (ParamUtil.isNullOrEmpty(name) && !(ParamUtil.isNullOrEmpty(telephone))) {
			List<UserInfo> user = userService.listupByTelephone(telephone);

			if (user == null || user.isEmpty()) {
				request.setAttribute("msg", "入力されたデータは見つかりませんでした");
				request.getRequestDispatcher("select.jsp").forward(request, response);
			} else {
				request.setAttribute("user", user);
				System.out.print(user);

				request.getRequestDispatcher("selectResult.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}
}
