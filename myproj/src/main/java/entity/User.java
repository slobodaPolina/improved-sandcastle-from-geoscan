package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "login")
	private String login;

	private String email;

	private String password;

	public User(String name, String email, String password) {
		this.login = name;
		this.email = email;
		this.password = password;
	}

	public User() {//need this default constructor because it failed without
		this.login = "default";
		this.email = "default";
		this.password = "default";
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

}
