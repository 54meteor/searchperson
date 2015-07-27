package net.yasite.searchperson;

import net.yasite.searchperson.entity.LocateEntity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.tencent.mapsdk.raster.model.GeoPoint;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.OverlayItem;

public class MapActivity extends BaseNewActivity {
	private MapView mapView;
	private MsgReceiver updateLocate;
	@Override
	public void setupView(Bundle arg0) {
		// TODO Auto-generated method stub
		mapView = (MapView)findViewById(R.id.map);
		mapView.onCreate(arg0);
		mapView.getController().setZoom(15);
	}

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		updateLocate = new MsgReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("net.yasite.searchperson.activity.UPDATE_LISTVIEW");
		registerReceiver(updateLocate, intentFilter);
	}

	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public class MsgReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			LocateEntity locate = (LocateEntity)intent.getSerializableExtra("info");
			
			System.out.println(locate.getLatitude());
			System.out.println(locate.getLongitude());
			addLocate(Double.parseDouble(locate.getLatitude()),
					Double.parseDouble(locate.getLongitude()));
		}
	}
	private void addLocate(double latitude,double longitude){
		GeoPoint p1 = new GeoPoint((int)(latitude * 1e6), (int)(longitude * 1e6));
		mapView.getController().animateTo(p1);
		OverlayItem oiFixed = new OverlayItem(p1, "标注1", "不可拖拽");
		mapView.add(oiFixed);	
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(updateLocate);
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mapView.onStop();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		mapView.onRestart();
	}
	
}
