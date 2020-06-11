package jp.co.axiz.web.servlet.update;

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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		String loginIdUp = request.getParameter("loginId");

		System.out.println(loginIdUp);
		 UserService userService = new UserService();
	        List<UserInfo> userUp = userService.findById(loginIdUp);
	        System.out.println(userUp);

	        String msg = "";
	        String msg1 = "";

	        if(loginIdUp.isEmpty()) {
	        	msg = "IDは必須です";

	        	request.setAttribute("msg", msg);
	        	 request.getRequestDispatcher("update.jsp").forward(request, response);
	        }
	        	 else {
	        if(!userUp.isEmpty()) {
	        	session.setAttribute("loginIdUp", loginIdUp);
	        	session.setAttribute("userUp", userUp);
	        	 request.getRequestDispatcher("updateInput.jsp").forward(request, response);




	        }else {
	        	msg1 = "入力されたデータは存在しません";
	        	request.setAttribute("msg1", msg1);
	        	 request.getRequestDispatcher("update.jsp").forward(request, response);
	        }

	        	 }

	}

}
