package edu.kit.informatik.pcc.core.data;

import java.io.File;
import java.util.logging.Logger;

public abstract class AFileManager implements IFileHierarchyManager, IFileManager {

	@Override
	public File getOrCreateFile(String name) {
		return getOrCreateFile(name, null);
	}

	@Override
	public File getExistingFile(String name) {
		return getExistingFile(name, null);
	}

	@Override
	public void deleteFile(File file) {
		if (file == null) {
			return;
		}
		try {
			file.delete();
		}
		catch (SecurityException e) {
			Logger.getGlobal().warning("failed to delete file: " + file.getAbsolutePath());
		}
	}
}
