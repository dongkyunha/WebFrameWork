package user.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.UserDAO;
import user.model.UserDTO;

public class UserService {
	private static UserDAO dao;
	private static UserService service = new UserService();

	public static UserService getInstance() {
		dao = UserDAO.getinstance();
		return service;
	}

	public int register(HttpServletRequest request, HttpServletResponse response) throws Exception { // �??��
		request.setCharacterEncoding("utf-8");

		int re = -1;
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");

		if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")
				|| userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
				|| userAge == null || userAge.equals("")) {
			request.getSession().setAttribute("messageType", "?��류메?���?");
			request.getSession().setAttribute("messageContent", "모든?��?��?�� ?��?��?��?��?��.");
			response.sendRedirect("join.jsp");
		} else if (dao.registerCheck(userID) == 0) {
			request.getSession().setAttribute("messageType", "?��류메?���?");
			request.getSession().setAttribute("messageContent", "?��?��?���? 존재?��?��?��.");
			response.sendRedirect("join.jsp");
		} else if (!userPassword.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "?��류메?���?");
			request.getSession().setAttribute("messageContent", "비�?번호�? ?��릅니?��.");
			response.sendRedirect("join.jsp");
		} else {
			UserDTO userDao = new UserDTO();
			userDao.setUserID(request.getParameter("userID"));
			userDao.setUserPassword(request.getParameter("userPassword1"));
			userDao.setUserName(request.getParameter("userName"));
			userDao.setUserAge(Integer.parseInt(request.getParameter("userAge")));

			re = dao.register(userDao);

			if (re > 0) {
				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("messageType", "?��공메?���?");
				request.getSession().setAttribute("messageContent", "?��?���??��?�� ?��공하???��?��?��.");
				response.sendRedirect("index.jsp");
			} else {
				request.getSession().setAttribute("messageType", "?��?��메세�?");
				request.getSession().setAttribute("messageContent", "?��?���??��?�� ?��?��?��???��?��?��.");
				response.sendRedirect("index.jsp");
			}
		}
		return re;
	}

	public int login(HttpServletRequest request, HttpServletResponse response) throws Exception { // 로그?��
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		int re = -1;

		if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
			request.getSession().setAttribute("messageType", "?��류메?���?");
			request.getSession().setAttribute("messageContent", "모든?��?��?�� ?��?��?��주세?��.");
			response.sendRedirect("login.jsp");
			return re;
		} else {
			re = dao.login(userID, userPassword);
			System.out.println(re);
			System.out.println(dao);
			if (re == -1) {
				request.getSession().setAttribute("messageType", "?��류메?���?");
				request.getSession().setAttribute("messageContent", "?��?��?���? 존재?���??��?��?��?��.");
				response.sendRedirect("login.jsp");
				return re;
			} else if (re == 0) {
				request.getSession().setAttribute("messageType", "?��류메?���?");
				request.getSession().setAttribute("messageContent", "비�?번호�? ??립니?��.");
				response.sendRedirect("login.jsp");
				return re;
			} else {
				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("messageType", "?��공메?���?");
				request.getSession().setAttribute("messageContent", "로그?�� ?��?��?��?��?��.");
				response.sendRedirect("index.jsp");
				return re;
			}
		}
	}

	public int registerCheck(HttpServletRequest request, HttpServletResponse response) throws Exception { // ?��?��?�� 중복?���?체크
		request.setCharacterEncoding("utf-8");

		String userID = request.getParameter("userID");
		System.out.println(userID);

		int re = -1;
		re = dao.registerCheck(userID);

		return re;
	}

	public ArrayList<UserDTO> listUser(HttpServletRequest request, HttpServletResponse response) throws Exception { // ?��??불러?���?
		request.setCharacterEncoding("utf-8");

		ArrayList<UserDTO> list = null;
		list = dao.listUserLoad();

		return list;
	}
}
