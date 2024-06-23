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
@Table(name = "doctor_experience")
public class DoctorExperience implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "doctor_details_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("doctorExperiences")
	private DoctorDetails doctorDetails;

	@Column(name = "hospital_name", length = 100, nullable = false)
	private String hospitalName;

	@Column(name = "from_year", length = 5, nullable = false)
	private String fromYear;

	@Column(name = "to_year", length = 5, nullable = false)
	private String toYear;

	@Column(name = "experience_data", length = 5, nullable = false)
	private String experienceData;

	@Column(name = "designation", length = 150, nullable = false)
	private String designation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getExperienceData() {
		return experienceData;
	}

	public void setExperienceData(String experienceData) {
		this.experienceData = experienceData;
	}

	public DoctorDetails getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(DoctorDetails doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

}
