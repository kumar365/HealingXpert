package com.healthcare.healingxpert.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    User findByToken(String token);

    @Query("SELECT u.id, u.displayName, u.email, u.userType, u.phoneNumber FROM User u")
    List<Object[]> getUsers();

    @Query("SELECT u FROM User u WHERE u.userType = ?1")
    List<User> getUsersByUserType(String userType);

    @Query("SELECT u FROM User u WHERE u.userType = ?1 AND DATE(u.createdDate) = ?2")
    List<User> getUsersByUserTypeAndDate(String userType, Date date);

    @Query("SELECT u FROM User u WHERE u.id = ?1 AND u.userType = ?2")
    User findUserByIdAandUserType(Long id, String userType);

    @Query("SELECT u FROM User u WHERE u.id != ?1 AND u.userType = ?2")
    List<User> findPatientListById(Long id, String userType);
}
