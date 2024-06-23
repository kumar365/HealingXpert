package com.healthcare.HealingXpert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "doctor_registration")
public class DoctorRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "doctor_details_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("doctorRegistrations")
	private DoctorDetails doctorDetails;

	@Column(name = "registration_name", nullable = false, length = 100)
	private String registrationName;

	@Column(name = "year", length = 5, nullable = false)
	private String year;

	public DoctorRegistration() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistrationName() {
		return registrationName;
	}

	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public DoctorDetails getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(DoctorDetails doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

}
