package user.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.ChatDAO;
import user.model.ChatDTO;

public class ChatService {
	private static ChatDAO dao;
	private static ChatService service = new ChatService();

	public static ChatService getInstance() {
		dao = ChatDAO.getinstance();
		return service;
	}

	public int submit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ChatDTO chat = new ChatDTO();

		chat.setFromID(request.getParameter("fromID"));
		chat.setToID(request.getParameter("toID"));
		chat.setChatContent(request.getParameter("chatContent"));

		int result = dao.submit(chat);

		return result;
	}
	
	public void Chatlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		
		if (fromID == null || fromID.equals("")||toID == null || toID.equals("")||listType == null || listType.equals("")) {
			System.out.println("ë¹ˆì¹¸");
			response.getWriter().write("");
		}else if (listType.equals("ten")) {
			System.out.println("ten");
			response.getWriter().write(getTen(fromID, toID));
		}else {
			try {
				System.out.println("getID");
				response.getWriter().write(getID(fromID, toID, listType));
			} catch (Exception e) {
				System.out.println("?˜¤ë¥?");
				response.getWriter().write("");
			}
		}
	}

	public String getTen(String fromID, String toID) throws Exception{
		StringBuffer result = new StringBuffer("");

		ArrayList<ChatDTO> chatlist = dao.getChatlistByRecent(fromID, toID, 40);
		
		if(chatlist.size() == 0){
			return "";
		}
		
		String timeType = "?˜¤? „";
		
		result.append("{\"result\":[");
		for (int i = 0; i < chatlist.size(); i++) {
			result.append("[{\"value\": \"" + chatlist.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getChatContent().replaceAll(" ", "&nbsp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
			int chatTime = Integer.parseInt(chatlist.get(i).getChatTime().substring(11, 13));
			if (chatTime >= 12) {
				timeType = "?˜¤?›„";
				chatTime -= 12;
			}
			result.append("{\"value\": \"" + chatlist.get(i).getChatTime().substring(0, 11) + " " + timeType + " "
					+ chatTime + ":" + chatlist.get(i).getChatTime().substring(14, 16) + "\"}]");
			if (i != chatlist.size() - 1) {
				result.append(",");
			}
		}
		result.append("], \"last\" : \"" + chatlist.get(chatlist.size() - 1).getChatNo() + "\"}");
		
		System.out.println(result);
		
		return result.toString();
	}
	
	public String getID(String fromID, String toID, String listType) throws Exception{
		StringBuffer result = new StringBuffer("");

	
		ArrayList<ChatDTO> chatlist = dao.getID(fromID, toID, listType);
		
		if(chatlist.size() == 0){
			return "";
		}
		String timeType = "?˜¤? „";

		result.append("{\"result\":[");
		for (int i = 0; i < chatlist.size(); i++) {
			result.append("[{\"value\": \"" + chatlist.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getChatContent().replaceAll(" ", "&nbsp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
			int chatTime = Integer.parseInt(chatlist.get(i).getChatTime().substring(11, 13));
			if (chatTime >= 12) {
				timeType = "?˜¤?›„";
				chatTime -= 12;
			}
			result.append("{\"value\": \"" + chatlist.get(i).getChatTime().substring(0, 11) + " " + timeType + " "
					+ chatTime + ":" + chatlist.get(i).getChatTime().substring(14, 16) + "\"}]");
			if (i != chatlist.size() - 1) {
				result.append(",");
			}
		}
		result.append("], \"last\" : \"" + chatlist.get(chatlist.size() - 1).getChatNo() + "\"}");
		
		System.out.println(result);
		
		return result.toString();
	}
	
	public int unleadChatList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		
		return 0;
	}
	
}
