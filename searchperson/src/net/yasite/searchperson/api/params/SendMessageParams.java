package net.yasite.searchperson.api.params;

import net.yasite.util.BaseHttpParam;

public class SendMessageParams extends BaseHttpParam {
	private String secret;
	private String time;
	private String content;
	private String sign;
	private String access_id;
	private String message_type;
	private String device_token;
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getDevice_token() {
		return device_token;
	}
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}
	
	
}
