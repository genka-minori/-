package jp.co.axiz.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.role;
import jp.co.axiz.web.service.UserService;
import jp.co.axiz.web.util.ParamUtil;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

		//Integer id = ParamUtil.checkAndParseInt(request.getParameter("loginId"));
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");


		String msg1 = "";
		String msg2 = "";
		Integer roleId = 0;

		if (ParamUtil.isNullOrEmpty(id) && ParamUtil.isNullOrEmpty(pass)) {
            // メッセージ設定
			msg2= "PASSは必須です";
			msg1 ="IDは必須です";

			request.setAttribute("msg1", msg1);
			request.setAttribute("msg2", msg2);

            // 次画面指定
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
        }else if (pass != null && (ParamUtil.isNullOrEmpty(id)))
		 {
			msg1 ="IDは必須です";
			request.setAttribute("msg1", msg1);
			request.getRequestDispatcher("login.jsp").forward(request, response);


		} else if (id != null && (ParamUtil.isNullOrEmpty(pass))) {

			msg2= "PASSは必須です";

			request.setAttribute("msg2", msg2);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}


		 // ログインチェック
        UserService userService = new UserService();
        UserInfo user = userService.authentication(id, pass);


        String user_name = "";

        List<UserInfo> ul = userService.findById(id);
        for(UserInfo u : ul) {

        	user_name = u.getUser_name();
        	roleId = u.getRole_id();
        }
        session.setAttribute("user_name",user_name);
        session.setAttribute("roleId", roleId);



        if (user != null) {

        	List<role> list = userService.findAll();
        	System.out.println(list.get(0).getRole_name());
        	System.out.println(list.get(1).getRole_name());
        	session.setAttribute("list",list);
        	session.setAttribute("loginIdOK", id);

            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            // メッセージ設定
            request.setAttribute("msg3", "IDまたはPASSが間違っています");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

	}

}
