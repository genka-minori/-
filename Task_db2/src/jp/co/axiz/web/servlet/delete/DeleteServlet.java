package jp.co.axiz.web.servlet.delete;

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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		String loginId = (String)session.getAttribute("loginIdOK");
		String loginIdDe = request.getParameter("loginId");
		UserService userService = new UserService();


        List<UserInfo> userDe = userService.findById(loginIdDe);
        System.out.println(userDe);

        String msg = "";
        String msg1 = "";
        String msg2 = "";
        Integer roleId= null;
        String roleName = "";

        if(loginIdDe.isEmpty()) {
        	msg = "IDは必須です";

        	request.setAttribute("msg", msg);
        	 request.getRequestDispatcher("delete.jsp").forward(request, response);
        }

        else if(loginIdDe.equals(loginId)) {
        	msg1 = "ログインユーザーは削除できません";
        	request.setAttribute("msg1",msg1);
        	request.getRequestDispatcher("delete.jsp").forward(request, response);
        }
        	 else {
        if(!userDe.isEmpty()) {


        		if(userDe.get(0).getRole_id() == 1) {
        			System.out.println("aaa");
       			 roleName= "管理者";
       		 } else {
       			 System.out.println("ggg");
       			 roleName= "一般";
        	}
        		session.setAttribute("roleNameDe", roleName);

        	session.setAttribute("loginIdDe", loginIdDe);
        	session.setAttribute("userDe", userDe);
        	 request.getRequestDispatcher("deleteConfirm.jsp").forward(request, response);




        }else {
        	msg2 = "入力されたデータは存在しません";
        	request.setAttribute("msg2", msg2);
        	 request.getRequestDispatcher("delete.jsp").forward(request, response);
        }

        	 }





	}

}
