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
import jp.co.axiz.web.util.ParamUtil;

/**
 * Servlet implementation class UpdateInputServlet
 */
@WebServlet("/updateInput")
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInputServlet() {
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

		System.out.println("dddd");

		@SuppressWarnings("unchecked")
		List<UserInfo> userUp = (List<UserInfo>)session.getAttribute("userUp");

		System.out.println(userUp);

		String loginIdUp = (String)session.getAttribute("loginIdUp");
		/*System.out.println(userUp);
		request.setAttribute("userUp",userUp);*/

		String userNameup = "";
		String telUp = "";
		String passUp = "";
		Integer roleIdUp = null;
		Integer userId = null;

		for(UserInfo u : userUp) {

			userNameup = u.getUser_name();
			telUp = u.getTelephone();
			passUp = u.getPassword();
			roleIdUp = u.getRole_id();
			userId = u.getUser_id();
        }


		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String roleId = request.getParameter("roleId");
		String pass = request.getParameter("pass");

		Integer RoleId = Integer.parseInt(roleId);

		String roleName ="";


		String msg1 = "";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";
		String msg5 = "";
		String msg6 = "";

		String passRe= "";


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



		UserService userService = new UserService();
	    List<UserInfo> userUp2 = userService.findIdId(userId, loginId);

        if((userNameup.equals(userName)) && (loginIdUp.equals(loginId))
        			&& (telUp.equals(tel)) && (passUp.equals(pass)) && (roleIdUp.equals(RoleId))){

        	System.out.println("kjkjk");
        	msg5 = "1項目以上変更してください";
        	request.setAttribute("msg5", msg5);
        	request.getRequestDispatcher("updateInput.jsp").forward(request, response);


        } else {
        	if (!(userUp2.isEmpty())) {
        		request.setAttribute("msg6", "IDが重複しています");
        		request.getRequestDispatcher("updateInput.jsp").forward(request, response);


        	} else {


        	if(RoleId == 1) {
        		roleName= "管理者";
        	} else {
        		roleName= "一般";
        	}
        	//session.setAttribute("roleNameUp", roleName);

        	//session.setAttribute("user", user);
        	if(pass.equals(passUp)) {
        		passRe = pass;
        		} else {
        			passRe = "";
        		}session.setAttribute("passRe", passRe);


        	session.setAttribute("nameUp",userName);
        	session.setAttribute("loginIdUp", loginId);
        	session.setAttribute("telUp",tel);
        	session.setAttribute("roleNameUp", roleName);
        	session.setAttribute("passUp",pass);
        	session.setAttribute("RoleIdUp", RoleId);
        	session.setAttribute("userId", userId);

        	request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);
        }


        }

	}

}
