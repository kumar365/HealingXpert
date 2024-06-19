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

/**
 * @author KUMAR
 */
@Entity
@Table(name = "doctor_details")
public class DoctorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "doctor_id")
//	private User doctorUser;

//	@ManyToOne
//	@JoinColumn(name = "education_id")
//	private Education education;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorUser")
//	private Set<Qualification> qualifications;
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorUser")
//	private Set<DoctorSpecialization> doctorSpecializations;
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorUser")
//	private Set<Experience> experience;

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


	public DoctorDetails() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public User getDoctorUser() {
//		return doctorUser;
//	}
//
//	public void setDoctorUser(User doctorUser) {
//		this.doctorUser = doctorUser;
//	}

//	public Education getEducation() {
//		return education;
//	}
//
//	public void setEducation(Education education) {
//		this.education = education;
//	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

//	public Set<Experience> getExperience() {
//		return experience;
//	}
//
//	public void setExperience(Set<Experience> experience) {
//		this.experience = experience;
//	}
//
//	public Set<Qualification> getQualifications() {
//		return qualifications;
//	}
//
//	public void setQualifications(Set<Qualification> qualifications) {
//		this.qualifications = qualifications;
//	}
//
//	public Set<DoctorSpecialization> getDoctorSpecializations() {
//		return doctorSpecializations;
//	}
//
//	public void setDoctorSpecializations(Set<DoctorSpecialization> doctorSpecializations) {
//		this.doctorSpecializations = doctorSpecializations;
//	}

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

}