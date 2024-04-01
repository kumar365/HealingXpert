package com.fossgen.healthcare.AidXpert.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
	
	
	List<Appointment> findByDoctorName(String doctorName);
	
	
	List<Appointment> findByPatientName(String patientName);
	
	@Modifying 
	@Query("update Appointment a set a.confirmed = ?1 where a.id = ?2")
	int setConfirmation(String confitmation, Integer id);
	

	@Modifying 
	@Query("update Appointment a set a.prescription = ?1 where a.id = ?2")
	int setPrescription(String prescription, Integer id);
	
	@Query(value="select * from appointment a where a.appointment_date =?1 AND a.doctor_name =?2", nativeQuery=true)
	List<Appointment> findByDate(String date,String doctorName);
}
