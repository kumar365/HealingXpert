package com.healthcare.healingxpert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

	@Query("SELECT C FROM CartItems C WHERE C.medicine.id= ?1")
	List<CartItems> findAllByProductId(int id);

}
