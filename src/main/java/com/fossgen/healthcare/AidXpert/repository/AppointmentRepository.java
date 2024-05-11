package com.fossgen.healthcare.AidXpert.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fossgen.healthcare.AidXpert.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query(value = "select * from appointment a where a.doctor_id =?1 ", nativeQuery = true)
	List<Appointment> findAppointmentsByDoctorId(Long doctorId);

	@Query(value = "select * from appointment a where a.patient_id =?1 ", nativeQuery = true)
	List<Appointment> findAppointmentsByPatientId(Long patientId);

	@Modifying
	@Query("update Appointment a set a.confirmed = ?1 where a.id = ?2")
	int setConfirmation(String confitmation, Integer id);

	@Modifying
	@Query("update Appointment a set a.prescription = ?1 where a.id = ?2")
	int setPrescription(String prescription, Integer id);

	@Query(value = "select * from appointment a where a.doctor_id =?1 AND a.appointment_date =?2  ", nativeQuery = true)
	List<Appointment> findAppointmentsByDoctorIdDate(Long doctorId, Date date);

}
