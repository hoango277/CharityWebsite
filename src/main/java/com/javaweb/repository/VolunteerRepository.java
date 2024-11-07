package com.javaweb.repository;

import com.javaweb.entity.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
    @Query("SELECT v FROM VolunteerEntity v WHERE v.charityProgram.charityProgramId = :charityProgramID")
    List<VolunteerEntity> findVolunteersByCharityProgramID(Long charityProgramID);
}
