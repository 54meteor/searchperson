package net.yasite.searchperson.handler;

import java.net.URLEncoder;

import net.yasite.net.HandlerHelp;
import net.yasite.searchperson.api.params.LocateRequestParams;
import net.yasite.searchperson.api.params.SendMessageParams;
import net.yasite.searchperson.api.params.Urls;
import net.yasite.searchperson.entity.LocateEntity;
import net.yasite.searchperson.service.SendMessageService;
import net.yasite.util.MD5Util;
import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;

public class SendMessageHandler extends HandlerHelp{
	private final static String secret = "042d5ca2a1d75a3d7fdee0f7c260918f";
	private final static String access_id = "2100087592";
	private String type = "2";
	private String token = "";
	private Context context;
	private LocateEntity custom;
	private String content = "";
	private String title = "";
	public SendMessageHandler(Context context, String token,
			LocateEntity custom) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.token = token;
		this.custom = custom;
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doTask(Message msg) throws Exception {
		// TODO Auto-generated method stub
		SendMessageService service = new SendMessageService(context);
		SendMessageParams pm = new SendMessageParams();
		pm.setAccess_id(access_id);
		pm.setMessage_type(type);
		pm.setSecret(secret);
		pm.setTime((System.currentTimeMillis() / 1000 ) + "");
		pm.setDevice_token(token);
		
		LocateRequestParams requestContent = new LocateRequestParams();
		requestContent.setContent(content);
//		LocateEntity custom = new LocateEntity();
//		custom.setLatitude("39.980484");
//		custom.setLongitude("116.311302");
//		custom.setFlag("0");
		requestContent.setCustomerContent(custom);
		requestContent.setTitle(title);
		
		pm.setContent(URLEncoder.encode(new Gson().toJson(requestContent),"UTF-8"));
		
		String sign = "GET" + Urls.HOST + Urls.PATH + Urls.ALL_DEVICE + "access_id=" + access_id
				+ "device_token=" + pm.getDevice_token()
				+ "message=" + new Gson().toJson(requestContent) 
				+ "message_type=2timestamp=" + pm.getTime() + secret;
		pm.setSign(MD5Util.md5(sign));
		service.sendMessaget(pm);
	}

	@Override
	public void doTaskAsNoNetWork(Message msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
