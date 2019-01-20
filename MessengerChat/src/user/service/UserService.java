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

	public int register(HttpServletRequest request, HttpServletResponse response) throws Exception { // κ°??
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
			request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
			request.getSession().setAttribute("messageContent", "λͺ¨λ ?΄?©? ?? ₯??Έ?.");
			response.sendRedirect("join.jsp");
		} else if (dao.registerCheck(userID) == 0) {
			request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
			request.getSession().setAttribute("messageContent", "??΄?κ°? μ‘΄μ¬?©??€.");
			response.sendRedirect("join.jsp");
		} else if (!userPassword.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
			request.getSession().setAttribute("messageContent", "λΉλ?λ²νΈκ°? ?€λ¦λ?€.");
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
				request.getSession().setAttribute("messageType", "?±κ³΅λ©?Έμ§?");
				request.getSession().setAttribute("messageContent", "??κ°??? ?±κ³΅ν???΅??€.");
				response.sendRedirect("index.jsp");
			} else {
				request.getSession().setAttribute("messageType", "?€?¨λ©μΈμ§?");
				request.getSession().setAttribute("messageContent", "??κ°??? ?€?¨????΅??€.");
				response.sendRedirect("index.jsp");
			}
		}
		return re;
	}

	public int login(HttpServletRequest request, HttpServletResponse response) throws Exception { // λ‘κ·Έ?Έ
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		int re = -1;

		if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
			request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
			request.getSession().setAttribute("messageContent", "λͺ¨λ ?΄?©? ?? ₯?΄μ£ΌμΈ?.");
			response.sendRedirect("login.jsp");
			return re;
		} else {
			re = dao.login(userID, userPassword);
			System.out.println(re);
			System.out.println(dao);
			if (re == -1) {
				request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
				request.getSession().setAttribute("messageContent", "??΄?κ°? μ‘΄μ¬?μ§???΅??€.");
				response.sendRedirect("login.jsp");
				return re;
			} else if (re == 0) {
				request.getSession().setAttribute("messageType", "?€λ₯λ©?Έμ§?");
				request.getSession().setAttribute("messageContent", "λΉλ?λ²νΈκ°? ??λ¦½λ?€.");
				response.sendRedirect("login.jsp");
				return re;
			} else {
				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("messageType", "?±κ³΅λ©?Έμ§?");
				request.getSession().setAttribute("messageContent", "λ‘κ·Έ?Έ ???΅??€.");
				response.sendRedirect("index.jsp");
				return re;
			}
		}
	}

	public int registerCheck(HttpServletRequest request, HttpServletResponse response) throws Exception { // ??΄? μ€λ³΅?¬λΆ?μ²΄ν¬
		request.setCharacterEncoding("utf-8");

		String userID = request.getParameter("userID");
		System.out.println(userID);

		int re = -1;
		re = dao.registerCheck(userID);

		return re;
	}

	public ArrayList<UserDTO> listUser(HttpServletRequest request, HttpServletResponse response) throws Exception { // ? ??λΆλ¬?€κΈ?
		request.setCharacterEncoding("utf-8");

		ArrayList<UserDTO> list = null;
		list = dao.listUserLoad();

		return list;
	}
}
