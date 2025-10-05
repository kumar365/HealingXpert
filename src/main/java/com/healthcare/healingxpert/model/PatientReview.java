package com.healthcare.healingxpert.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
@Table(name = "patient_review")
public class PatientReview implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private User patientUser;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private User doctorUser;

	@Column(name = "is_review_anonymous", length = 1)
	private String isReviewAnonymous;

	@Column(name = "wait_time_rating")
	private int waitTimeRating;

	@Column(name = "overall_rating")
	private int overallRating;

	@Column(name = "review", length = 255)
	private String review;

	@Column(name = "is_doctor_recommended", length = 1)
	private String isDoctorRecommended;

	@Column(name = "review_date")
	private Date reviewDate;

	@Column(name = "version", length = 10)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public PatientReview() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPatientUser() {
		return patientUser;
	}

	public void setPatientUser(User patientUser) {
		this.patientUser = patientUser;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}

	public String getIsReviewAnonymous() {
		return isReviewAnonymous;
	}

	public void setIsReviewAnonymous(String isReviewAnonymous) {
		this.isReviewAnonymous = isReviewAnonymous;
	}

	public int getWaitTimeRating() {
		return waitTimeRating;
	}

	public void setWaitTimeRating(int waitTimeRating) {
		this.waitTimeRating = waitTimeRating;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getIsDoctorRecommended() {
		return isDoctorRecommended;
	}

	public void setIsDoctorRecommended(String isDoctorRecommended) {
		this.isDoctorRecommended = isDoctorRecommended;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}