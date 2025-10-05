package com.healthcare.healingxpert.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.healingxpert.model.Dependent;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {
    @Query("SELECT d FROM Dependent d WHERE d.userId = ?1")
    List<Dependent> findDependentSByUserId(Long userId);
}
