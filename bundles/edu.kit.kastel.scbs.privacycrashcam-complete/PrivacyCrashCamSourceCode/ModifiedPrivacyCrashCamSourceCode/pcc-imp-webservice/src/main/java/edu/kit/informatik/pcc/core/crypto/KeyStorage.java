package edu.kit.informatik.pcc.core.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.logging.Logger;

import edu.kit.informatik.pcc.core.data.IFileManager;

public class KeyStorage implements IKeyStorage {
	private IFileManager fileManager;
	
	public KeyStorage(IFileManager fileManager) {
		this.fileManager = fileManager;
	}
	
	public void setFileManager(IFileManager fileManager) {
		assert this.fileManager == null;
		this.fileManager = fileManager;
	}

	@Override
	public void storeKey(String id, Key key) {
		assertCompletelySetup();
		File file = fileManager.getOrCreateFile(id);
		try (
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(key);
		}
		catch (IOException e) {
			e.printStackTrace();
			Logger.getGlobal().warning("failed to serialize key to file: " + file.getAbsolutePath() + "\n" + e.getLocalizedMessage());
		}
	}

	@Override
	public Key loadKey(String id) {
		assertCompletelySetup();
		File file = fileManager.getExistingFile(id);
		if (file == null) {
			return null;
		}
		try (
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Key)ois.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			Logger.getGlobal().warning("failed to deserialize key from file: " + file.getAbsolutePath() + "\n" + e.getLocalizedMessage());
		}
		return null;
	}
	
	private void assertCompletelySetup() {
		assert fileManager != null;
	}
}
