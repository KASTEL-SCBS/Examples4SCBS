package edu.kit.informatik.pcc.service.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographicHasher implements CryptographicHashing {

	
    public String hash(String input, byte[] salt) {
    	if (salt == null || input == null || input.isEmpty()) {
            return null;
        }
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(input.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
