package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.model.Ambulance;
import com.fossgen.healthcare.AidXpert.repository.AmbulanceRepository;

@Service
@Transactional
public class AmbulanceService {

	@Autowired
	AmbulanceRepository ambulanceRepository;

	public Ambulance saveAmbulance(Ambulance ambulance) {
		ambulance.setVersion(AppUtils.VERSION);
		ambulance.setCreatedBy(AppUtils.getName());
		ambulance.setCreatedDate(AppUtils.getTimestamp());
		return ambulanceRepository.save(ambulance);
	}

	public List<Ambulance> getAmbulances() {
		return ambulanceRepository.findAll();
	}

}
