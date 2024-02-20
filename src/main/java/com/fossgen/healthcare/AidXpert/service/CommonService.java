package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;

public interface CommonService {

	List<Country> getCountries();

	List<State> getStates(int countryId);

	List<City> getCities(int stateId);

}
