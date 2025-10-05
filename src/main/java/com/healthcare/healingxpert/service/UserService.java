package com.healthcare.healingxpert.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.healthcare.healingxpert.dto.LocalUser;
import com.healthcare.healingxpert.dto.SignUpRequest;
import com.healthcare.healingxpert.exception.*;
import com.healthcare.healingxpert.model.*;

public interface UserService {
    User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User findUserByEmail(String email);

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

    User findUserById(Long id);

    void updateUser(User user);

    Dependent saveDependent(Dependent dependent) throws DependentAlreadyExistException;

    List<Dependent> getDependents(Long userId);

    MedicalDetails saveMedicalDetails(MedicalDetails medicalDetails) throws MedicalDetailsAlreadyExistException;

    List<MedicalDetails> getMedicalDetails(Long userId);

    void deleteMedicalDetails(Long id);

    MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) throws MedicalRecordsAlreadyExistException;

    List<MedicalRecords> getMedicalRecords(Long userId);

    void deleteMedicalRecords(Long id);

    User registerNewPatient(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User registerNewDoctor(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User registerNewStaff(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    List<User> getUsers();

    void registerAmbulance(Ambulance ambulance);

    boolean checkAmbulance(Ambulance ambulance);

    String forgotPassword(String email);

    String resetPassword(String token, String password);

    User getUserDataByToken(String token);

    List<User> getPatientList();

    List<User> getTodayPatientList();

    User getPatientById(Long id);

    List<User> getDoctorList();

    User getDoctorById(Long id);

    List<Orders> getOrdersList(Long id);

    List<User> getPatientListById(Long id);

    User findUserByPhoneNumber(String phoneNumber);
}
