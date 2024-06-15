package com.healthcare.HealingXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.Util.AppUtils;
import com.healthcare.HealingXpert.model.City;
import com.healthcare.HealingXpert.model.Country;
import com.healthcare.HealingXpert.model.State;
import com.healthcare.HealingXpert.model.TestDetails;
import com.healthcare.HealingXpert.repository.CityRepository;
import com.healthcare.HealingXpert.repository.CountryRepository;
import com.healthcare.HealingXpert.repository.StateRepository;
import com.healthcare.HealingXpert.repository.TestDetailsRepository;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	private TestDetailsRepository testDetailsRepository;

	@Override
	public List<Country> getCountries() {
		return countryRepository.findAll();
	}

	@Override
	public List<State> getStates() {
		return stateRepository.findAll();
	}

	@Override
	public List<State> getStates(int countryId) {
		return stateRepository.findStatesByCountryId(countryId);
	}

	@Override
	public List<City> getCities() {
		return cityRepository.findAll();
	}

	@Override
	public List<City> getCities(int stateId) {
		return cityRepository.findCitiesByStateId(stateId);
	}

	@Override
	public TestDetails saveTestDetails(TestDetails testDetails) {
		testDetails.setVersion(AppUtils.VERSION);
		testDetails.setCreatedBy(AppUtils.getName());
		testDetails.setCreatedDate(AppUtils.getTimestamp());
		testDetailsRepository.save(testDetails);
		return testDetails;
	}

	@Override
	public TestDetails getTestDetailsById(Integer id) {
		return testDetailsRepository.getTestDetailsByTestId(id);
	}

	@Override
	public List<TestDetails> getTestDetailsList() {
		return testDetailsRepository.findAll();
	}

	@Override
	public void deleteTestDetailsById(int id) {
		testDetailsRepository.deleteById(id);
	}
}
