package com.healthcare.HealingXpert.model;

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
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnTransformer;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "bill")
public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bill_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;

	@ColumnTransformer(forColumn = "name", read = "pgp_sym_decrypt(name::BYTEA, 'secret-key-12345')", write = "pgp_sym_encrypt (?, 'secret-key-12345')")
	@Column(name = "name", columnDefinition = "bytea", nullable = false)
	private String name;

	@Column(name = "order_id")
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoiceId;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private User patientUser;

	private String title;

	@Column(name = "bill_amount", nullable = false)
	private float billAmount;

	@Column(name = "bill_type")
	private String billType;

	@Column(name = "billing_date")
	private Date billingDate;

	@Column(name = "payment_status", length = 50)
	private String paymentStatus;

	@Column(name = "version", length = 10)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Transient
	private String billingDateString;

	public Bill() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBillingDateString() {
		return billingDateString;
	}

	public void setBillingDateString(String billingDateString) {
		this.billingDateString = billingDateString;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
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

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Invoice getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Invoice invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	public User getPatientUser() {
		return patientUser;
	}

	public void setPatientUser(User patientUser) {
		this.patientUser = patientUser;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
}