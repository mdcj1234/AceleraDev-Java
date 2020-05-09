package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Override
    <S extends Challenge> S saveAndFlush(S s);

    @Query("SELECT ch FROM Challenge ch, Acceleration a WHERE a.challenge = ch AND a.id = :accelerationId AND a.candidates IN" +
            "(SELECT c FROM Candidate c, User u WHERE c.id.user = u AND u.id = :userId)")
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,@Param("userId") Long userId);
}
