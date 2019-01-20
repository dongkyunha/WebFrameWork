package user.mapper;

import java.util.ArrayList;

import user.model.UserDTO;

public interface UserMapper {
	UserDTO getOneList(String userID);
	int register(UserDTO userDTO);
	ArrayList<UserDTO> listUserLoad();
}
