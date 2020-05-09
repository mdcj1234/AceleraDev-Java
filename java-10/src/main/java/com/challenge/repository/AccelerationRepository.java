package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    @Override
    <S extends Acceleration> S saveAndFlush(S s);

    @Override
    Optional<Acceleration> findById(Long id);

    @Query("SELECT a FROM Acceleration a, Candidate c WHERE a.candidates IN" +
            "(SELECT c FROM Candidate c, Company cp WHERE c.id.company = cp AND cp.id = :companyId)")
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

}
