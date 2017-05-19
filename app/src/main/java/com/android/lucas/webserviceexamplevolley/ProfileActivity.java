package com.android.lucas.webserviceexamplevolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tv = (TextView) findViewById(R.id.tvAnswer);
        tv.setText("Resposta servidor: " + getIntent().getExtras().getString("answer"));
    }
}
