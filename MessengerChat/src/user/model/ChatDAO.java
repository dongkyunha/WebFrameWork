package user.model;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import user.mapper.ChatMapper;

public class ChatDAO {
	private static ChatDAO dao = new ChatDAO();
	
	public static ChatDAO getinstance(){
		return dao;
	}
	
	public SqlSessionFactory getSqlSessionFactiory(){
		String resource = "mybatis-config.xml";
		
		InputStream in = null;
		
		try {
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(in);
	}
	
	
	public int submit(ChatDTO chat){
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		int chatlist = -1;
		
		try {
			chatlist = sqlsession.getMapper(ChatMapper.class).submit(chat);
			if(chatlist > 0){
				sqlsession.commit();
			}else{
				sqlsession.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return chatlist;
	}
	
	public ArrayList<ChatDTO> getChatlist(String chatTime){
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		ArrayList<ChatDTO> chatlist = null;
		
		try {
			chatlist = sqlsession.getMapper(ChatMapper.class).getChatlist(chatTime);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return chatlist;
	}
	
	public ArrayList<ChatDTO> getChatlistByRecent(String fromID, String toID, int chatNo){
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		ArrayList<ChatDTO> chatlist = null;
		
		try {
			chatlist = sqlsession.getMapper(ChatMapper.class).getChatlistByRecent(fromID, toID, chatNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return chatlist;
	}
	
	public ArrayList<ChatDTO> getChatlistUpdate(int chatNo){
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		ArrayList<ChatDTO> chatNolist = null;
		System.out.println(chatNo);
		
		
		try {
			chatNolist = sqlsession.getMapper(ChatMapper.class).getChatlistUpdate(chatNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return chatNolist;
	}	

	public ArrayList<ChatDTO> getID(String fromID, String toID, String listType) {
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		ArrayList<ChatDTO> chatNolist = null;
		
		try {
			chatNolist = sqlsession.getMapper(ChatMapper.class).getChatListbyID(fromID, toID, listType);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return chatNolist;
	}	

	public void unleadChatUpdate(String fromID, String toID) {
		// TODO Auto-generated method stub
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		
		int chatlist = -1;
		
		try {
			chatlist = sqlsession.getMapper(ChatMapper.class).unleadUpdate(fromID, toID);
			if(chatlist > 0){
				sqlsession.commit();
			}else{
				sqlsession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		
	}
		
	public int unleadAllChatlist(String userID){
		SqlSession sqlsession = getSqlSessionFactiory().openSession();

		int unleadChatCount = 0;
		try {
			System.out.println(unleadChatCount);
			unleadChatCount = sqlsession.getMapper(ChatMapper.class).unleadAllChatlist(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return unleadChatCount;
	}

	public ArrayList<ChatDTO> getMessengerBox(String userID) {
		SqlSession sqlsession = getSqlSessionFactiory().openSession();

		ArrayList<ChatDTO> getchatlist = null;
		try {
			System.out.println(getchatlist);
			getchatlist = sqlsession.getMapper(ChatMapper.class).getChatlist(userID);
			System.out.println(getchatlist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return getchatlist;
	}
}
