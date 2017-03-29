package com.example.zalegoadmin.dbstorage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button submit;
    TextView textView;
    BroadcastReceiver receiver=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.TV);

        IntentFilter filter=new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               getSmsReceived(context,intent);
            }
        };
        registerReceiver(receiver,filter);
        /*username=(EditText)findViewById(R.id.username);
        submit=(Button)findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cecil", "start");
                String uname=username.getText().toString();
                String url="http:///androidremotedb/senddata.php";
                new SendData(uname,url).execute();
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void getSmsReceived(Context c,Intent intent){
        Bundle bundle=intent.getExtras();
        Object[] obj=(Object[])bundle.get("pdus");

        String sms="";
        for (int i=0;i<obj.length;i++){
            SmsMessage msg=SmsMessage.createFromPdu((byte[])(obj[i]));
            String msgBody=msg.getMessageBody();
            String sender=msg.getDisplayOriginatingAddress();

            sms+=msgBody+"Sent by:"+sender;
            textView.setText(sms);


           if(sender.equals("+254719316544")){
               Intent message=new Intent(MainActivity.this,ActivityTwo.class);
               startActivity(message);
                //finish();
            }else {
                sms+=msgBody+"Sent by:"+sender;
                textView.setText(sms);
            }

        }
    }
}
