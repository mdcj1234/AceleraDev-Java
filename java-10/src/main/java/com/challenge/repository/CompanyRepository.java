package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Override
    <S extends Company> S saveAndFlush(S s);

    @Override
    Optional<Company> findById(Long aLong);

    @Query("SELECT c FROM Company cp, Candidate c WHERE cp.candidates IN" +
            "(SELECT c FROM Candidate c, Acceleration a WHERE c.id.acceleration = a AND a.id = :accelerationId)")
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query("SELECT c FROM Company cp, Candidate c WHERE cp.candidates IN" +
            "(SELECT c FROM Candidate c, User u WHERE c.id.user = u AND u.id = :userId)")
    List<Company> findByUserId(@Param("userId") Long userId);

}
