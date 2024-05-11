package com.fossgen.healthcare.AidXpert.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.Util.DateUtils;
import com.fossgen.healthcare.AidXpert.constants.CommonConstants;
import com.fossgen.healthcare.AidXpert.model.Appointment;
import com.fossgen.healthcare.AidXpert.repository.AppointmentRepository;
import com.fossgen.healthcare.AidXpert.repository.UserRepository;

@Service
@Transactional
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Appointment> listAll() {
		return appointmentRepository.findAll();
	}

	public Appointment save(Appointment appointment) {
		if (null != appointment.getPatientUser()) {
			if (null != appointment.getPatientUser().getFirstName()) {
				appointment.getPatientUser().setUsername(appointment.getPatientUser().getFirstName());
				appointment.getPatientUser().setDisplayName(appointment.getPatientUser().getFirstName());
			} else {
				appointment.getPatientUser().setUsername("new user");
				appointment.getPatientUser().setDisplayName("new user");
			}
			appointment.getPatientUser().setPassword(appointment.getPatientUser().getEmail());
			appointment.getPatientUser().setUserType(CommonConstants.PATIENT);
			appointment.getPatientUser().setVersion(AppUtils.VERSION);
			appointment.getPatientUser().setLostLogin(AppUtils.getTimestamp());
			appointment.getPatientUser().setCreatedBy(AppUtils.getName());
			appointment.getPatientUser().setCreatedDate(AppUtils.getTimestamp());
			userRepository.save(appointment.getPatientUser());
		}

		if (null == appointment.getAppointmentDate()) {
			appointment.setAppointmentDate(DateUtils.getSqlDate());
		}
		if (null == appointment.getStartTime()) {
			appointment.setStartTime(DateUtils.getSqlTimestamp());
		}
		if (null == appointment.getEndTime()) {
			appointment.setEndTime(DateUtils.getSqlTimestamp());
		}
		if (appointment.getAmountPaid() < 0) {
			appointment.setAmountPaid(0);
		}
		if (null == appointment.getStatus() || appointment.getStatus().isEmpty()) {
			appointment.setStatus("Created");
		}
		appointment.setVersion(AppUtils.VERSION);
		appointment.setCreatedBy(AppUtils.getName());
		appointment.setCreatedDate(AppUtils.getTimestamp());
		return appointmentRepository.save(appointment);
	}

	public void cancelAppointment(Integer id) {
		appointmentRepository.deleteById(id);
	}

	public int setConfirmation(String confirmation, Integer id) {
		return appointmentRepository.setConfirmation(confirmation, id);
	}

	public int setPrescription(String confirmation, Integer id) {
		return appointmentRepository.setPrescription(confirmation, id);
	}

	public Optional<Appointment> get(Integer id) {
		return appointmentRepository.findById(id);

	}

	public List<Appointment> findAppointmentsByPatientId(Long patientId) {
		return appointmentRepository.findAppointmentsByPatientId(patientId);
	}

	public List<Appointment> findAppointmentsByDoctorId(Long doctorId) {
		return appointmentRepository.findAppointmentsByDoctorId(doctorId);
	}

	public List<Appointment> findAppointmentsByDoctorIdDate(Long doctorId, Date date) {
		return appointmentRepository.findAppointmentsByDoctorIdDate(doctorId, date);
	}

	public List<Appointment> findTodayAppointmentsByDoctorId(Long id) {
		return appointmentRepository.findAppointmentsByDoctorIdDate(id, DateUtils.getSqlDate());
	}

}
