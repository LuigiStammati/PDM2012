package pdm.DueActivityV2.luigistammati;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class Second extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        TextView label =(TextView) findViewById(R.id.textView1);
        String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
        label.setText(iltestoricevuto);
    } 
    public void onStart() {
        super.onStart();
        Log.d("Second","onStart");
       }
           
       public void onRestart() {
           super.onRestart();
           Log.d("Second", "onRestart");
       }
           
       public void onResume() {
           super.onRestart();
           Log.d("Second", "onResume");
       }
           
       public void onPause() {
           super.onRestart();
           Log.d("Second", "onPause");
       }
           
       public void onStop() {
           super.onRestart();
           Log.d("Second", "onStop");
       }
           
       public void onDestroy() {
           super.onRestart();
           Log.d("Second", "onDestroy");
       }
}
