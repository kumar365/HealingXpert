package com.healthcare.HealingXpert.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "doctor_education")
public class DoctorEducation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "doctor_details_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("doctorEducations")
	private DoctorDetails doctorDetails;

	@Column(name = "degree_name", length = 100, nullable = false)
	private String degreeName;

	@Column(name = "university", length = 100)
	private String university;

	@Column(name = "from_year", length = 5)
	private String fromYear;

	@Column(name = "to_year", length = 5)
	private String toYear;

	@Column(name = "year_of_completion", length = 5)
	private String yearOfCompletion;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "country", length = 100)
	private String country;

	@Column(name = "version", length = 10)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public DoctorEducation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public DoctorDetails getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(DoctorDetails doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public String getYearOfCompletion() {
		return yearOfCompletion;
	}

	public void setYearOfCompletion(String yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}

}