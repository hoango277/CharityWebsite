package com.javaweb.entity;

import lombok.*;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="charity_programs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharityProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charity_program_id")
    private Long charityProgramId;

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String address;
    private Long amountNeeded;
    private Long totalAmount;
    private String image;

    @OneToMany(mappedBy = "charityProgram",cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private Set<VolunteerEntity> volunteers = new HashSet<>();
}
