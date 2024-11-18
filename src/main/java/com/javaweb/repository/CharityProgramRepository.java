package com.javaweb.repository;

import com.javaweb.entity.CharityProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityProgramRepository extends JpaRepository<CharityProgramEntity,Long> {

    @Query("select c from CharityProgramEntity c " +
            "where (lower(c.name) like lower(concat('%', :keyword, '%')) or :keyword is null)")
    Page<CharityProgramEntity> findCharityProgramByKeyword(@Param(value = "keyword") String keyword, Pageable pageable);
}
