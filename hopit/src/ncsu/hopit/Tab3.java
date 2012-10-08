package ncsu.hopit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Tab3 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*TextView textview = new TextView(this);
        textview.setText("This is the Second tab");
        setContentView(textview);*/
        Toast.makeText(this, "Third Tab - First time", Toast.LENGTH_LONG).show();
        
        setContentView(R.layout.second);
    }
}
