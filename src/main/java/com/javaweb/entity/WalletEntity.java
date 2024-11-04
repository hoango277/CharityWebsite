package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    private Long totalAmount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
