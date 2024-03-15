package com.fossgen.healthcare.AidXpert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fossgen.healthcare.AidXpert.enums.AuthenticationType;
import com.fossgen.healthcare.AidXpert.model.User;

/**
 * @author KUMAR
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT U FROM User U WHERE U.username = ?1")
	List<User> getUserByName(String name);

	@Query("SELECT U FROM User U WHERE U.username = ?1 AND U.password=?2")
	List<User> getUserByName(String name, String password);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<User> findByUsername(String username);

	@Modifying
	@Query("UPDATE User u SET u.authType = ?2 WHERE u.username = ?1")
	public void updateAuthenticationType(String username, AuthenticationType authType);

	User findByEmail(String email);

	@Query("SELECT U.username,U.email,U.userType,U.id FROM User U")
	List<Object[]> getUsers();
	
	User findByToken(String token);
	
	@Query("SELECT U FROM User U WHERE U.phoneNumber = ?1")
	User findByPhoneNumber(String phoneNumber );
	
	@Query("SELECT U FROM User U WHERE U.phoneNumber = ?1 AND U.password=?2")
	User findByPhoneNumber(String phoneNumber,String password );

}
