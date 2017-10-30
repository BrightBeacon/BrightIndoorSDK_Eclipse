package com.zs.brtmap.demo;

import java.util.List;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.esri.android.map.GraphicsLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.ty.mapsdk.TYMapInfo;
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
		Log.i(TAG, "Clicked Point: " + mappoint.getX() + ", " +  mappoint.getY()+" mapScale:"+mapView.getScale()+mapView.getMaxScale()+mapView.getMinScale());
		
		TYPoi poi = mapView.extractRoomPoiOnCurrentFloor(mappoint.getX(),
				mappoint.getY());
		if (poi != null && poi.getName()!=null) {
			mapView.highlightPoi(poi);
		}
		TYPictureMarkerSymbol pms = new TYPictureMarkerSymbol(getResources()
				.getDrawable(R.drawable.green_pushpin));
		pms.setWidth(20.0f);
		pms.setHeight(20.0f);
		pms.setOffsetY(10);
		
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
	
	@Override
	public void mapViewDidLoad(TYMapView mapView, Error error) {
		super.mapViewDidLoad(mapView, error);
	}
	
	@Override	
	public void onFinishLoadingFloor(final TYMapView mapView, TYMapInfo mapInfo) {
		setMinMaxScaleFactor(1, 8);
	}
	
	public void setMinMaxScaleFactor(double minFactor,double maxFactor) {

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		double deviceDistance = metrics.widthPixels/metrics.xdpi*0.0254;
		double distance = mapView.currentMapInfo.getMapSize().x;
		double baseScale  = distance / deviceDistance;
		mapView.setMinScale(baseScale*minFactor);
		mapView.setMaxScale(baseScale/maxFactor);
	}
}
