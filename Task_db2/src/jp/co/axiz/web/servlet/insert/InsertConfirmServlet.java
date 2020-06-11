package jp.co.axiz.web.servlet.insert;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserService;

/**
 * Servlet implementation class InsertConfirmServlet
 */
@WebServlet("/insertConfirm")
public class InsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String pass = (String)session.getAttribute("pass");
		String rePass = request.getParameter("rePass");

		String loginId = (String)session.getAttribute("loginId");
		String userName = (String)session.getAttribute("name");
		String tel = (String)session.getAttribute("tel");
		Integer RoleId = (Integer)session.getAttribute("RoleId");



		String msg6 ="";

		if(!(pass.equals(rePass)) ) {
			msg6 = "前画面で入力したパスワードと一致しません";

			System.out.println(msg6);

			request.setAttribute("msg6", msg6);
			//request.setAttribute(msg6, "前画面で入力したパスワードと一致しません");
			request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);
		} else {

			UserService userService = new UserService();
			List<UserInfo> addlist = userService.addA(loginId, userName, tel, pass, RoleId);
			 session.setAttribute("addlist", addlist);
			 request.getRequestDispatcher("insertResult.jsp").forward(request, response);
		}







	}

}
