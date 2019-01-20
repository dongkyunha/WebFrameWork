package user.model;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private String userID;
	private String userPassword;
	private String userName;
	private int userAge;
	
	public UserDTO(){}
	
	public UserDTO(String userID, String userPassword, String userName, int userAge) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAge = userAge;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	@Override
	public String toString() {
		return "userDTO [userID=" + userID + ", userPassword=" + userPassword + ", userName=" + userName + ", userAge="
				+ userAge + "]";
	}
}
