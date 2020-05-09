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

    @Override
    <S extends Submission> S saveAndFlush(S s);

    @Query("SELECT MAX(s.score) FROM Submission s, Challenge c WHERE s.id.challenge = c AND c.id = :challengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query("SELECT s FROM Submission s, Challenge c, Acceleration a  " +
            "WHERE s.id.challenge = c AND c.id = :challengeId AND c.accelerations IN " +
            "(SELECT a FROM Acceleration WHERE a.id = :accelerationId)")
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);

}
