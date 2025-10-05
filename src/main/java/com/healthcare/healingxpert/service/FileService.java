package com.healthcare.healingxpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.healingxpert.constants.FileErrors;
import com.healthcare.healingxpert.exception.FileNotFoundException;
import com.healthcare.healingxpert.exception.FileSaveException;
import com.healthcare.healingxpert.model.FileModel;
import com.healthcare.healingxpert.repository.FileRepo;

@Service
@Transactional
public class FileService {

	@Autowired
	FileRepo fileRepo;

	public FileModel saveFile(MultipartFile file) {

		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (filename.contains("...")) {
				throw new FileSaveException(FileErrors.INVALID_FILE + filename);
			}

			FileModel model = new FileModel(filename, file.getContentType(), file.getBytes());
			return fileRepo.save(model);

		} catch (Exception e) {

			throw new FileSaveException(FileErrors.FILE_NOT_STORED, e);
		}

	}

	public FileModel getFile(String fileId) throws FileNotFoundException {

		return fileRepo.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException(FileErrors.FILE_NOT_FOUND + fileId));
	}

	public List<FileModel> getListOfFiles() {

		return fileRepo.findAll();
	}

}