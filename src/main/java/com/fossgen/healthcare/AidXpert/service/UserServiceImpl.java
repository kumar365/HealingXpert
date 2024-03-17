package com.fossgen.healthcare.AidXpert.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.Util.GeneralUtils;
import com.fossgen.healthcare.AidXpert.constants.CommonConstants;
import com.fossgen.healthcare.AidXpert.dto.LocalUser;
import com.fossgen.healthcare.AidXpert.dto.SignUpRequest;
import com.fossgen.healthcare.AidXpert.enums.ERole;
import com.fossgen.healthcare.AidXpert.enums.SocialProvider;
import com.fossgen.healthcare.AidXpert.exception.DependentAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalDetailsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalRecordsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.OAuth2AuthenticationProcessingException;
import com.fossgen.healthcare.AidXpert.exception.UserAlreadyExistAuthenticationException;
import com.fossgen.healthcare.AidXpert.model.Ambulance;
import com.fossgen.healthcare.AidXpert.model.Dependent;
import com.fossgen.healthcare.AidXpert.model.MedicalDetails;
import com.fossgen.healthcare.AidXpert.model.MedicalRecords;
import com.fossgen.healthcare.AidXpert.model.Role;
import com.fossgen.healthcare.AidXpert.model.User;
import com.fossgen.healthcare.AidXpert.repository.AmbulanceRepository;
import com.fossgen.healthcare.AidXpert.repository.DependentRepository;
import com.fossgen.healthcare.AidXpert.repository.MedicalDetailsRepository;
import com.fossgen.healthcare.AidXpert.repository.MedicalRecordsRepository;
import com.fossgen.healthcare.AidXpert.repository.RoleRepository;
import com.fossgen.healthcare.AidXpert.repository.UserRepository;
import com.fossgen.healthcare.AidXpert.security.oauth2.user.OAuth2UserInfo;
import com.fossgen.healthcare.AidXpert.security.oauth2.user.OAuth2UserInfoFactory;

/**
 * @author KUMAR
 * @since 01/11/23
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private DependentRepository dependentRepository;

	@Autowired
	private MedicalDetailsRepository medicalDetailsRepository;

	@Autowired
	private MedicalRecordsRepository medicalRecordsRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AmbulanceRepository ambulanceRepository;

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

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
		// user.setPhoneNumber("9096488159");
		user.setVersion(AppUtils.VERSION);
		user.setLostLogin(AppUtils.getTimestamp());
		user.setCreatedBy(AppUtils.getName());
		user.setCreatedDate(AppUtils.getTimestamp());
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	private User buildUser(final SignUpRequest signUpRequest, ERole eRole) {
		User user = new User();
		user.setDisplayName(signUpRequest.getDisplayName());
		user.setEmail(signUpRequest.getEmail());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		final HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName(eRole));
		user.setRoles(roles);
		if (null != signUpRequest.getSocialProvider()) {
			user.setProvider(signUpRequest.getSocialProvider().getProviderType());
		} else {
			user.setProvider(SocialProvider.LOCAL.getProviderType());
		}
		user.setEnabled(true);
		user.setProviderUserId(signUpRequest.getProviderUserId());
		return user;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByPhoneNumber(final String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public User findUserByPhoneNumberAndPassword(final String phoneNumber, final String password) {
		return userRepository.findByPhoneNumber(phoneNumber, password);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
			OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (null != user) {
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
		return userRepository.findById(id).get();
	}

	@Override
	public void updateUser(User user) {
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
		// user.setPhoneNumber("9096488159");
		user.setVersion(AppUtils.VERSION);
		user.setLostLogin(AppUtils.getTimestamp());
		user.setCreatedBy(AppUtils.getName());
		user.setCreatedDate(AppUtils.getTimestamp());
		user = userRepository.save(user);
		userRepository.flush();
		return user;
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
		user.setLostLogin(AppUtils.getTimestamp());
		user.setCreatedBy(AppUtils.getName());
		user.setCreatedDate(AppUtils.getTimestamp());
		user = userRepository.save(user);
		userRepository.flush();
		return user;
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
		// user.setPhoneNumber("9096488159");
		user.setVersion(AppUtils.VERSION);
		user.setLostLogin(AppUtils.getTimestamp());
		user.setCreatedBy(AppUtils.getName());
		user.setCreatedDate(AppUtils.getTimestamp());
		user = userRepository.save(user);
		userRepository.flush();
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> userList = new ArrayList<User>();
		List<Object[]> users = userRepository.getUsers();
		for (Object[] value : users) {
			User user = new User();
			if (null != value[0]) {
				user.setDisplayName(value[0].toString());
			}
			if (null != value[1]) {
				user.setEmail(value[1].toString());
			}
			if (null != value[2]) {
				user.setUserType(value[2].toString());
			}
			if (null != value[3]) {
				user.setId(Long.valueOf(value[3].toString()));
			}
			userList.add(user);
		}
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
		if (ambulanceList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String forgotPassword(String email) {
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

		if (!userOptional.isPresent()) {
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

		if (!userOptional.isPresent()) {
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
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
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
		return userRepository.getUserByUserType(CommonConstants.PATIENT);
	}

	@Override
	public User getPatientById(Long id) {
		return userRepository.findUserByIdAandUserType(id, CommonConstants.PATIENT);
	}
}