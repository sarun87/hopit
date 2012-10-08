package ncsu.hopit;

import ncsu.hopit.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Tab2 extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*TextView textview = new TextView(this);
        textview.setText("This is the Second tab");
        setContentView(textview);*/
        Toast.makeText(this, "Second Tab - First Time", Toast.LENGTH_LONG).show();
        
        setContentView(R.layout.second);
    }
}
