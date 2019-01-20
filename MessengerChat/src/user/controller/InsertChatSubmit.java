package user.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.ChatService;


//Board/InsertSubmitServlet 
@WebServlet("/InsertChatSubmit")
public class InsertChatSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertChatSubmit() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ChatService service = ChatService.getInstance();

		String fromID = URLDecoder.decode(request.getParameter("fromID"), "utf-8");
		String toID = URLDecoder.decode(request.getParameter("toID"), "utf-8");
		String chatContent = URLDecoder.decode(request.getParameter("chatContent"), "utf-8");
		System.out.println(fromID);
		System.out.println(toID);
		System.out.println(chatContent);

		int re = -1;
		try {
			re = service.submit(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (re == -1) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

}
