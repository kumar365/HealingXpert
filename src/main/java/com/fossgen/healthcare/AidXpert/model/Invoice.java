package com.fossgen.healthcare.AidXpert.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "invoice")
@DynamicUpdate
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "invoice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer invoiceID;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private User patientUser;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private User doctorUser;

	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "appointment_id")
	private Integer appointmentID;

	private String invoice;

	@Column(name = "amount", nullable = false)
	private double amount;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "tax_id")
	private Taxrates taxrates;

	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;

	@Column(name = "version", length = 10)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@OneToMany(mappedBy = "invoice")
	private List<Appointment> appointments;

	public Invoice(Integer invoiceID, String patientName, Integer appointmentID, String invoice) {
		super();
		this.invoiceID = invoiceID;
		this.patientName = patientName;
		this.appointmentID = appointmentID;
		this.invoice = invoice;
	}

	public Invoice() {

	}

	public Integer getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(Integer invoiceID) {
		this.invoiceID = invoiceID;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
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

	public User getPatientUser() {
		return patientUser;
	}

	public void setPatientUser(User patientUser) {
		this.patientUser = patientUser;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Taxrates getTaxrates() {
		return taxrates;
	}

	public void setTaxrates(Taxrates taxrates) {
		this.taxrates = taxrates;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(User doctorUser) {
		this.doctorUser = doctorUser;
	}

}
