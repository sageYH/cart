package com.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {
	private static final String SECRET_KEY = "kicsSecret256$!";
	private static final String SALT = "keepPrivate256$!";

	public static String encOneWay(String strData) { //단방향 암호화
		String SHA = "";
	    try{
	        MessageDigest sh = MessageDigest.getInstance("SHA-256");
	        sh.update(strData.getBytes());
	        byte byteData[] = sh.digest();
	        StringBuffer sb = new StringBuffer();
	        for(int i = 0 ; i < byteData.length ; i++){
	            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	
	        }
	        SHA = sb.toString();
	        byte[] raw = SHA.getBytes();
		    byte[] encodedBytes = Base64.encodeBase64(raw);
		    SHA = new String(encodedBytes);
	    }catch(Exception e){
	        e.printStackTrace();
	        SHA = null;
	    }
	    return SHA;
    }

	public static String encrypt(String strToEncrypt) {	//양방향 암호화
		try {
			byte[] iv = {0, 1, 0, 3, 0, 5, 0, 7, 8, 9, 0, 1, 0, 5, 7, 9};
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			 
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return java.util.Base64.getEncoder()
			.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {//양방향 암호화
		try {
			byte[] iv = {0, 1, 0, 3, 0, 5, 0, 7, 8, 9, 0, 1, 0, 5, 7, 9};
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}
