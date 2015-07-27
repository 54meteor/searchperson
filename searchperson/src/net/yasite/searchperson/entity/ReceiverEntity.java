package net.yasite.searchperson.entity;

import java.io.Serializable;

public class ReceiverEntity implements Serializable {
	private String title;
	private String content;
	private String customerContent;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCustomerContent() {
		return customerContent;
	}
	public void setCustomerContent(String customerContent) {
		this.customerContent = customerContent;
	}
	
}
