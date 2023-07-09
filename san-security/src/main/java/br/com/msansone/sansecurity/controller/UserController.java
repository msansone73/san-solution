package br.com.msansone.sansecurity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.sansecurity.exceptions.UserExistsException;
import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.model.dto.ErrorDTO;
import br.com.msansone.sansecurity.model.dto.UserLoginRequestDTO;
import br.com.msansone.sansecurity.model.dto.UserRequestChangePassDto;
import br.com.msansone.sansecurity.model.dto.UserResponseDTO;
import br.com.msansone.sansecurity.service.UserService;
@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO useLogin){
		logger.debug("useLogin="+useLogin.email()+" - "+useLogin.pass());
		User user = userService.login(useLogin.email(), useLogin.pass());

		if (user==null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(convertUserDTO(user));
		}
	}
	

	@GetMapping
	public List<UserResponseDTO> getAll(){
		logger.error("GetAll OK.");
		List<User> users = userService.getAll();
		List<UserResponseDTO> usersDTO = users.stream().map(u -> convertUserDTO(u)).toList();
		return usersDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		logger.error("GetAll OK.");
		User user = userService.getById(id);
		if (user==null) {
			return ResponseEntity.noContent().build();
		}
		return    ResponseEntity.ok(convertUserDTO(user));
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addUser(
			@RequestBody User user){
		User newUser=null;
		UserResponseDTO userDto=null;
		try {
			newUser = userService.save(user);
			userDto= convertUserDTO(newUser);
			
		} catch (UserExistsException e) {
			
			return ResponseEntity.badRequest().body(new ErrorDTO("Email já existe!"));
		}
		return ResponseEntity.ok(userDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserResponseDTO useDto)  {
		
		try {
			User savedUser=userService.updateUser(id,useDto);
			return ResponseEntity.ok(savedUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/pass")
	public ResponseEntity<?> updatePass(
			@RequestBody UserRequestChangePassDto userRequestChangePassDto){
		try {
			userService.changePass(userRequestChangePassDto.email(),
					userRequestChangePassDto.oldPass(),
					userRequestChangePassDto.newPass());
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
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
