package com.healthcare.HealingXpert.service;

import java.util.List;

import com.healthcare.HealingXpert.model.City;
import com.healthcare.HealingXpert.model.Country;
import com.healthcare.HealingXpert.model.State;
import com.healthcare.HealingXpert.model.TestDetails;

public interface CommonService {

	List<Country> getCountries();

	List<State> getStates();

	List<State> getStates(int countryId);

	List<City> getCities();

	List<City> getCities(int stateId);

	TestDetails saveTestDetails(TestDetails testDetails);

	public TestDetails getTestDetailsById(Integer id);

	List<TestDetails> getTestDetailsList();

	void deleteTestDetailsById(int id);

}
