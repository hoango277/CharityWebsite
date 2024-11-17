package com.javaweb.repository;

import com.javaweb.entity.CharityProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityProgramRepository extends JpaRepository<CharityProgramEntity,Long> {

}
