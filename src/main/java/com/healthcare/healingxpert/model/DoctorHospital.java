package com.healthcare.healingxpert.model;

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

/**
 * @author KUMAR
 */
@Entity
@Table(name = "doctor_hospital")
public class DoctorHospital implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private User doctorUser;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "hospital_affiliation_id")
	private HospitalAffiliation hospitalAffiliation;

	@Column(name = "time_slot_per_client_mints")
	private int timeSlotPerClientMints;

	@Column(name = "first_consultation_fee")
	private double firstConsultationFee;

	@Column(name = "followup_consultation_fee")
	private double followupConsultationFee;

	@Column(name = "street_address", length = 500)
	private String streetAddress;

	@Column(name = "city", length = 50)
	private String city;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "country", length = 50)
	private String country;

	@Column(name = "pin_code", length = 10)
	private String pinCode;

	@Column(name = "version", length = 10)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public DoctorHospital() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}

	public HospitalAffiliation getHospitalAffiliation() {
		return hospitalAffiliation;
	}

	public void setHospitalAffiliation(HospitalAffiliation hospitalAffiliation) {
		this.hospitalAffiliation = hospitalAffiliation;
	}

	public int getTimeSlotPerClientMints() {
		return timeSlotPerClientMints;
	}

	public void setTimeSlotPerClientMints(int timeSlotPerClientMints) {
		this.timeSlotPerClientMints = timeSlotPerClientMints;
	}

	public double getFirstConsultationFee() {
		return firstConsultationFee;
	}

	public void setFirstConsultationFee(double firstConsultationFee) {
		this.firstConsultationFee = firstConsultationFee;
	}

	public double getFollowupConsultationFee() {
		return followupConsultationFee;
	}

	public void setFollowupConsultationFee(double followupConsultationFee) {
		this.followupConsultationFee = followupConsultationFee;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

}