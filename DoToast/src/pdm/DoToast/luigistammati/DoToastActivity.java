package pdm.DoToast.luigistammati;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout ll = new LinearLayout(DoToastActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        Button btn=new Button(this);
        btn.setText("saluta");
        btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        btn.setOnClickListener(this);
        ll.addView(btn);  //aggiunge il bottone btn al layout 
        setContentView(ll); //imposta il layou ll come root dell'activyty
    }
    public void onClick(View v){
    	Toast.makeText(getApplicationContext(), "ciao a tutti", Toast.LENGTH_LONG).show();
    };
}