package com.example.smslistener;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

// permision if use sms listener for android more 9.0
public class PemissionManager {
    public static void check(Activity activity, String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }
}
