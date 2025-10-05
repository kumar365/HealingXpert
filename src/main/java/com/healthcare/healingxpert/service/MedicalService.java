package com.healthcare.healingxpert.service;

import com.healthcare.healingxpert.model.MedicalDetails;
import com.healthcare.healingxpert.model.MedicalRecords;

public interface MedicalService {
    MedicalDetails saveMedicalDetails(MedicalDetails medicalDetails);
    MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords);
}
