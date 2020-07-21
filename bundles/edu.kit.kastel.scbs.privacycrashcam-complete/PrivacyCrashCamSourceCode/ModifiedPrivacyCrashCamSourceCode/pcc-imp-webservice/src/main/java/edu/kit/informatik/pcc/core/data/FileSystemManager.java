package edu.kit.informatik.pcc.core.data;

import java.io.File;
import java.util.logging.Logger;

public class FileSystemManager extends AFileManager {
	private String containerName;
	
	/**
	 * Creates a new file system manager managing files in the provided container.
	 * All files and directory paths are resolved against the provided container as root directory.
	 * @param containerName The name of the container which this manager should manage. Must be some valid file path.
	 */
	public FileSystemManager(String containerName) {
		this.containerName = containerName;
		File container = new File(containerName);
		if (!container.exists()) {
			createDirectory(null);
		}
	}
	
	public String getContainerName() {
		return containerName;
	}
	
	@Override
	public File getOrCreateFile(String name, String directoryPath) {
		File file = new File(fullPath(name, directoryPath));
		if (!file.exists()) {
			try {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			catch (Exception e) {
				Logger.getGlobal().warning("Failed to create file " + file.getAbsolutePath());
			}
		}
		return file;
	}

	@Override
	public File getExistingFile(String name, String directoryPath) {
		File file = new File(fullPath(name, directoryPath));
		if (file.exists()) {
			return file;
		}
		return null;
	}

	@Override
	public void createDirectory(String directoryPath) {
		new File(combinedPath(directoryPath, containerName)).mkdirs();		
	}

	@Override
	public File[] getAllFilesInDirectory(String directoryPath) {
		File[] result = new File(combinedPath(directoryPath, containerName)).listFiles();
		if (result == null) {
			return new File[0];
		}
		return result;
	}
	
	private String combinedPath(String fileName, String directoryPath) {
		if (directoryPath == null) {
			return fileName;
		}
		if (fileName == null) {
			return directoryPath;
		}
		if (directoryPath.endsWith(File.separator) || fileName.startsWith(File.separator)) {
			return directoryPath + fileName;
		}
		return directoryPath + File.separator + fileName;
	}
	
	private String fullPath(String fileName, String directoryPath) {
		return combinedPath(combinedPath(fileName, directoryPath), containerName);
	}
}
