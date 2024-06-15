package com.healthcare.HealingXpert.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.healthcare.HealingXpert.converter.StringListConverter;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 150, nullable = false)
	private String name;

	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imageData;

	@Column(name = "medicine_type", length = 100)
	private String medicineType;

	@Column(name = "medicine_category", length = 100)
	private String medicineCategory;

	@Column(name = "medicine_reg_no", length = 100)
	private String medicineRegNumber;

	@Column(name = "medicine_price", nullable = false, columnDefinition = "int default 0")
	private float medicinePrice;

	@Column(name = "expiry_date", nullable = false)
	private Date expiryDate;

	@Column(name = "units", columnDefinition = "int default 0")
	private int units;

	@Column(name = "qty_per_unit", columnDefinition = "int default 0")
	private int quantityPerUnit;

	@Column(name = "price_per_unit", columnDefinition = "int default 0")
	private float pricePerUnit;

	@Column(name = "total_qty", columnDefinition = "int default 0")
	private int totalQuantity;

	@Column(name = "is_prescription_required", length = 1, nullable = false)
	private String isPrescriptionRequired;

	@Column(name = "discount_percentage", columnDefinition = "int default 0")
	private int discountPercentage;

	@Column(name = "vendor", length = 150)
	private String vendor;

	@Convert(converter = StringListConverter.class)
	@Column(name = "used_for")
	private List<String> usedFor = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_details_id")
	private ProductDetails productDetails;

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

	@Transient
	private String expiryDateString;

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

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getExpiryDateString() {
		return expiryDateString;
	}

	public void setExpiryDateString(String expiryDateString) {
		this.expiryDateString = expiryDateString;
	}

	public String getMedicineCategory() {
		return medicineCategory;
	}

	public void setMedicineCategory(String medicineCategory) {
		this.medicineCategory = medicineCategory;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public List<String> getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(List<String> usedFor) {
		this.usedFor = usedFor;
	}

}
