package com.healthcare.healingxpert.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.healthcare.healingxpert.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String displayName;
    private String email;
    private String phoneNumber;
    private Set<Role> roles;
    private boolean enabled;
    private String gender;
    private Date dateOfBirth;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String ipAddress;
    private String newPassword;
}
