package br.com.msansone.sansecurity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msansone.sansecurity.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	List<User> findAllByEmail(String email);

}
