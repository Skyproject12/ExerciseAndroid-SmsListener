package com.example.smslistener;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;


public class DownloadService extends IntentService {


    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Download Service","Dowbnload Service dijalankan");
        if (intent != null) {
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            // intent  with download
            Intent notifyIntent= new Intent(MainActivity.ACTION_DOWNLOAD_STATUS);
            // melakukan downlload file secara asyncronus
            sendBroadcast(notifyIntent);
        }
    }

}
