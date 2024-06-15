package com.healthcare.HealingXpert.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class FileUtils {
	public static void createDirectory(String directoryName) {
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	public static void writeFile(String filePath, byte[] bytes) {
		Path path = Paths.get(filePath);
		try {
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
