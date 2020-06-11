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
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class insertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
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

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String roleId = request.getParameter("roleId");
		String tel = request.getParameter("tel");
		String pass = request.getParameter("pass");

		Integer RoleId = Integer.parseInt(roleId);

		String roleName ="";


		String msg1 = "";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";
		String msg5 = "";



   	 //

		if (ParamUtil.isNullOrEmpty(loginId)) {

			msg1 ="IDは必須です";
			request.setAttribute("msg1", msg1);

        }

		if(ParamUtil.isNullOrEmpty(userName)){
			msg2= "名前は必須です";
			request.setAttribute("msg2", msg2);
		}

		if(ParamUtil.isNullOrEmpty(tel)) {
			msg3= "TELは必須です";
			request.setAttribute("msg3", msg3);
		}


		if(ParamUtil.isNullOrEmpty(pass)) {
			msg4= "PASSは必須です";
			request.setAttribute("msg4", msg4);
		}





			/*List<role> list = userService.findAll();
			String roleName = session.getAttribute(list.role_name);
			*/

		//IDチェック
        UserService userService = new UserService();
        List<UserInfo> user = userService.findById(loginId);
        System.out.println(user);


        if (!(user.isEmpty())) {
        	request.setAttribute("msg5", "IDが重複しています");
            request.getRequestDispatcher("insert.jsp").forward(request, response);


        } else {


        	if(!(ParamUtil.isNullOrEmpty(loginId)) && !(ParamUtil.isNullOrEmpty(userName))
        			&& !(ParamUtil.isNullOrEmpty(tel)) && !(ParamUtil.isNullOrEmpty(pass))){

				/*
				*/
        		 if(RoleId == 1) {
        			 roleName= "管理者";
        		 } else {
        			 roleName= "一般";
        		 }
        		 session.setAttribute("roleName", roleName);

				session.setAttribute("user", user);
				session.setAttribute("name",userName);
				session.setAttribute("loginId", loginId);
				session.setAttribute("tel",tel);
				session.setAttribute("roleName", roleName);
				session.setAttribute("pass",pass);
				session.setAttribute("RoleId", RoleId);



        	System.out.println("kjkjk");
        	request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);


        } else {
        	 request.getRequestDispatcher("insert.jsp").forward(request, response);
        }

		/*
		        	if(!(ParamUtil.isNullOrEmpty(loginId)) && !(ParamUtil.isNullOrEmpty(userName))
		        			&& !(ParamUtil.isNullOrEmpty(tel)) && !(ParamUtil.isNullOrEmpty(pass))){

		        	session.setAttribute("user", user);
		        	session.setAttribute("name",userName);
		        	session.setAttribute("loginId", loginId);
		        	session.setAttribute("tel",tel);
		        	session.setAttribute("roleName", roleName);
		        	session.setAttribute("pass",pass);

		        	request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);

		        	System.out.println("listlistlist");
		        	}
		*/

        	}

	}

	}





