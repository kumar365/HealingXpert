package com.healthcare.HealingXpert.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "doctor_details")
//@Check(constraints = "rating >= 0 AND rating <= 5")
public class DoctorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_details_id", nullable = false)
	private Long doctorDetailsId;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "registration_number", nullable = false, length = 20)
	private String registrationNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	@Column(name = "clinic_visit_fee")
	private double clinicVisitFee;

	@Column(name = "tele_consult_fee")
	private double teleConsultationFee;

	@Column(name = "video_consult_fee")
	private double videoConsultFee;

	@Column(name = "service")
	private String service;

	@Column(name = "specialization")
	private String specialization;

	@Column(name = "rating", precision = 2, scale = 1, columnDefinition = "default 0")
	private BigDecimal rating;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_details_id")
	@JsonIgnoreProperties("doctorDetails")
	private List<DoctorEducation> doctorEducations;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_details_id")
	@JsonIgnoreProperties("doctorDetails")
	private List<DoctorExperience> doctorExperiences;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_details_id")
	@JsonIgnoreProperties("doctorDetails")
	private List<DoctorAwards> doctorAwards;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_details_id")
	@JsonIgnoreProperties("doctorDetails")
	private List<DoctorMemberships> doctorMemberships;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_details_id")
	@JsonIgnoreProperties("doctorDetails")
	private List<DoctorRegistration> doctorRegistrations;

	public DoctorDetails() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public double getClinicVisitFee() {
		return clinicVisitFee;
	}

	public void setClinicVisitFee(double clinicVisitFee) {
		this.clinicVisitFee = clinicVisitFee;
	}

	public double getTeleConsultationFee() {
		return teleConsultationFee;
	}

	public void setTeleConsultationFee(double teleConsultationFee) {
		this.teleConsultationFee = teleConsultationFee;
	}

	public double getVideoConsultFee() {
		return videoConsultFee;
	}

	public void setVideoConsultFee(double videoConsultFee) {
		this.videoConsultFee = videoConsultFee;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public List<DoctorEducation> getDoctorEducations() {
		return doctorEducations;
	}

	public void setDoctorEducations(List<DoctorEducation> doctorEducations) {
		this.doctorEducations = doctorEducations;
	}

	public List<DoctorExperience> getDoctorExperiences() {
		return doctorExperiences;
	}

	public void setDoctorExperiences(List<DoctorExperience> doctorExperiences) {
		this.doctorExperiences = doctorExperiences;
	}

	public List<DoctorAwards> getDoctorAwards() {
		return doctorAwards;
	}

	public void setDoctorAwards(List<DoctorAwards> doctorAwards) {
		this.doctorAwards = doctorAwards;
	}

	public List<DoctorMemberships> getDoctorMemberships() {
		return doctorMemberships;
	}

	public void setDoctorMemberships(List<DoctorMemberships> doctorMemberships) {
		this.doctorMemberships = doctorMemberships;
	}

	public List<DoctorRegistration> getDoctorRegistrations() {
		return doctorRegistrations;
	}

	public void setDoctorRegistrations(List<DoctorRegistration> doctorRegistrations) {
		this.doctorRegistrations = doctorRegistrations;
	}

	public Long getDoctorDetailsId() {
		return doctorDetailsId;
	}

	public void setDoctorDetailsId(Long doctorDetailsId) {
		this.doctorDetailsId = doctorDetailsId;
	}

}