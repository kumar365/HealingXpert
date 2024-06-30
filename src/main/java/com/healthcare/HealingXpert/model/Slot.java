package com.healthcare.HealingXpert.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "slot")
public class Slot implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "day_week", nullable = false)
	private int dayWeek;

	@ManyToOne
	@JoinColumn(name = "doctor_slot_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("slots")
	private DoctorSlot doctorSlot;

	@Column(name = "slot_date", nullable = false)
	private Date slotDate;
	
	@Column(name = "day_name", nullable = false, length = 15)
	private String dayName;

	@Column(name = "start_time", nullable = false)
	private String startTime;

	@Column(name = "end_time", nullable = false)
	private String endTime;

	@Column(name = "status", nullable = false, length = 25)
	private String status;

	@Column(name = "is_booked", nullable = false, columnDefinition = "default false")
	private boolean isBooked;

	public Slot() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(int dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Date getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public DoctorSlot getDoctorSlot() {
		return doctorSlot;
	}

	public void setDoctorSlot(DoctorSlot doctorSlot) {
		this.doctorSlot = doctorSlot;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

}