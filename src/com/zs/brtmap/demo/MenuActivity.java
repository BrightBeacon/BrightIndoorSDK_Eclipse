package com.zs.brtmap.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 导航菜单页
 * 
 * @author thomasho no happy so low!
 *
 */
public class MenuActivity extends Activity implements OnClickListener {

	private TextView baseMap;
	private TextView showCallout;
	private TextView showMapLayer;
	private TextView showRoute;
	private TextView showLocation;
	private TextView showSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		initView();
		initListener();
	}
	
	private void noHappySoLow()
	{
		
	}

	private void initListener() {
		baseMap.setOnClickListener(this);
		showCallout.setOnClickListener(this);
		showMapLayer.setOnClickListener(this);
		showRoute.setOnClickListener(this);
		showLocation.setOnClickListener(this);
		showSearch.setOnClickListener(this);
	}

	private void initView() {
		baseMap = (TextView) findViewById(R.id.show_base_map);
		showCallout = (TextView) findViewById(R.id.show_map_callout);
		showMapLayer = (TextView) findViewById(R.id.show_map_layer);
		showRoute = (TextView) findViewById(R.id.show_map_route);
		showLocation = (TextView) findViewById(R.id.show_location);
		showSearch = (TextView) findViewById(R.id.show_search);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.show_base_map:
			intent = new Intent(this, MapActivity.class);
			break;
		case R.id.show_map_callout:
			intent = new Intent(this, CalloutActivity.class);
			break;
		case R.id.show_map_layer:
			intent = new Intent(this, LayerActivity.class);
			break;
		case R.id.show_map_route:
			intent = new Intent(this, RouteActivity.class);
			break;
		case R.id.show_location:
			intent = new Intent(this, LocationActivity.class);
			break;
		case R.id.show_search:
			intent = new Intent(this, SearchActivity.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

}
