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
			System.out.println("빈칸");
			response.getWriter().write("");
		}else if (listType.equals("ten")) {
			System.out.println("ten");
			response.getWriter().write(getTen(fromID, toID));
		}else {
			try {
				System.out.println("getID");
				response.getWriter().write(getID(fromID, toID, listType));
			} catch (Exception e) {
				System.out.println("오류");
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
		
		String timeType = "오전";
		
		result.append("{\"result\":[");
		for (int i = 0; i < chatlist.size(); i++) {
			result.append("[{\"value\": \"" + chatlist.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getChatContent().replaceAll(" ", "&nbsp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
			int chatTime = Integer.parseInt(chatlist.get(i).getChatTime().substring(11, 13));
			if (chatTime >= 12) {
				timeType = "오후";
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
		dao.unleadChatUpdate(fromID, toID);
		
		if(chatlist.size() == 0){
			return "";
		}
		String timeType = "오전";

		result.append("{\"result\":[");
		for (int i = 0; i < chatlist.size(); i++) {
			result.append("[{\"value\": \"" + chatlist.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getChatContent().replaceAll(" ", "&nbsp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
			int chatTime = Integer.parseInt(chatlist.get(i).getChatTime().substring(11, 13));
			if (chatTime >= 12) {
				timeType = "오후";
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
	
	public int unleadAllChatlist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String userID = request.getParameter("userID");
		
		int unleadListCount = 0;
		//unleadListCount = dao.unleadAllChatlist(userID);
		
		return unleadListCount;
		
	}
	
	public String getBox(String userID) throws Exception{
		StringBuffer result = new StringBuffer("");
		ArrayList<ChatDTO> chatlist = dao.getBox(userID);

		if(chatlist.size() == 0){
			return "";
		}
	
		for(int i =0 ; i< chatlist.size() ; i++){
			ChatDTO x = chatlist.get(i);
			for(int j=0; j<chatlist.size() ; i++){
				ChatDTO y = chatlist.get(j);
				if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())){
					if(x.getChatNo() < y.getChatNo()){
						chatlist.remove(x);
						i--;
						break;
					}else{
						chatlist.remove(y);
						j--;
					}
				}
			}
		}
		
		String timeType = "오전";
		
		result.append("{\"result\":[");
		for (int i = 0; i < chatlist.size(); i++) {
			result.append("[{\"value\": \"" + chatlist.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatlist.get(i).getChatContent().replaceAll(" ", "&nbsp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") + "\"},");
			int chatTime = Integer.parseInt(chatlist.get(i).getChatTime().substring(11, 13));
			if (chatTime >= 12) {
				timeType = "오후";
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
}
