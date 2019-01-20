package user.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import user.model.ChatDTO;

public interface ChatMapper {
		ArrayList<ChatDTO> getChatlist(String chatTime);
		int submit(ChatDTO chat);
		ArrayList<ChatDTO> getChatlistUpdate(int chatNo);

		ArrayList<ChatDTO> getChatListbyID(@Param("fromID") String fromID,@Param("toID") String toID,@Param("chatNo") String listType);
		ArrayList<ChatDTO> getChatlistByRecent(@Param("fromID") String fromID,@Param("toID") String toID,@Param("chatNo") int chatNo);
		int readChat(ChatDTO chatDto);
		ArrayList<ChatDTO> getAllUnleadChat(String userID);
}
