package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
@Service
public class PasswordHasher {
	private MessageDigest m;
	private byte digest[];
	private BigInteger bigint;

	public String hash(String password, String algorythm) throws NoSuchAlgorithmException {
		m = MessageDigest.getInstance(algorythm);
		m.reset();
		m.update(password.getBytes());
		digest = m.digest();
		bigint = new BigInteger(digest);
		return bigint.toString();
	}
}
