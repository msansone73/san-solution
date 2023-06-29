package br.com.msansone.sansecurity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.sansecurity.exceptions.UserExistsException;
import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.model.dto.ErrorDTO;
import br.com.msansone.sansecurity.model.dto.UserLoginRequestDTO;
import br.com.msansone.sansecurity.model.dto.UserResponseDTO;
import br.com.msansone.sansecurity.service.UserService;
@RestController
@RequestMapping("/api/security")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addUser(
			@RequestBody User user){
		User newUser=null;
		UserResponseDTO userDto=null;
		try {
			newUser = userService.save(user);
			userDto= convertUserDTO(newUser, null);
			
		} catch (UserExistsException e) {
			
			return ResponseEntity.badRequest().body(new ErrorDTO("Email já existe!"));
		}
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping
	public List<UserResponseDTO> getAll(){
		logger.error("GetAll OK.");
		List<User> users = userService.getAll();
		List<UserResponseDTO> usersDTO = users.stream().map(u -> convertUserDTO(u,null)).toList();
		return usersDTO;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO useLogin){
		
		User user = userService.login(useLogin.email(), useLogin.pass());

		if (user==null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(convertUserDTO(user,null));
		}
	}

	private UserResponseDTO convertUserDTO(User user, String errorMessage) {
		return new UserResponseDTO(user.getId(), 
				user.getEmail(),
				user.getName(),
				user.getDateCreate(), 
				user.isAdmin(),
				user.isEnable());
	}

}
