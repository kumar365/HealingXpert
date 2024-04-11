package com.fossgen.healthcare.AidXpert.model;

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
@Table(name = "doctor_slot")
public class DoctorSlot implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private User doctorUser;

	@Column(name = "day_week", nullable = false)
	private int dayWeek;

	@Column(name = "slot_start", nullable = false)
	private Timestamp slotStart;

	@Column(name = "slot_end", nullable = false)
	private Timestamp slotEnd;

	@Column(name = "slot_date", nullable = false)
	private Date slotDate;

	@Column(name = "status", nullable = false, length = 10)
	private String status;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private User patientUser;

	@Column(name = "day_week_text", length = 100)
	private String dayWeekText;

	@Column(name = "price")
	private double price;

	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public DoctorSlot() {
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

	public int getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(int dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Timestamp getSlotStart() {
		return slotStart;
	}

	public void setSlotStart(Timestamp slotStart) {
		this.slotStart = slotStart;
	}

	public Timestamp getSlotEnd() {
		return slotEnd;
	}

	public void setSlotEnd(Timestamp slotEnd) {
		this.slotEnd = slotEnd;
	}

	public Date getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getPatientUser() {
		return patientUser;
	}

	public void setPatientUser(User patientUser) {
		this.patientUser = patientUser;
	}

	public String getDayWeekText() {
		return dayWeekText;
	}

	public void setDayWeekText(String dayWeekText) {
		this.dayWeekText = dayWeekText;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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