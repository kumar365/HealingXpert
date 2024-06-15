package com.healthcare.HealingXpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.HealingXpert.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

	@Query("SELECT O FROM Orders O WHERE O.user.id= ?1")
	List<Orders> findOrdersByUserId(Long id);
}