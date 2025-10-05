package com.healthcare.healingxpert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.model.Dependent;
import com.healthcare.healingxpert.repository.DependentRepository;

@Service
@Transactional
public class DependentServiceImpl implements DependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Override
    public Dependent saveDependent(Dependent dependent) {
        return dependentRepository.save(dependent);
    }
}
