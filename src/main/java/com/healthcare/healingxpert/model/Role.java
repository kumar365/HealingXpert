package com.healthcare.healingxpert.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private com.healthcare.healingxpert.enums.ERole name;

    public Role() {
    }

    public Role(com.healthcare.healingxpert.enums.ERole name) {
        this.name = name;
    }
}
