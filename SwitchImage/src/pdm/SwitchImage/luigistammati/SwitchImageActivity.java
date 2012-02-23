package pdm.SwitchImage.luigistammati;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class SwitchImageActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final ImageView iv=(ImageView) findViewById(R.id.imageView1);
        ToggleButton togBut =(ToggleButton) findViewById(R.id.toggleButton1);
        togBut.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton b, boolean isChecked) {
				// TODO Auto-generated method stub
				 if(isChecked){
			        	iv.setImageResource(R.drawable.button_pause);
			        }else{
			        	iv.setImageResource(R.drawable.button_play);
			        }
			}
		
    });
}
}