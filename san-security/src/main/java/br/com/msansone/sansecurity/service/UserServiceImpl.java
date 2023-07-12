package br.com.msansone.sansecurity.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.msansone.sansecurity.controller.UserController;
import br.com.msansone.sansecurity.exceptions.UserExistsException;
import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.model.dto.UserResponseDTO;
import br.com.msansone.sansecurity.repository.UserRepository;
import br.com.msansone.sansecurity.utils.HashUtils;

@Service
public class UserServiceImpl implements UserService {
	

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user) throws UserExistsException {
		if (existsEmail(user)) {
			throw new UserExistsException("Email já cadastrado!");
		}
		if (user.getDateCreate()==null) {
			user.setDateCreate(LocalDate.now());
		}

		user.setPass(HashUtils.createHash(user.getPass()));
		return userRepository.save(user); 
		
	}

	@Override
	public List<User> getAll(boolean justEnabed) {
		if (justEnabed) {
            return userRepository.findAllByEnable(Boolean.TRUE);
        } else {
            return userRepository.findAll();
        }
	}
	

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User disableUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null) {
            user.setEnable(false);
            userRepository.save(user);
        }
        return user;
    }

	@Override
	public User login(String email, String pass) {
		
		List<User> users = userRepository.findAllByEmail(email);
		
		if (CollectionUtils.isEmpty(users)) {
			logger.info(String.format("email %s não encontrado", email));
			return null;			
		}
		if (users.size()>1) {
			logger.info(String.format("encontrado mais de uma corrência do email %s.", email));
			return null;			
		}
		
		if (!HashUtils.validatePass(pass, users.get(0).getPass())) {
			logger.info(String.format("email / pass inválida:  %s/%s.", email, pass));
			return null;			
		}
		
		     
		return users.get(0);
	}

	@Override
	public User updateUser(Long id, UserResponseDTO userDto) throws Exception {
		User userAtual = userRepository.findById(id).orElseThrow();
		List<User> userEmail = userRepository.findAllByEmail(userDto.email());
		valida(userAtual, userEmail);
		userAtual.setEmail(userDto.email());
		userAtual.setAdmin(userDto.admin());
		userAtual.setEnable(userDto.enable());
		userAtual.setName(userDto.name());
		userRepository.save(userAtual);
		return userAtual;
	}
	
	@Override
	public void changePass(String email, String oldPass, String newPass) throws Exception {
		User user = getUserByEmail(email);
		if (HashUtils.validatePass(oldPass, user.getPass())) {
			user.setPass(HashUtils.createHash(newPass));
			userRepository.save(user);
		} else {
			throw new Exception("Senha não confere.");
		}
		
	}	
	
	private User getUserByEmail(String email) throws Exception {
		List<User> userEmail = userRepository.findAllByEmail(email);
		if (userEmail.size()>1) {
			logger.error(String.format("Email %s está a ser usado por mais de um cliente!", userEmail.get(0).getEmail()));
			throw new Exception("Há mais de um cliente com este e-mail!");
		} else if (CollectionUtils.isEmpty(userEmail)) {
			throw new Exception("Não há cliente com este e-mail!");
		} else {
			return userEmail.get(0);
		}
	}
	
	private void valida(User userAtual, List<User> userEmail) throws Exception {
		if (userEmail.size()>1) {
			logger.error(String.format("Email %s está a ser usado por mais de um cliente!", userEmail.get(0).getEmail()));
			throw new Exception("Há mais de um cliente com este e-mail!");
		} else if (userEmail.size()==1 && userEmail.get(0).getId()!=userAtual.getId()) {
			throw new Exception("Este email já está a ser usado por outro cliente!");
		}
	}

	private boolean existsEmail(User user) {
		return  !CollectionUtils.isEmpty(userRepository.findAllByEmail(user.getEmail()));
	}


}
