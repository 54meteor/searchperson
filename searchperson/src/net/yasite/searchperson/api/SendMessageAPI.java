package net.yasite.searchperson.api;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import net.yasite.searchperson.api.params.SendMessageParams;
import net.yasite.searchperson.api.params.Urls;
import net.yasite.util.BaseAPI;
import net.yasite.util.BaseHttpParam;

public class SendMessageAPI extends BaseAPI {

	public SendMessageAPI(Context context, SendMessageParams pm) {
		super(context, pm);
		// TODO Auto-generated constructor stub
		setMethod(Urls.WEB_SERVER_PATH + Urls.PATH + Urls.ALL_DEVICE + "?timestamp=" + pm.getTime() + "&access_id=" 
				+ pm.getAccess_id() + "&device_token=" + pm.getDevice_token()
				+ "&message_type=" + pm.getMessage_type() 
				+ "&message=" + pm.getContent() 
				+ "&sign=" + pm.getSign());
		
	}
	
	
	

	@Override
	public Object handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
