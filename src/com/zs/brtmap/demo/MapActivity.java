package com.zs.brtmap.demo;

import java.util.List;

import android.os.Bundle;
import android.util.Log;

import com.esri.android.map.GraphicsLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.ty.mapsdk.TYMapView;
import com.ty.mapsdk.TYPictureMarkerSymbol;
import com.ty.mapsdk.TYPoi;

public class MapActivity extends BaseMapViewActivity {
	static {
		System.loadLibrary("TYMapSDK");
		//System.loadLibrary("TYLocationEngine");
	}
	Boolean isFirst = true;
	GraphicsLayer graphicsLayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initContentViewID() {
		contentViewID = R.layout.activity_map_view;
	}

	@Override
	public void onClickAtPoint(TYMapView mapView, Point mappoint) {
		Log.i(TAG, "Clicked Point: " + mappoint.getX() + ", " +  mappoint.getY());
		
		TYPoi poi = mapView.extractRoomPoiOnCurrentFloor(mappoint.getX(),
				mappoint.getY());
		if (poi != null) {
			mapView.highlightPoi(poi);
		}
		TYPictureMarkerSymbol pms = new TYPictureMarkerSymbol(getResources()
				.getDrawable(R.drawable.green_pushpin));
		pms.setWidth(20.0f);
		pms.setHeight(20.0f);
		
		if (graphicsLayer == null) {
			graphicsLayer = new GraphicsLayer();
			mapView.addLayer(graphicsLayer);
		}
		graphicsLayer.removeAll();
		graphicsLayer.addGraphic(new Graphic(new Point(mappoint.getX(),
				mappoint.getY()), pms));

	}

	@Override
	public void onPoiSelected(TYMapView mapView, List<TYPoi> poiList) {
		mapView.highlightPois(poiList);
	}
}
