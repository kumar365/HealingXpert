package com.fossgen.healthcare.AidXpert.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "medicine")
public class Madicine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "medicine_type", length = 50)
	private String medicineType;

	@Column(name = "medicine_reg_no", length = 100)
	private String medicineRegNumber;

	@Column(name = "medicine_price")
	private float medicinePrice;

	@Column(name = "expiry_date", nullable = false)
	private Date expiryDate;

	@Column(name = "units")
	private int units;

	@Column(name = "qty_per_unit")
	private int quantityPerUnit;

	@Column(name = "price_per_unit")
	private float pricePerUnit;

	@Column(name = "total_qty")
	private int totalQuantity;

	@Column(name = "is_prescription_required", length = 1)
	private String isPrescriptionRequired;

	@Column(name = "version", length = 50)
	private String version;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@Column(name = "modified_by", length = 50)
	private String modifiedBy;

	@Column(name = "modified_date")
	private Timestamp modified_date;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getMedicineRegNumber() {
		return medicineRegNumber;
	}

	public void setMedicineRegNumber(String medicineRegNumber) {
		this.medicineRegNumber = medicineRegNumber;
	}

	public float getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(float medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(int quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getIsPrescriptionRequired() {
		return isPrescriptionRequired;
	}

	public void setIsPrescriptionRequired(String isPrescriptionRequired) {
		this.isPrescriptionRequired = isPrescriptionRequired;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
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
