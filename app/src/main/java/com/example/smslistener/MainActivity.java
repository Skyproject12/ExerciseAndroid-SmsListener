package com.example.smslistener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCheckPermision;
    BroadcastReceiver downloadReceiver;
    Button buttonDownload;
    public static final String ACTION_DOWNLOAD_STATUS="download_status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCheckPermision= findViewById(R.id.button_permission);
        buttonCheckPermision.setOnClickListener(this);
        // check permision from manifest
        PemissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
        // initialitation download button
        buttonDownload= findViewById(R.id.button_download);
        // call Broadcash Receiver
        buttonDownload.setOnClickListener(this);

        // berfungsi ketika suatu intent suadah di jalankan atau sudah melakukan download maka proses ini akan dijalankan
        downloadReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Main Activity","Download Selesai");
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show();

            }
        };
        // menangkap object yang dikirimmoleh download receiver
        IntentFilter downloadFilter= new IntentFilter(ACTION_DOWNLOAD_STATUS);
        // memanggil fungsi donload
        registerReceiver(downloadReceiver, downloadFilter);

    }

    final int SMS_REQUEST_CODE= 101;

    // use permissin in sms listener
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== SMS_REQUEST_CODE){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Sms receiver permision diterima", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Sms receiver permision ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_permission){
            PemissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
        }
        // menklik button download
        else if(view.getId() == R.id.button_download){
            Intent downloadInte= new Intent(this, DownloadService.class);
            startService(downloadInte);
        }
    }

    @Override
    protected void onDestroy() {
        // menghancurkan aktifity ketika download berhasil
        super.onDestroy();
        // ketika download tidak sama dengan kosong
        if(downloadReceiver!=null){
            unregisterReceiver(downloadReceiver);
        }
    }
}
