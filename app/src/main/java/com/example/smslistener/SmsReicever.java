package com.example.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReicever extends BroadcastReceiver {

    final String TAG= SmsReicever.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle= intent.getExtras();
        try {
            if(bundle!=null){
                final Object[] object= (Object[])bundle.get("pdus");
                if(object!=null){
                    for(Object objectj: object){
                        SmsMessage currentMessage= getInComingMessage(objectj, bundle);
                        // get no telp when the user can telp
                        String senderNum= currentMessage.getDisplayOriginatingAddress();
                        // getb message from sms user
                        String message= currentMessage.getDisplayMessageBody();
                        Log.d(TAG,"senderNum:"+senderNum+"; message"+message);
                        Intent showSmsIntent= new Intent(context, SmsReceiverActivity.class);
                        showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message);
                        context.startActivity(showSmsIntent);
                    }
                }
            }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
    // call class smsmessage
    private SmsMessage getInComingMessage(Object object, Bundle bundle){
        // initial smsmessage
        SmsMessage currentSMS;
        if(Build.VERSION.SDK_INT >=23 ){
            String format= bundle.getString("format");
            currentSMS= SmsMessage.createFromPdu((byte[])object, format);
        }
        else{
            currentSMS= SmsMessage.createFromPdu((byte[])object);
        }
        // mengembalikan nilai smsmessage
        return currentSMS;
    }

    public SmsReicever() {
    }
}
