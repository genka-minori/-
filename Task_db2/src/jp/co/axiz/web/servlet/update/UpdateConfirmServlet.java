package jp.co.axiz.web.servlet.update;

import java.io.IOException;
import java.sql.Timestamp;
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
 * Servlet implementation class UpdateConfirmServlet
 */
@WebServlet("/updateConfirm")
public class UpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateConfirmServlet() {
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

		String rePass = request.getParameter("rePass");
		String passUp = (String) session.getAttribute("passUp");
		String nameUp = (String) session.getAttribute("nameUp");
		String loginIdUp = (String) session.getAttribute("loginIdUp");
		String telUp = (String) session.getAttribute("telUp");
		Integer RoleIdUp = (Integer) session.getAttribute("RoleIdUp");
		Integer userId = (Integer) session.getAttribute("userId");



		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timeStamp);
		String msg = "";
		if (!(rePass.equals(passUp))) {

			msg ="前画面で入力したパスワードと一致しません";
			request.setAttribute("msg",msg );
			request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);

		}else {

			UserService userService = new UserService();
			List<UserInfo> userUp2 = userService.update(loginIdUp, nameUp, telUp, rePass, RoleIdUp, userId);

			List<UserInfo> updateDt = userService.updateDt(timeStamp, userId);
			session.setAttribute("userUp2", userUp2);
			session.setAttribute("updateDt", updateDt);
			request.getRequestDispatcher("updateResult.jsp").forward(request, response);
		}

	}

}
