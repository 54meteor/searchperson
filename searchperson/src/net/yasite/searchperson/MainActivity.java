package net.yasite.searchperson;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import net.yasite.searchperson.adapter.AddressAdapter;
import net.yasite.searchperson.entity.AddressEntitiy;
import net.yasite.searchperson.model.AddressModel;
import net.yasite.searchperson.view.MyLetterListView;
import net.yasite.searchperson.view.MyLetterListView.OnTouchingLetterChangedListener;
import net.yasite.searchperson.view.PinnedSectionListView;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;

public class MainActivity extends BaseNewActivity {
	Message m = null;
	
	String localToken = null;
	
	ImageView addAddress,aboutMe;
	PinnedSectionListView listview;
	AddressAdapter adapter;
	AddressModel model;
	List<AddressEntitiy> list;
	
	private MyLetterListView letterListView;
	public boolean overlayFlag = true;
	private TextView overlay;// 显示索引字母
	private HashMap<String, Integer> alphaIndexer;
	public String[] sections;
	private Handler handler;
	private OverlayThread overlayThread;
	
	
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.address_list);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		list = model.getAddressList();
		adapter.setList(list);
		adapter.notifyDataSetChanged();
		sections = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = getAlpha(list.get(i).getPinyin());
            // 上一个汉语拼音首字母，如果不存在为“ ”
            String previewStr = (i - 1) >= 0 ? getAlpha(list.get(i - 1)
                    .getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(list.get(i).getPinyin());
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
        overlayThread = new OverlayThread();
		adapter = new AddressAdapter(context);
		model = new AddressModel(context);
		
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, 
					int position,long arg3) {
				Intent it = new Intent(context,AddressInfoActivity.class);
				it.putExtra("id", adapter.getItem(position).get_id());
				startActivity(it);
			}
		});
		
		
		// 1.获取设备Token
		// 注册接口
		XGPushManager.registerPush(getApplicationContext(),
				new XGIOperateCallback() {
					@Override
					public void onSuccess(Object data, int flag) {
//						localToken = data.toString();
						System.out.println(
								"+++ register push sucess. token:" + data);
					}

					@Override
					public void onFail(Object data, int errCode, String msg) {
						Log.w(Constants.LogTag,
								"+++ register push fail. token:" + data
										+ ", errCode:" + errCode + ",msg:"
										+ msg);
//						localToken = null;

					}
				});
	}
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setupView(Bundle arg0) {
		// TODO Auto-generated method stub
		getTextView(R.id.titleTv).setText("列表");
		getImageView(R.id.back).setVisibility(View.GONE);
		addAddress = getImageView(R.id.add_address);
		addAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(context,AddAddressActivity.class);
				startActivity(it);
			}
		});
		listview = (PinnedSectionListView)findViewById(R.id.address_list);
		aboutMe = getImageView(R.id.aboutme);
		aboutMe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(context,AboutMeActivity.class);
				startActivity(it);
			}
		});
		letterListView = (MyLetterListView) findViewById(R.id.letter);
		letterListView.setVisibility(View.VISIBLE);
		letterListView
		.setOnTouchingLetterChangedListener(new LetterListViewListener());
	}
		
	
	
	private class LetterListViewListener implements
	OnTouchingLetterChangedListener {
		
		@Override
		public void onTouchingLetterChanged(final String s) {
			if (overlayFlag) {
				initOverlay();
				overlayFlag = false;
			}
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				listview.setSelection(position);
				overlay.setText(sections[position]);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				handler.postDelayed(overlayThread, 1500);
			}
		}
		
	}
	// 设置overlay不可见
	private class OverlayThread implements Runnable {
		
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
		
	}
	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		try{
			LayoutInflater inflater = LayoutInflater.from(this);
			overlay = (TextView) inflater.inflate(R.layout.overlay, null);
			overlay.setVisibility(View.INVISIBLE);
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
					PixelFormat.TRANSLUCENT);
			WindowManager windowManager = (WindowManager) this
					.getSystemService(Context.WINDOW_SERVICE);
			windowManager.addView(overlay, lp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }

        if (str.trim().length() == 0) {
            return "#";
        }

        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else {
            return "#";
        }
    }
}
