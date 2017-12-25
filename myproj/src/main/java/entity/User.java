package entity;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String login;
	private String email;
	private String password;
	private int code;
	private int confirmed;

	public User(String name, String email, String password) {
		this.login = name;
		this.email = email;
		this.password = password;
		Random r = new Random();
		this.code = r.nextInt();
		this.confirmed = 0;
	}

	public User() {// need this default constructor because it failed without
		this.login = "default";
		this.email = "default";
		this.password = "default";
		this.code = 0;
		this.confirmed = 0;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
}
