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
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {
	/**
	 * 
	 */
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
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String title;

	@Column(name = "bill_amount", nullable = false)
	private float amount;

	@Column(name = "bill_type")
	private String type;

	@Transient
	private String billingDateString;

	@Column(name = "billing_date")
	private Date billingDate;

	private String version;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}