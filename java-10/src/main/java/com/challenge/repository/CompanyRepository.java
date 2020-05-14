package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT DISTINCT c FROM Company c JOIN c.candidates ca " +
            "WHERE ca.id.acceleration.id = :pAccelerationId")
    List<Company> findByAccelerationId(@Param("pAccelerationId") Long accelerationId);

    @Query("SELECT DISTINCT c FROM Company c JOIN c.candidates ca WHERE ca.id.user.id = :pUserId")
    List<Company> findByUserId(@Param("pUserId") Long userId);
}
