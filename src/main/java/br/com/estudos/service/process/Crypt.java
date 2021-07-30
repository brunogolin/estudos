package br.com.estudos.service.process;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypt {

	static String IV = "AAAAAAAAAAAAAAAA";

	private byte[] getKey() {
		String seckey = "0x5b, 0x63, 0x6f, 0x6d, 0x6d, 0x43, 0x65, 0x72, 0x74, 0x4d, 0x52, 0x53, 0x31, 0x34, 0x5d, 0x40";
		String[] arraySec = seckey.split(",");
		byte[] key = new byte[arraySec.length];
		for (int i = 0; i < arraySec.length; i++) {
			String output = arraySec[i].trim().substring(2);
			int decimal = Integer.parseInt(output, 16);
			key[i] = (byte) decimal;
		}
		return key;
	}

	public String encrypt(String strToEncrypt) {
		if ((null == strToEncrypt) || (strToEncrypt.equals("")))
			return null;
		try {

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(getKey(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			System.out.println("Problemas ao encriptar chave. Erro=[" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return null;

	}

	public String decrypt(String strToDecrypt) {
		if ((null == strToDecrypt) || (strToDecrypt.equals("")))
			return null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(getKey(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

			final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
			return decryptedString;
		} catch (Exception e) {
			System.out.println("Problemas ao encriptar chave. Erro=[" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return null;
	}

}
