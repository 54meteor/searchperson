package net.yasite.searchperson.api.params;

import net.yasite.searchperson.entity.LocateEntity;
import net.yasite.util.BaseHttpParam;

public class LocateRequestParams extends BaseHttpParam {
	private String title;
	private String content;
	private LocateEntity custom_content;
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
		return custom_content;
	}
	public void setCustomerContent(LocateEntity customerContent) {
		this.custom_content = customerContent;
	}
}
