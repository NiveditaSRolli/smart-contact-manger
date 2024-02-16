package com.example.demo.helper;

public class Message {

	private String content;
	private String type;


	public Message(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Message [content=" + content + ", type=" + type + "]";
	}
	
	
}
