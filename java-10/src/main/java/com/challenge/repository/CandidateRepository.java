package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {

    @Override
    <S extends Candidate> S saveAndFlush(S s);

    @Override
    Optional<Candidate> findById(CandidateId candidateId);

    @Query(value = "SELECT c FROM Candidate c, Company cp, Acceleration a, User u " +
            "WHERE c.id.user = u AND c.id.company = cp AND c.id.acceleration = a AND " +
            "u.id = :userId AND cp.id = :companyId AND a.id = :accelerationId")
    Candidate findById(@Param("userId") Long userId,
                       @Param("companyId") Long companyId,
                       @Param("accelerationId") Long accelerationId);

    @Query(value = "SELECT c FROM Candidate c, Company cp WHERE c.id.company = cp AND cp.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT c FROM Candidate c, Acceleration a WHERE c.id.acceleration = a AND a.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
