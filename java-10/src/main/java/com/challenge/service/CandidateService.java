package com.challenge.service;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.repository.CandidateRepository;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements CandidateServiceInterface {

    @Autowired
    CandidateRepository candidateRepostory;

    @Override
    public Optional<Candidate> findById(CandidateId id) { return candidateRepostory.findById(id); }

    @Override
    public Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId) {
        return Optional.ofNullable(candidateRepostory.findById(userId,companyId,accelerationId));
    }

    @Override
    public List<Candidate> findByCompanyId(Long companyId) {
        return candidateRepostory.findByCompanyId(companyId);
    }

    @Override
    public List<Candidate> findByAccelerationId(Long accelerationId) {
        return candidateRepostory.findByAccelerationId(accelerationId);
    }

    @Override
    public Candidate save(Candidate object) {
        return candidateRepostory.saveAndFlush(object);
    }
}
