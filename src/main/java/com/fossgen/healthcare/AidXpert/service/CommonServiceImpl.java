package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;
import com.fossgen.healthcare.AidXpert.model.TestDetails;
import com.fossgen.healthcare.AidXpert.repository.CityRepository;
import com.fossgen.healthcare.AidXpert.repository.CountryRepository;
import com.fossgen.healthcare.AidXpert.repository.StateRepository;
import com.fossgen.healthcare.AidXpert.repository.TestDetailsRepository;

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
