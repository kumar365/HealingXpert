package com.healthcare.HealingXpert.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "cart_items")
public class CartItems implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "session_id")
	private ShoppingSession shoppingSession;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Medicine medicine;

	@Column(name = "quantity", nullable = false, columnDefinition = "int default 0")
	private int quantity;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public CartItems() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShoppingSession getShoppingSession() {
		return shoppingSession;
	}

	public void setShoppingSession(ShoppingSession shoppingSession) {
		this.shoppingSession = shoppingSession;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}