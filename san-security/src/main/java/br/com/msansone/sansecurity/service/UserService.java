package br.com.msansone.sansecurity.service;

import java.util.List;

import br.com.msansone.sansecurity.model.User;

public interface UserService {

	User save(User user);

	List<User> getAll();

	User login(String email, String pass);

}
