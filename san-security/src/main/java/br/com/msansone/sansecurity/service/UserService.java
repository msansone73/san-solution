package br.com.msansone.sansecurity.service;

import java.util.List;

import br.com.msansone.sansecurity.exceptions.UserExistsException;
import br.com.msansone.sansecurity.model.User;

public interface UserService {

	User save(User user) throws UserExistsException;

	List<User> getAll();

	User login(String email, String pass);

}
