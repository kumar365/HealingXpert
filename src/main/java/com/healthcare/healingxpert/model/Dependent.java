package com.healthcare.healingxpert.model;

import java.sql.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dependents")
public class Dependent extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String relationship;
    private Date dateOfBirth;
    private String dateOfBirthString;
    private String bloodGroup;
    private String gender;
    private String age;
}
