package com.fossgen.healthcare.AidXpert.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.fossgen.healthcare.AidXpert.dto.LocalUser;
import com.fossgen.healthcare.AidXpert.dto.SignUpRequest;
import com.fossgen.healthcare.AidXpert.exception.DependentAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalDetailsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalRecordsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.UserAlreadyExistAuthenticationException;
import com.fossgen.healthcare.AidXpert.model.Ambulance;
import com.fossgen.healthcare.AidXpert.model.Dependent;
import com.fossgen.healthcare.AidXpert.model.MedicalDetails;
import com.fossgen.healthcare.AidXpert.model.MedicalRecords;
import com.fossgen.healthcare.AidXpert.model.Orders;
import com.fossgen.healthcare.AidXpert.model.User;

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

}
