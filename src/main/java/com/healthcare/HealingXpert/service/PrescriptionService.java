package com.healthcare.HealingXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.HealingXpert.Util.AppUtils;
import com.healthcare.HealingXpert.model.Prescription;
import com.healthcare.HealingXpert.repository.PrescriptionRepository;

@Service
@Transactional
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	public Prescription save(Prescription prescription) {
		prescription.setPatientName(AppUtils.getName());
		prescription.setVersion(AppUtils.VERSION);
		prescription.setCreatedBy(AppUtils.getName());
		prescription.setCreatedDate(AppUtils.getTimestamp());
		return prescriptionRepository.save(prescription);
	}

	public List<Prescription> findByPatientName(String patientName) {
		return prescriptionRepository.findByPatientName(patientName);
	}
}
