package com.javaweb.repository;

import com.javaweb.entity.VolunteerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
    @Query("SELECT v FROM VolunteerEntity v WHERE v.charityProgram.charityProgramId = :charityProgramID")
    Page<VolunteerEntity> findVolunteersByCharityProgramID(Long charityProgramID, Pageable pageable);
}
