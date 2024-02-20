package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fossgen.healthcare.AidXpert.constants.FileErrors;
import com.fossgen.healthcare.AidXpert.exception.FileNotFoundException;
import com.fossgen.healthcare.AidXpert.exception.FileSaveException;
import com.fossgen.healthcare.AidXpert.model.FileModel;
import com.fossgen.healthcare.AidXpert.repository.FileRepo;

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