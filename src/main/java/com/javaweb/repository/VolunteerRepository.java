package com.javaweb.repository;

import com.javaweb.entity.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
}
