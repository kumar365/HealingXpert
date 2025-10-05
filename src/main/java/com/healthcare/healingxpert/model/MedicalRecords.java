package com.healthcare.healingxpert.model;

import java.sql.Date;
import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medical_records")
public class MedicalRecords extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Date recordDate;
    private String recordDateString;
    private String recordDetails;
    private String mrId;

    @Lob
    @Column(name = "attachment_file")
    private byte[] attachmentFile;

    private String attachment;

    @Transient
    private MultipartFile attachmentMultipartFile;
}
