package com.example.smslistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textSmsFrom;
    TextView textSmsMessage;
    Button buttonClose;
    public static final String EXTRA_SMS_NO= "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE= "extra_sms_message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);
        final Bundle bundle= getIntent().getExtras();
        String no = bundle.get(EXTRA_SMS_NO).toString();
        String message = bundle.get(EXTRA_SMS_MESSAGE).toString();
        setTitle("Incoming Message");
        textSmsMessage= findViewById(R.id.text_message);
        textSmsFrom= findViewById(R.id.text_no);
        buttonClose= findViewById(R.id.button_close);
        textSmsFrom.setText(no);
        textSmsMessage.setText(message);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.button_close){
            finish();
        }
    }
}
