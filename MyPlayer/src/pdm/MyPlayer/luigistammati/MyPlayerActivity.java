package pdm.MyPlayer.luigistammati;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


 
public class MyPlayerActivity extends Activity {
	MediaPlayer mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mp=MediaPlayer.create(MyPlayerActivity.this,R.raw.dst);
        Button startButton =(Button)findViewById(R.id.button1);
        startButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        	  mp.start();
        	}
        });
        Button pausaButton =(Button)findViewById(R.id.button2);
        pausaButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        	  mp.pause();
        	}
        	
        }); 
        
    }
    @Override
    public void onDestroy(){
    	mp.release();
    }
}