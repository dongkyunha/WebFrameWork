package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.ChatService;


@WebServlet("/ChatBoxList")
public class ChatBoxList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		ChatService service = new ChatService();

		String userID = request.getParameter("userID");
		if(userID == null && userID.equals("")){
			response.getWriter().write("");
		}else{
			try {
				String re = service.getBox(userID);
				response.getWriter().write(re +"");
			} catch (Exception e) {
				System.out.println("에러");
				e.printStackTrace();
			}
		}
	}

}
