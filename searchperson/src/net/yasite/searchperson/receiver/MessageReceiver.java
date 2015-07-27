package net.yasite.searchperson.receiver;

import net.yasite.searchperson.constant.SysConstant;
import net.yasite.searchperson.entity.LocateEntity;
import net.yasite.searchperson.handler.SendMessageHandler;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class MessageReceiver extends XGPushBaseReceiver implements TencentLocationListener{
	private Intent intent = new Intent("net.yasite.searchperson.activity.UPDATE_LISTVIEW");
	public static final String LogTag = "TPushReceiver";
	private TencentLocationManager mLocationManager;
	private Context context;
	private String localToken;
	private String token;
	private void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	// 通知展示
	@Override
	public void onNotifactionShowedResult(Context context,
			XGPushShowedResult notifiShowedRlt) {
		if (context == null || notifiShowedRlt == null) {
			return;
		}
	}

	@Override
	public void onUnregisterResult(Context context, int errorCode) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "反注册成功";
		} else {
			text = "反注册失败" + errorCode;
		}
		Log.d(LogTag, text);
//		show(context, text);

	}

	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"设置成功";
		} else {
			text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
//		show(context, text);

	}

	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"删除成功";
		} else {
			text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
//		show(context, text);

	}

	// 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
	@Override
	public void onNotifactionClickedResult(Context context,
			XGPushClickedResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
			// 通知在通知栏被点击啦。。。。。
			// APP自己处理点击的相关动作
			// 这个动作可以在activity的onResume也能监听，请看第3点相关内容
			text = "通知被打开 :" + message;
		} else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
			// 通知被清除啦。。。。
			// APP自己处理通知被清除后的相关动作
			text = "通知被清除 :" + message;
		}
		Toast.makeText(context, "广播接收到通知被点击:" + message.toString(),
				Toast.LENGTH_SHORT).show();
		// 获取自定义key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1为前台配置的key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP自主处理的过程。。。
		Log.d(LogTag, text);
//		show(context, text);
	}

	@Override
	public void onRegisterResult(Context context, int errorCode,
			XGPushRegisterResult message) {
		// TODO Auto-generated method stub
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = message + "注册成功";
			// 在这里拿token
			String token = message.getToken();
		} else {
			text = message + "注册失败，错误码：" + errorCode;
		}
		System.out.println(LogTag + " : " + text);
//		show(context, text);
	}

	// 消息透传
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		// TODO Auto-generated method stub
		this.context = context;
		String text = "收到消息:" + message.toString();
		System.out.println(text);
		System.out.println(message.getCustomContent());
		LocateEntity entity = new Gson().fromJson(message.getCustomContent(), 
				LocateEntity.class);
		token = entity.getToken();
		
		if(entity.getFlag().equals(SysConstant.SEARCH)){
			mLocationManager = TencentLocationManager.getInstance(context);
			// 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
			mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
			startLocation();
		}else if(entity.getFlag().equals(SysConstant.REQUEST)){
			intent.putExtra("info", 
					entity);
			context.sendBroadcast(intent);
		}
		
		show(context, text);
	}

	public void startLocation() {
		// 创建定位请求
		TencentLocationRequest request = TencentLocationRequest.create()
				.setInterval(5000) // 设置定位周期
				.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME); // 设置定位level

		// 开始定位
		mLocationManager.requestLocationUpdates(request, this);

	}

	@Override
	public void onLocationChanged(TencentLocation location, int error,
			String reason) {
		// TODO Auto-generated method stub
		if (error == TencentLocation.ERROR_OK) {
			// 定位成功
//			addLocate(location.getLatitude(),location.getLongitude());
			LocateEntity entity = new LocateEntity();
			entity.setLatitude(location.getLatitude() + "");
			entity.setLongitude(location.getLongitude() + "");
			entity.setFlag(SysConstant.REQUEST);
			entity.setToken(XGPushConfig.getToken(context));
			new SendMessageHandler(context, token, entity).execute();
			mLocationManager.removeUpdates(this);
		} else {
			// 定位失败
			//msg = "定位失败: " + reason;
		}
		
	}

	@Override
	public void onStatusUpdate(String arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

}
