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

    @Query("FROM Candidate c WHERE c.id.company.id = :pCompanyId AND c.id.user.id = :pUserId AND c.id.acceleration.id = :pAccelerationId")
    Optional<Candidate> findComParametrosId(@Param("pUserId") Long userId,@Param("pCompanyId") Long companyId,@Param("pAccelerationId") Long accelerationId);

    @Query("FROM Candidate c WHERE c.id.company.id = :pCompanyId")
    List<Candidate> findByCompanyId(@Param("pCompanyId") Long companyId);

    @Query("FROM Candidate c WHERE c.id.acceleration.id = :pAccelerationId")
    List<Candidate> findByAccelerationId(@Param("pAccelerationId") Long accelerationId);

}
