package com.example.clement.cesiactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    public static String KEY ="keyone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txt = (TextView) findViewById(R.id.txtView);

        String value = this.getIntent().getExtras().getString(KEY);
        txt.setText(value);
    }
}
