package net.yasite.searchperson.service;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import com.google.gson.Gson;

import net.yasite.searchperson.api.SendMessageAPI;
import net.yasite.searchperson.api.params.SendMessageParams;
import net.yasite.util.BaseAPI;
import net.yasite.util.BaseService;
import android.content.Context;

public class SendMessageService extends BaseService{
	
	public SendMessageService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void sendMessaget(SendMessageParams pm){
		BaseAPI api = new SendMessageAPI(context, pm);
		try {
			if(api.doGet()){
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
