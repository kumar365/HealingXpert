package com.fossgen.healthcare.AidXpert.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author KUMAR
 */
@Entity
@Table(name = "file_upload_download")
public class FileModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")

	private String fileId;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] fileData;

	public FileModel() {

	}

	public FileModel(String fileName, String fileType) {
		this.fileName = fileName;
		this.fileType = fileType;

	}

	public FileModel(String fileName, String fileType, byte[] fileData) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
}
