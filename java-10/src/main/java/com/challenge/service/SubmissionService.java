package com.challenge.service;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService implements SubmissionServiceInterface {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        return submissionRepository.findHigherScoreByChallengeId(challengeId)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return submissionRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

    @Override
    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }
}