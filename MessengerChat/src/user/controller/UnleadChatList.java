package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.ChatService;

@WebServlet("/unleadChatList")
public class UnleadChatList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UnleadChatList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		ChatService service = new ChatService();

		String userID = request.getParameter("userID");
		if(userID == null && userID.equals("")){
			response.getWriter().write("0");
		}else{
			try {
				int re = service.unleadAllChatlist(request, response);
				response.getWriter().write(re +"");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("오류발생");
				e.printStackTrace();
			}
		}
	}

}
