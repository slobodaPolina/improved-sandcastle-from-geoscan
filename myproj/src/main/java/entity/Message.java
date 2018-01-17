package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	private int id;
	private String receiver;
	private String sender;
	private String text;
	private Date time;

	public Message() {
		this.sender = "default";
		this.text = "default";
		this.receiver = "default";
		this.time = new Date();
	}

	public Message(String receiver, String sender, String text) {
		this.sender = sender;
		this.text = text;
		this.receiver = receiver;
		this.time = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
