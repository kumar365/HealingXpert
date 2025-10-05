package com.healthcare.healingxpert.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.healthcare.healingxpert.dto.LocalUser;
import com.healthcare.healingxpert.dto.SignUpRequest;
import com.healthcare.healingxpert.enums.ERole;
import com.healthcare.healingxpert.enums.SocialProvider;
import com.healthcare.healingxpert.exception.*;
import com.healthcare.healingxpert.model.*;
import com.healthcare.healingxpert.repository.*;
import com.healthcare.healingxpert.security.oauth2.user.OAuth2UserInfo;
import com.healthcare.healingxpert.security.oauth2.user.OAuth2UserInfoFactory;
import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.util.DateUtils;
import com.healthcare.healingxpert.util.GeneralUtils;
import com.healthcare.healingxpert.constants.CommonConstants;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final int EXPIRE_TOKEN_AFTER_MINUTES = 30;

    private final UserRepository userRepository;
    private final DependentRepository dependentRepository;
    private final MedicalDetailsRepository medicalDetailsRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final OrdersRepository ordersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AmbulanceRepository ambulanceRepository;

    @Override
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
            OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (org.springframework.util.StringUtils.hasLength(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (org.springframework.util.StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId)
                    && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your "
                                + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }
        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId())
                .addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public void updateUser(User user) {
        if (null != user.getDoctorDetails()) {
            if (null != user.getDoctorDetails().getDoctorExperiences()) {
                for (DoctorExperience doctorExperience : user.getDoctorDetails().getDoctorExperiences()) {
                    doctorExperience.setExperienceData(
                            DateUtils.yearDifference(doctorExperience.getFromYear(), doctorExperience.getToYear()));
                }
            }
            if (null != user.getDoctorDetails().getDoctorSlots()) {
                for (DoctorSlot doctorSlot : user.getDoctorDetails().getDoctorSlots()) {
                    doctorSlot.setDoctorDetails(user.getDoctorDetails());
                    doctorSlot.setSlotStart(Timestamp.valueOf("2024-07-01 09:00:00"));
                    doctorSlot.setSlotEnd(Timestamp.valueOf("2024-07-01 19:00:00"));
                }
            }
        }
        userRepository.save(user);
    }

    @Override
    public Dependent saveDependent(Dependent dependent) throws DependentAlreadyExistException {
        dependent.setVersion(AppUtils.VERSION);
        dependent.setCreatedBy(AppUtils.getName());
        dependent.setCreatedDate(AppUtils.getTimestamp());
        return dependentRepository.save(dependent);
    }

    @Override
    public List<Dependent> getDependents(Long userId) {
        return dependentRepository.findDependentSByUserId(userId);
    }

    @Override
    public MedicalDetails saveMedicalDetails(MedicalDetails medicalDetails) throws MedicalDetailsAlreadyExistException {
        medicalDetails.setVersion(AppUtils.VERSION);
        medicalDetails.setCreatedBy(AppUtils.getName());
        medicalDetails.setCreatedDate(AppUtils.getTimestamp());
        return medicalDetailsRepository.save(medicalDetails);
    }

    @Override
    public List<MedicalDetails> getMedicalDetails(Long userId) {
        return medicalDetailsRepository.findMedicalDetailsByUserId(userId);
    }

    @Override
    public void deleteMedicalDetails(Long id) {
        medicalDetailsRepository.deleteById(id);
    }

    @Override
    public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) throws MedicalRecordsAlreadyExistException {
        medicalRecords.setVersion(AppUtils.VERSION);
        medicalRecords.setCreatedBy(AppUtils.getName());
        medicalRecords.setCreatedDate(AppUtils.getTimestamp());
        return medicalRecordsRepository.save(medicalRecords);
    }

    @Override
    public List<MedicalRecords> getMedicalRecords(Long userId) {
        return medicalRecordsRepository.findMedicalRecordsByUserId(userId);
    }

    @Override
    public void deleteMedicalRecords(Long id) {
        medicalRecordsRepository.deleteById(id);
    }

    @Override
    public User registerNewPatient(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Patient with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Patient with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest, ERole.ROLE_PATIENT);
        user.setUserType(CommonConstants.PATIENT);
        user.setUsername(signUpRequest.getDisplayName());
        user.setVersion(AppUtils.VERSION);
        user.setLastLogin(AppUtils.getTimestamp());
        user.setCreatedBy(AppUtils.getName());
        user.setCreatedDate(AppUtils.getTimestamp());
        return userRepository.save(user);
    }

    @Override
    public User registerNewDoctor(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Doctor with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Doctor with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest, ERole.ROLE_DOCTOR);
        user.setUserType(CommonConstants.DOCTOR);
        user.setUsername(signUpRequest.getDisplayName());
        user.setVersion(AppUtils.VERSION);
        user.setLastLogin(AppUtils.getTimestamp());
        user.setCreatedBy(AppUtils.getName());
        user.setCreatedDate(AppUtils.getTimestamp());
        return userRepository.save(user);
    }

    @Override
    public User registerNewStaff(@Valid SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Staff with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException(
                    "Staff with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest, ERole.ROLE_STAFF);
        user.setUserType(CommonConstants.STAFF);
        user.setUsername(signUpRequest.getDisplayName());
        user.setVersion(AppUtils.VERSION);
        user.setLastLogin(AppUtils.getTimestamp());
        user.setCreatedBy(AppUtils.getName());
        user.setCreatedDate(AppUtils.getTimestamp());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        List<Object[]> users = userRepository.getUsers();
        for (Object[] value : users) {
            User user = new User();
            if (value[0] != null) {
                user.setId(Long.parseLong(value[0].toString()));
            }
            if (value[1] != null) {
                user.setDisplayName(value[1].toString());
            }
            if (value[2] != null) {
                user.setEmail(value[2].toString());
            }
            if (value[3] != null) {
                user.setUserType(value[3].toString());
            }
            if (value[4] != null) {
                user.setPhoneNumber(value[4].toString());
            }
            userList.add(user);
        }
        userList.sort(Comparator.comparing(User::getId));
        return userList;
    }

    @Override
    public void registerAmbulance(Ambulance ambulance) {
        ambulance.setVersion(AppUtils.VERSION);
        ambulance.setCreatedBy(AppUtils.getName());
        ambulance.setCreatedDate(AppUtils.getTimestamp());
        ambulanceRepository.save(ambulance);
    }

    @Override
    public boolean checkAmbulance(Ambulance ambulance) {
        List<Ambulance> ambulanceList = ambulanceRepository
                .getAmbulanceByMobileNumberPassword(ambulance.getMobileNumber(), ambulance.getPassword());
        return !ambulanceList.isEmpty();
    }

    @Override
    public String forgotPassword(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if (userOptional.isEmpty()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());
        user = userRepository.save(user);

        return user.getToken();
    }

    @Override
    public String resetPassword(String token, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByToken(token));

        if (userOptional.isEmpty()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }
        User user = userOptional.get();
        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);
        userRepository.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        return UUID.randomUUID() + String.valueOf(UUID.randomUUID());
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

    @Override
    public User getUserDataByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public List<User> getPatientList() {
        return userRepository.getUsersByUserType(CommonConstants.PATIENT);
    }

    @Override
    public List<User> getTodayPatientList() {
        return userRepository.getUsersByUserTypeAndDate(CommonConstants.PATIENT, DateUtils.getSqlDate());
    }

    @Override
    public User getPatientById(Long id) {
        return userRepository.findUserByIdAandUserType(id, CommonConstants.PATIENT);
    }

    @Override
    public List<User> getDoctorList() {
        return userRepository.getUsersByUserType(CommonConstants.DOCTOR);
    }

    @Override
    public User getDoctorById(Long id) {
        return userRepository.findUserByIdAandUserType(id, CommonConstants.DOCTOR);
    }

    @Override
    public List<Orders> getOrdersList(Long id) {
        return ordersRepository.findOrdersByUserId(id);
    }

    @Override
    public List<User> getPatientListById(Long id) {
        return userRepository.findPatientListById(id, CommonConstants.PATIENT);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException(
                    "User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException(
                    "User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest, ERole.ROLE_USER);
        user.setUsername(signUpRequest.getDisplayName());
        user.setVersion(AppUtils.VERSION);
        user.setLastLogin(AppUtils.getTimestamp());
        user.setCreatedBy(AppUtils.getName());
        user.setCreatedDate(AppUtils.getTimestamp());
        return userRepository.save(user);
    }

    private User buildUser(final SignUpRequest signUpRequest, ERole eRole) {
        User user = new User();
        user.setDisplayName(signUpRequest.getDisplayName());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        final HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(eRole));
        user.setRoles(roles);

        user.setProvider(signUpRequest.getSocialProvider() != null ?
            signUpRequest.getSocialProvider().getProviderType() :
            SocialProvider.LOCAL.getProviderType());
        user.setEnabled(true);
        user.setProviderUserId(signUpRequest.getProviderUserId());
        return user;
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
            .orElse(null);
    }

}
