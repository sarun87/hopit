package ncsu.hopit;



import java.io.File;
import java.io.FilenameFilter;

import ncsu.hopit.Tab1;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Tab1 extends Activity {
	
	private static String srcFile = "";
	private static String destFile = "";
	private static String fileType = "";
    private static final int REQUEST_ENABLE_BT = 0;
	private static final String HOPIT_DEVICE = "HOPIT";

	/** Called when the activity is first created. */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.first);

		// Initialize array adapters. One for already paired devices and
		// one for newly discovered devices

		// Get the local Bluetooth adapter
		BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		String myInfo = "My Info:\nMac: " + mBtAdapter.getAddress() + "\n";
		if (!mBtAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			myInfo = "My Info:\nMac: " + mBtAdapter.getAddress() + "\n";
			String oldName = mBtAdapter.getName();
			mBtAdapter.setName(HOPIT_DEVICE + "_" + mBtAdapter.getAddress());

		}
		//

	}
	
	public void onSelectSrcClicked(View v){
		//onCreateDialog(DIALOG_LOAD_FILE);
        //Dialog alertDialog = new Dialog(this);
        //.Builder(this).create();
        //alertDialog.show();
		fileType = "src";
		loadFileList(new File(Environment.getExternalStorageDirectory() + "//"));
		showDialog(DIALOG_LOAD_FILE);	
	}
	
	public void onSelectDestClicked(View v){
		//onCreateDialog(DIALOG_LOAD_FILE);
        //Dialog alertDialog = new Dialog(this);
        //.Builder(this).create();
        //alertDialog.show();
		fileType = "dest";
		loadFileList(new File(Environment.getExternalStorageDirectory() + "//"));
		showDialog(DIALOG_LOAD_FILE);	
	}
	
	
	public void onConvertClicked(View v){
		/// Write bluetooth search/protocol handshake and conversion code here.
		// For now, use default destination file/folder
	}
	
	
	////////////////////
	private String[] mFileList;

	private String mChosenFile;
	private static final String FTYPE = ".doc";    
	private static final int DIALOG_LOAD_FILE = 1000;
	private static final String TAG = null;

	private void loadFileList(File mPath) {
	    try {
	        mPath.mkdirs();
	    }
	    catch(SecurityException e) {
	        Log.e(TAG, "unable to write on the sd card " + e.toString());
	    }
	    if(mPath.exists()) {
	        FilenameFilter filter = new FilenameFilter() {
	            public boolean accept(File dir, String filename) {
	                File sel = new File(dir, filename);
	                return filename.contains(FTYPE) || sel.isDirectory();
	            }
	        };
	        mFileList = mPath.list(filter);
	        String path = mPath.getPath();
	        for(int i = 0; i < mFileList.length; ++i)
	        	mFileList[i] = path + "/" + mFileList[i]; 
	    }
	    else {
	        mFileList= new String[0];
	    }
	}

	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog = null;
	    AlertDialog.Builder builder = new Builder(this);
	    
	    switch(id) {
	        case DIALOG_LOAD_FILE:
	            builder.setTitle("Choose your file");
	            if(mFileList == null) {
	                Log.e(TAG, "Showing file picker before loading the file list");
	                dialog = builder.create();
	                return dialog;
	            }
	            	builder.setItems(mFileList, new DialogInterface.OnClickListener() {
	            		public void onClick(DialogInterface dialog, int which) {
	            			mChosenFile = mFileList[which];
	            			//you can do stuff with the file here too
	            			dialog.dismiss();
	            			dialog.cancel();
	            			setFile();
	            			return;
	            		}
	            	});
	            break;
	    }
	    dialog = builder.show();
	    return dialog;
	}
	
	public void setFile(){
		if(new File(mChosenFile).isDirectory()){
			loadFileList(new File(mChosenFile+ "//"));
			removeDialog(DIALOG_LOAD_FILE);
			showDialog(DIALOG_LOAD_FILE);
		}	
		else{
			
			TextView infoServ = null;
			if(fileType.equalsIgnoreCase("src"))
			{	srcFile = mChosenFile;
				infoServ= (TextView) findViewById(R.id.textView_Src);
			}else
			{
				destFile = mChosenFile;
				infoServ= (TextView) findViewById(R.id.textView_Dest);
			}
			infoServ.setText(mChosenFile);
			fileType = "";
		}
	}
	
}