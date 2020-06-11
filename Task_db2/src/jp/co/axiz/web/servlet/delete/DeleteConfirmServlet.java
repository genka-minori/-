package jp.co.axiz.web.servlet.delete;

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
 * Servlet implementation class DeleteConfirmServlet
 */
@WebServlet("/deleteConfirm")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirmServlet() {
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
		String userId = request.getParameter("userId");

		Integer user_Id = Integer.parseInt(userId);

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

		System.out.println(timeStamp);

		UserService userService = new UserService();
		List<UserInfo> deletelist = userService.delete(user_Id);
		List<UserInfo> updateDt = userService.updateDt(timeStamp, user_Id);
		 session.setAttribute("deletelist", deletelist);
		 session.setAttribute("updateDt", updateDt);
		 request.getRequestDispatcher("deleteResult.jsp").forward(request, response);



	}

}
