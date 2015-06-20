package traffic_web.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public final class Encryptor {

	private static Encryptor instance;

	private Encryptor() {
		
	}

	public synchronized String encrypt(String plaintext) throws Exception {
		
		String hash = null;
		try {
			// Both SHA and SHA-1 refer to the same thing, a revised SHA algorithm.
			MessageDigest md = MessageDigest.getInstance("SHA");
			// Convert the plain text password into a byte-representation using UTF-8 encoding format.
			md.update(plaintext.getBytes("UTF-8"));
			byte raw[] = md.digest();
			// Create a String representation of the byte array representing the digested password value.
			hash = Base64.encodeBase64String(raw).trim();		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return hash;
	}

	public static synchronized Encryptor getInstance(){
		return (instance == null) ? new Encryptor() : instance;		
	}
}
