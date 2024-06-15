package com.healthcare.HealingXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.Util.AppUtils;
import com.healthcare.HealingXpert.model.Ambulance;
import com.healthcare.HealingXpert.repository.AmbulanceRepository;

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

	public Ambulance getAmbulanceDetailsById(Long id) {
		return ambulanceRepository.findById(id).get();
	}

}
