package user.model;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import user.mapper.UserMapper;

public class UserDAO {
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getinstance(){
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
	
	
	//메소드 기능들(DB와 연결)	
	public int login(String userID, String userPassword){	//로그인 sql연결
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		
		UserDTO user = null;
		try {
			user = sqlsession.getMapper(UserMapper.class).getOneList(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		
		if(user == null){
			return -1;	//해당사용자가 존재하지않음
		}else if(user.getUserPassword().equals(userPassword)){
			return 1;	//로그인에 성공
		}else{
			return 0;	//비밀번호가 틀림
		}

	}
	
	public int registerCheck(String userID){	//아이디중복여부 sql 연결
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		
		UserDTO user = null;
		
		try {
			user = sqlsession.getMapper(UserMapper.class).getOneList(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		System.out.println(user);
		
		if(user != null || userID.equals("")){
			return 0;	//이미존재하는 회원
		}else{
			return 1;	//가입가능한 회원
		}
	}
	
	public int register(UserDTO userDTO){	//회원가입 sql연결
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		
		int user = -1;
		try {
			user = sqlsession.getMapper(UserMapper.class).register(userDTO);
			if(user > 0){
				sqlsession.commit();
			}else{
				sqlsession.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return user;
	}

	public ArrayList<UserDTO> listUserLoad() {		//유저리스트 불러오기
		SqlSession sqlsession = getSqlSessionFactiory().openSession();
		
		ArrayList<UserDTO> list = null;
		try {
			list = sqlsession.getMapper(UserMapper.class).listUserLoad();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlsession.close();
		}
		return list;
	}
}
