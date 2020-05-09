package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long aLong);

    @Query("SELECT u FROM User u, Candidate c, Acceleration a WHERE c.id.user = u AND c.id.acceleration = a AND a.name = :name")
    List<User> findByAccelerationName(@Param("name") String name);

    @Query("SELECT u FROM User u, Candidate c, Company cp WHERE c.id.user = u AND c.id.company = cp AND cp.id = :id")
    List<User> findByCompanyId(@Param("id") Long companyId);
}
