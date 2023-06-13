package br.com.msansone.sansecurity.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.msansone.sansecurity.model.User;
import br.com.msansone.sansecurity.repository.UserRepository;
import br.com.msansone.sansecurity.utils.HashUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user){
		if (user.getDateCreate()==null) {
			user.setDateCreate(LocalDate.now());
		}

		user.setPass(HashUtils.createHash(user.getPass()));
		return userRepository.save(user); 
		
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User login(String email, String pass) {
		
		List<User> users = userRepository.findAllByEmail(email);
		
		if (CollectionUtils.isEmpty(users)) {
			System.out.println(String.format("email %s não encontrado", email));
			return null;			
		}
		if (users.size()>1) {
			System.out.println(String.format("encontrado mais de uma corrência do email %s.", email));
			return null;			
		}
		
		if (!HashUtils.validatePass(pass, users.get(0).getPass())) {
			System.out.println(String.format("email / pass inválida:  %s/%s.", email, pass));
			return null;			
		}
		
		     
		return users.get(0);
	}

}
