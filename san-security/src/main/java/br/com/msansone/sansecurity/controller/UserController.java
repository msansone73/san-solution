package br.com.msansone.sansecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.model.dto.UserLoginRequestDTO;
import br.com.msansone.sansecurity.model.dto.UserResponseDTO;
import br.com.msansone.sansecurity.service.UserService;

@RestController
@RequestMapping("/api/security")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<User> addUser(
			@RequestBody User user){
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping
	public List<UserResponseDTO> getAll(){
		List<User> users = userService.getAll();
		List<UserResponseDTO> usersDTO = users.stream().map(u -> convertUserDTO(u)).toList();
		return usersDTO;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO useLogin){
		
		User user = userService.login(useLogin.email(), useLogin.pass());

		if (user==null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(convertUserDTO(user));
		}
	}

	private UserResponseDTO convertUserDTO(User user) {
		return new UserResponseDTO(user.getId(), 
				user.getEmail(),
				user.getName(),
				user.getDateCreate(), 
				user.isAdmin(),
				user.isEnable());
	}

}
