package com.example.zalegoadmin.dbstorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Zalegoadmin on 3/28/2017.
 */
public class ActivityTwo extends Activity {
    TextView TV2;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        TV2=(TextView)findViewById(R.id.TV2);
        back=(Button)findViewById(R.id.backBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(ActivityTwo.this,MainActivity.class);
                startActivity(back);
            }
        });
    }
}
