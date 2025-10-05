package com.healthcare.healingxpert.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor_details")
public class DoctorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;
    private String qualification;
    private String description;
    private Double experience;
    private Double consultationFee;
    private String version;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;

    @OneToMany(mappedBy = "doctorDetails", cascade = CascadeType.ALL)
    private List<DoctorExperience> doctorExperiences;

    @OneToMany(mappedBy = "doctorDetails", cascade = CascadeType.ALL)
    private List<DoctorSlot> doctorSlots;
}
