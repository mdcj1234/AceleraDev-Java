package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {

    @Query("SELECT max(s.score) FROM Submission s WHERE s.id.challenge.id = :pChallengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("pChallengeId") Long challengeId);

    @Query("FROM Submission s JOIN s.id.challenge.accelerations a " +
            "WHERE s.id.challenge.id = :pChallengeId AND a.id = :pAccelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(@Param("pChallengeId") Long challengeId,
                                                        @Param("pAccelerationId") Long accelerationId);
}
