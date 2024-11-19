package com.javaweb.repository;

import com.javaweb.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    @Query("SELECT t FROM TransactionEntity t where t.user.userId =:userID")
    Optional<List<TransactionEntity>> findByUserID(@Param("userID") Long userID);
    @Query("SELECT t FROM TransactionEntity t")
    Page<TransactionEntity> findAllTransaction(Pageable pageable);
}
