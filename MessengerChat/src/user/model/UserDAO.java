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
	
	
	//�޼ҵ� ��ɵ�(DB�� ����)	
	public int login(String userID, String userPassword){	//�α��� sql����
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
			return -1;	//�ش����ڰ� ������������
		}else if(user.getUserPassword().equals(userPassword)){
			return 1;	//�α��ο� ����
		}else{
			return 0;	//��й�ȣ�� Ʋ��
		}

	}
	
	public int registerCheck(String userID){	//���̵��ߺ����� sql ����
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
			return 0;	//�̹������ϴ� ȸ��
		}else{
			return 1;	//���԰����� ȸ��
		}
	}
	
	public int register(UserDTO userDTO){	//ȸ������ sql����
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

	public ArrayList<UserDTO> listUserLoad() {		//��������Ʈ �ҷ�����
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
