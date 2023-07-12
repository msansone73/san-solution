package br.com.msansone.sansecurity.service;

import java.util.List;

import br.com.msansone.sansecurity.exceptions.UserExistsException;
import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.model.dto.UserResponseDTO;

public interface UserService {

	User save(User user) throws UserExistsException;


	List<User> getAll(boolean justEnabed);

	User login(String email, String pass);

	User updateUser(Long id, UserResponseDTO useDto) throws Exception;

	void changePass(String email, String oldPass, String newPass) throws Exception;

	User getById(Long id);

	User disableUser(Long id);
}
