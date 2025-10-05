package com.healthcare.healingxpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.model.MedicalDetails;
import com.healthcare.healingxpert.model.MedicalRecords;
import com.healthcare.healingxpert.repository.MedicalDetailsRepository;
import com.healthcare.healingxpert.repository.MedicalRecordsRepository;

@Service
@Transactional
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    private MedicalDetailsRepository medicalDetailsRepository;

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Override
    public MedicalDetails saveMedicalDetails(MedicalDetails medicalDetails) {
        return medicalDetailsRepository.save(medicalDetails);
    }

    @Override
    public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) {
        return medicalRecordsRepository.save(medicalRecords);
    }
}
