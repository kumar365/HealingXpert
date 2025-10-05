package com.healthcare.healingxpert.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String displayName;
    private String email;
    private String phoneNumber;
    private boolean enabled;
    private String userType;
    private String provider;
    private String providerUserId;
    private String token;
    private LocalDateTime tokenCreationDate;
    private String gender;
    private Date dateOfBirth;
    private String firstName;
    private String age;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_data", columnDefinition = "BYTEA", nullable = true)
    private byte[] imageData = null;

    private String profileImageName;

    @Column(name = "date_of_birth_string")
    private String dateOfBirthString;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DoctorDetails doctorDetails;

    private Timestamp lastLogin;

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getLastLogin() {
        return this.lastLogin;
    }
}
