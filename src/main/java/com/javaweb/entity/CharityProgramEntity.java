package com.javaweb.entity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "address")
    private String address;

    @Column(name = "amount_needed")
    @Min(1)
    private Long amountNeeded;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "charityProgram",cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private Set<VolunteerEntity> volunteers = new HashSet<>();
}
