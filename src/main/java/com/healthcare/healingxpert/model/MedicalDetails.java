package com.healthcare.healingxpert.model;

import java.sql.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medical_details")
public class MedicalDetails extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Date orderDate;
    private String orderDateString;
    private String orderDetails;
}
