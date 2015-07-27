package net.yasite.searchperson.entity;

import java.io.Serializable;

public class LocateReceiverEntity implements Serializable {
	private String title;
	private String content;
	private LocateEntity customContent;
	
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
	public LocateEntity getCustomerContent() {
		return customContent;
	}
	public void setCustomerContent(LocateEntity customerContent) {
		this.customContent = customerContent;
	}
	
}
