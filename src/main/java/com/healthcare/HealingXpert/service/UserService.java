package com.healthcare.HealingXpert.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.healthcare.HealingXpert.dto.LocalUser;
import com.healthcare.HealingXpert.dto.SignUpRequest;
import com.healthcare.HealingXpert.exception.DependentAlreadyExistException;
import com.healthcare.HealingXpert.exception.MedicalDetailsAlreadyExistException;
import com.healthcare.HealingXpert.exception.MedicalRecordsAlreadyExistException;
import com.healthcare.HealingXpert.exception.UserAlreadyExistAuthenticationException;
import com.healthcare.HealingXpert.model.Ambulance;
import com.healthcare.HealingXpert.model.Dependent;
import com.healthcare.HealingXpert.model.MedicalDetails;
import com.healthcare.HealingXpert.model.MedicalRecords;
import com.healthcare.HealingXpert.model.Orders;
import com.healthcare.HealingXpert.model.User;

/**
 * @author KUMAR
 * @since 01/11/2023
 */
public interface UserService {

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	User findUserByPhoneNumber(String phoneNumber);

	User findUserByPhoneNumberAndPassword(String phoneNumber, String password);

	User findUserById(Long id);
	
	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
			OidcUserInfo userInfo);

	public void updateUser(User user);

	public Dependent saveDependent(Dependent dependent) throws DependentAlreadyExistException;

	public List<Dependent> getDependents(Long userId);

	public MedicalDetails saveMedicalDetails(MedicalDetails medicalDetails) throws MedicalDetailsAlreadyExistException;

	public List<MedicalDetails> getMedicalDetails(Long userId);

	public void deleteMedicalDetails(Long id);

	public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) throws MedicalRecordsAlreadyExistException;

	public List<MedicalRecords> getMedicalRecords(Long userId);

	public void deleteMedicalRecords(Long id);

	public User registerNewPatient(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	public User registerNewDoctor(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	public User registerNewStaff(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	public List<User> getUsers();

	public void registerAmbulance(Ambulance ambulance);

	public boolean checkAmbulance(Ambulance ambulance);

	public String forgotPassword(String email);

	public String resetPassword(String token, String password);

	public User getUserDataByToken(String token);

	public List<User> getPatientList();

	public User getPatientById(Long id);

	public List<User> getDoctorList();

	public User getDoctorById(Long id);

	public List<Orders> getOrdersList(Long id);

	public List<User> getPatientListById(Long id);

	List<User> getTodayPatientList();

}
