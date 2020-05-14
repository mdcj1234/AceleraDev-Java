package com.challenge.repository;

import com.challenge.entity.User;
import com.challenge.repository.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends RepositoryInterface<User>, JpaRepository<User, Long> {

    @Query("FROM User u JOIN u.candidates c WHERE c.id.acceleration.name LIKE :pName")
    List<User> findByAccelerationName(@Param("pName") String name);

    @Query("FROM User u JOIN u.candidates c WHERE c.id.company.id = :pCompanyId")
    List<User> findByCompanyId(@Param("pCompanyId") Long companyId);
}
