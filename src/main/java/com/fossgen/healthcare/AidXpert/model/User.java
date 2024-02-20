package com.fossgen.healthcare.AidXpert.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnTransformer;

import com.fossgen.healthcare.AidXpert.enums.AuthenticationType;

@Entity
@Table(name = "users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ColumnTransformer(forColumn = "name", read = "pgp_sym_decrypt(name::BYTEA, 'secret-key-12345')", write = "pgp_sym_encrypt (?, 'secret-key-12345')")
	@Column(name = "name", columnDefinition = "bytea", nullable = false)
	private String username;
	
	@Column(name = "user_type")
	private String userType;

	@ColumnTransformer(forColumn = "password", read = "pgp_sym_decrypt(password::BYTEA, 'secret-key-12345')", write = "pgp_sym_encrypt (?, 'secret-key-12345')")
	@Column(name = "password", columnDefinition = "bytea", nullable = false)
	private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	@ColumnTransformer(forColumn = "email", read = "pgp_sym_decrypt(email::BYTEA, 'secret-key-12345')", write = "pgp_sym_encrypt (?, 'secret-key-12345')")
	@Column(name = "email", columnDefinition = "bytea", nullable = false)
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	private String gender;
	
	@Transient
	private String dateOfBirthString;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	private String age;

	private String biography;

	@Column(name = "blood_group")
	private String bloodGroup;

	private String address;
	
	@OneToMany(mappedBy="user")
    private Set<Dependent> dependents;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city")
	private City city;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state")
	private State state;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country")
	private Country country;

	@Column(name = "profile_image_name")
	private String profileImageName;

	@Column(name = "pin_code")
	private String pinCode;

	private String version;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	@Column(name = "lost_login")
	private Timestamp lostLogin;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_type", length = 20)
	private AuthenticationType authType;

	private boolean enabled;

	@Column(name = "provider_user_id")
	private String providerUserId;

	@Column(name = "display_name")
	private String displayName;

	private String provider;

	@Transient
	private String token;

	@Transient
	private String oldPassword;

	@Transient
	private String newPassword;

	public User() {
		super();
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getLostLogin() {
		return lostLogin;
	}

	public void setLostLogin(Timestamp lostLogin) {
		this.lostLogin = lostLogin;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public AuthenticationType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthenticationType authType) {
		this.authType = authType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDateOfBirthString() {
		return dateOfBirthString;
	}

	public void setDateOfBirthString(String dateOfBirthString) {
		this.dateOfBirthString = dateOfBirthString;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(Set<Dependent> dependents) {
		this.dependents = dependents;
	}

}