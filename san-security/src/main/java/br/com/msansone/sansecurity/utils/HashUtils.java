package br.com.msansone.sansecurity.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public  class HashUtils {
	
	public static String createHash(String pass) {
		
		String hashedPassword=null;
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = pass.getBytes();
            byte[] hashedPasswordBytes = md.digest(passwordBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                sb.append(String.format("%02x", b));
            }
            hashedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return hashedPassword;
	}
	
	public static boolean validatePass(String pass, String hash) {
        MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        byte[] passBytes = md.digest(pass.getBytes());
        
        StringBuilder sb = new StringBuilder();
        for (byte b : passBytes) {
        	sb.append(String.format("%02x",b));
        }
        String enterHash = sb.toString();
        
        return enterHash.equals(hash);
	}
        

}
