package com.fossgen.healthcare.AidXpert.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "prescription")
@DynamicUpdate
public class Prescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prescriptionID;

	@Column(name = "prescription_name")
	private String prescriptionName;

	@Column(name = "patient_name")
	private String patientName;

	private Integer quantity;

	@Column(name = "prescription_days")
	private Integer prescriptionDays;

	@Column(name = "prescription_time")
	private Integer prescriptionTime;

	@Column(name = "appointment_id")
	private Integer appointmentID;

	private String description;

	@Column(name = "doctor_name")
	private String doctorName;

	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public Prescription(String patientName, Integer appointmentID, String description, String doctorName) {
		super();
		this.patientName = patientName;
		this.appointmentID = appointmentID;
		this.description = description;
		this.doctorName = doctorName;
	}

	public Prescription() {
	}

	public Integer getPrescriptionID() {
		return prescriptionID;
	}

	public void setPrescriptionID(Integer prescriptionID) {
		this.prescriptionID = prescriptionID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrescriptionDays() {
		return prescriptionDays;
	}

	public void setPrescriptionDays(Integer prescriptionDays) {
		this.prescriptionDays = prescriptionDays;
	}

	public Integer getPrescriptionTime() {
		return prescriptionTime;
	}

	public void setPrescriptionTime(Integer prescriptionTime) {
		this.prescriptionTime = prescriptionTime;
	}

}
