package com.javaweb.repository;

import com.javaweb.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    @Query("SELECT w FROM WalletEntity w where w.user.userId = :userID")
    WalletEntity findByUserID(@Param("userID") Long userID);
}
