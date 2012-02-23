package pdm.test.mappe;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RunnerActivity extends MapActivity
 {
    MapView mapView;
    MyLocationOverlay myLocationOverlay;
    
    RadiusOverlay radiusOverlayStazioneTermini,radiusOverlayPiazzaDellaRepubblica,radiusOverlayColosseo,radiusOverlayCasaDiRomoloERemo;
    
    GeoPoint stazioneTermini,
    piazzaDellaRepubblica,
    colosseo,
    casaDiRomoloERemo;
    
    PendingIntent pendingIntentTermini,
    pendingIntentPiazzaDellaRepubblica,
    pendingIntentColosseo,
    pendingIntentCasaDiRomoloERemo;
    
    ProximityBroadcast proximityBroadcast;
    LocationManager locationManager;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView=(MapView)findViewById(R.id.mapview);
        myLocationOverlay = new MyLocationOverlay(this, mapView);

        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        
        stazioneTermini = new GeoPoint (41902022, 12500882);
        piazzaDellaRepubblica = new GeoPoint (41902622, 12495482);
        colosseo = new GeoPoint (41890310, 12492410);
        casaDiRomoloERemo = new GeoPoint (41890492, 12484823);

        radiusOverlayStazioneTermini = new RadiusOverlay (stazioneTermini, 400, Color.BLUE);
        radiusOverlayPiazzaDellaRepubblica = new RadiusOverlay (piazzaDellaRepubblica, 300, Color.BLUE);
        radiusOverlayColosseo = new RadiusOverlay (colosseo, 500, Color.BLUE);
        radiusOverlayCasaDiRomoloERemo = new RadiusOverlay (casaDiRomoloERemo, 450, Color.BLUE);

        mapView.getOverlays().add(radiusOverlayStazioneTermini);
        mapView.getOverlays().add(radiusOverlayPiazzaDellaRepubblica);
        mapView.getOverlays().add(radiusOverlayColosseo);
        mapView.getOverlays().add(radiusOverlayCasaDiRomoloERemo);
        
        

        
        myLocationOverlay.runOnFirstFix(new Runnable() {
        	public void run() {
        	mapView.getController().animateTo(myLocationOverlay.getMyLocation());
        	}
        	});
        
        


    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void onResume()
    {
    	super.onResume();
    	myLocationOverlay.enableMyLocation();
        registerReceiver(proximityBroadcast, new IntentFilter("pdm.test.mappe"));
       
        Intent intentStazioneTermini = new Intent("pdm.test.mappe");
           intentStazioneTermini.putExtra("overlay", 1);
           pendingIntentTermini = PendingIntent.getBroadcast (this, 1,
            intentStazioneTermini,
            PendingIntent.FLAG_CANCEL_CURRENT);
           
           Intent intentPiazzaDellaRepubblica = new Intent("pdm.test.mappe");
           intentPiazzaDellaRepubblica.putExtra("overlay", 2);
           pendingIntentPiazzaDellaRepubblica = PendingIntent.getBroadcast (this, 2,
            intentPiazzaDellaRepubblica,
            PendingIntent.FLAG_CANCEL_CURRENT);
           
           Intent intentColosseo = new Intent("pdm.test.mappe");
           intentColosseo.putExtra("overlay", 3);
           pendingIntentColosseo = PendingIntent.getBroadcast (this, 3,
            intentColosseo,
            PendingIntent.FLAG_CANCEL_CURRENT);
           
           Intent intentCasaDiRomoloERemo = new Intent("pdm.test.mappe");
           intentCasaDiRomoloERemo.putExtra("overlay", 4);
           pendingIntentCasaDiRomoloERemo = PendingIntent.getBroadcast (this, 4,
            intentCasaDiRomoloERemo,
            PendingIntent.FLAG_CANCEL_CURRENT);
           
           locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
           locationManager.addProximityAlert (stazioneTermini.getLatitudeE6() * 0.000001,
            stazioneTermini.getLongitudeE6() * 0.000001,
            400, -1, pendingIntentTermini);
           
           locationManager.addProximityAlert (piazzaDellaRepubblica.getLatitudeE6() * 0.000001,
            piazzaDellaRepubblica.getLongitudeE6() * 0.000001,
            300, -1, pendingIntentPiazzaDellaRepubblica);
           
           locationManager.addProximityAlert (colosseo.getLatitudeE6() * 0.000001,
            colosseo.getLongitudeE6() * 0.000001,
            500, -1, pendingIntentColosseo);
           
           locationManager.addProximityAlert (casaDiRomoloERemo.getLatitudeE6() * 0.000001,
   casaDiRomoloERemo.getLongitudeE6() * 0.000001,
   450, -1, pendingIntentCasaDiRomoloERemo);
       
    }
	
	@Override
    public void onPause()
    {
    	super.onPause();
    	myLocationOverlay.disableMyLocation();
        unregisterReceiver(proximityBroadcast);
        locationManager.removeProximityAlert(pendingIntentTermini);
        locationManager.removeProximityAlert(pendingIntentPiazzaDellaRepubblica);
        locationManager.removeProximityAlert(pendingIntentColosseo);
        locationManager.removeProximityAlert(pendingIntentCasaDiRomoloERemo);
       

    }
	
	class ProximityBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.d("Proximity", "Proximity Alert");
			Toast.makeText(getApplicationContext(), "Alert di Prossimità", Toast.LENGTH_LONG).show();
			boolean stoEntrando = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
			if (stoEntrando) {
			Toast.makeText(getApplicationContext(), "Benvenuto", Toast.LENGTH_SHORT).show();
			radiusOverlayStazioneTermini.setColor(Color.GREEN);
			radiusOverlayPiazzaDellaRepubblica.setColor(Color.GREEN);
			radiusOverlayColosseo.setColor(Color.GREEN);
			radiusOverlayCasaDiRomoloERemo.setColor(Color.GREEN);
			} else {
			Toast.makeText(getApplicationContext(), "Arrivederci", Toast.LENGTH_SHORT).show();
			radiusOverlayStazioneTermini.setColor(Color.GRAY);
			radiusOverlayPiazzaDellaRepubblica.setColor(Color.GRAY);
			radiusOverlayColosseo.setColor(Color.GRAY);
			radiusOverlayCasaDiRomoloERemo.setColor(Color.GRAY);
			}
			int area = intent.getIntExtra("overlay", -1);

			}
		}
}