package com.example.smslistener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCheckPermision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCheckPermision= findViewById(R.id.button_permission);
        buttonCheckPermision.setOnClickListener(this);
        // check permision from manifest
        PemissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
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

        }
    }
}
