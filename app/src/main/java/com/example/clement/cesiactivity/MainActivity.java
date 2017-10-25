package com.example.clement.cesiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText txt = (EditText) findViewById(R.id.txt);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(SecondActivity.KEY, txt.getText().toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.ping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ping
                new PingAsyncTacks().execute();
            }
        });

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hello
                new HelloAsyncTacks().execute(txt.getText().toString());
            }
        });

        findViewById(R.id.GoToSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hello
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

    public class HelloAsyncTacks extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(MainActivity.this)) {
                return "Erreur";
            }
            Map<String, String> params = new HashMap<>();
            params.put("name", strings[0]);
            HttpResult r = NetworkHelper.doGet("http://cesi.cleverapps.io/hello", params, null);
            return r.json;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public class PingAsyncTacks extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(MainActivity.this)) {
                return "Erreur";
            }
            HttpResult r = NetworkHelper.doPost("http://cesi.cleverapps.io/ping", null , null);
            return r.json;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}