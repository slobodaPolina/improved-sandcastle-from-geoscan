package helpful;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
	private MessageDigest m;
	private byte digest[];
	private BigInteger bigint;

	public PasswordHasher(String algorythm) throws NoSuchAlgorithmException {
		m = MessageDigest.getInstance(algorythm);
		m.reset();
	}

	public String hash(String password) {
		m.update(password.getBytes());
		digest = m.digest();
		bigint = new BigInteger(digest);
		return bigint.toString();
	}
}
