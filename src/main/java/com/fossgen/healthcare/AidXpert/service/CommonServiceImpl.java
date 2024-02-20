package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;
import com.fossgen.healthcare.AidXpert.repository.CityRepository;
import com.fossgen.healthcare.AidXpert.repository.CountryRepository;
import com.fossgen.healthcare.AidXpert.repository.StateRepository;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Override
	public List<Country> getCountries() {
		return countryRepository.findAll();
	}

	@Override
	public List<State> getStates(int countryId) {
		return stateRepository.findStatesByCountryId(countryId);
	}

	@Override
	public List<City> getCities(int stateId) {
		return cityRepository.findCitiesByStateId(stateId);
	}

}
