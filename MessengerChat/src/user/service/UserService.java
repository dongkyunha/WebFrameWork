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

	public int register(HttpServletRequest request, HttpServletResponse response) throws Exception { // ê°??…
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
			request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
			request.getSession().setAttribute("messageContent", "ëª¨ë“ ?‚´?š©?„ ?…? ¥?•˜?„¸?š”.");
			response.sendRedirect("join.jsp");
		} else if (dao.registerCheck(userID) == 0) {
			request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
			request.getSession().setAttribute("messageContent", "?•„?´?””ê°? ì¡´ì¬?•©?‹ˆ?‹¤.");
			response.sendRedirect("join.jsp");
		} else if (!userPassword.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
			request.getSession().setAttribute("messageContent", "ë¹„ë?ë²ˆí˜¸ê°? ?‹¤ë¦…ë‹ˆ?‹¤.");
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
				request.getSession().setAttribute("messageType", "?„±ê³µë©”?„¸ì§?");
				request.getSession().setAttribute("messageContent", "?šŒ?›ê°??…?— ?„±ê³µí•˜???Šµ?‹ˆ?‹¤.");
				response.sendRedirect("index.jsp");
			} else {
				request.getSession().setAttribute("messageType", "?‹¤?Œ¨ë©”ì„¸ì§?");
				request.getSession().setAttribute("messageContent", "?šŒ?›ê°??…?— ?‹¤?Œ¨?•˜???Šµ?‹ˆ?‹¤.");
				response.sendRedirect("index.jsp");
			}
		}
		return re;
	}

	public int login(HttpServletRequest request, HttpServletResponse response) throws Exception { // ë¡œê·¸?¸
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		int re = -1;

		if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
			request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
			request.getSession().setAttribute("messageContent", "ëª¨ë“ ?‚´?š©?„ ?…? ¥?•´ì£¼ì„¸?š”.");
			response.sendRedirect("login.jsp");
			return re;
		} else {
			re = dao.login(userID, userPassword);
			System.out.println(re);
			System.out.println(dao);
			if (re == -1) {
				request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
				request.getSession().setAttribute("messageContent", "?•„?´?””ê°? ì¡´ì¬?•˜ì§??•Š?Šµ?‹ˆ?‹¤.");
				response.sendRedirect("login.jsp");
				return re;
			} else if (re == 0) {
				request.getSession().setAttribute("messageType", "?˜¤ë¥˜ë©”?„¸ì§?");
				request.getSession().setAttribute("messageContent", "ë¹„ë?ë²ˆí˜¸ê°? ??ë¦½ë‹ˆ?‹¤.");
				response.sendRedirect("login.jsp");
				return re;
			} else {
				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("messageType", "?„±ê³µë©”?„¸ì§?");
				request.getSession().setAttribute("messageContent", "ë¡œê·¸?¸ ?˜?—ˆ?Šµ?‹ˆ?‹¤.");
				response.sendRedirect("index.jsp");
				return re;
			}
		}
	}

	public int registerCheck(HttpServletRequest request, HttpServletResponse response) throws Exception { // ?•„?´?”” ì¤‘ë³µ?—¬ë¶?ì²´í¬
		request.setCharacterEncoding("utf-8");

		String userID = request.getParameter("userID");
		System.out.println(userID);

		int re = -1;
		re = dao.registerCheck(userID);

		return re;
	}

	public ArrayList<UserDTO> listUser(HttpServletRequest request, HttpServletResponse response) throws Exception { // ?œ ??ë¶ˆëŸ¬?˜¤ê¸?
		request.setCharacterEncoding("utf-8");

		ArrayList<UserDTO> list = null;
		list = dao.listUserLoad();

		return list;
	}
}
