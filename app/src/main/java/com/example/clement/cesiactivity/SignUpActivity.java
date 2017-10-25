package com.example.clement.cesiactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText username = (EditText) findViewById(R.id.txtUsername);
        final EditText pwd = (EditText) findViewById(R.id.txtPwd);
        final EditText imgPhoto = (EditText) findViewById(R.id.txtUrlImg);

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hello
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SingUpAsyncTacks().execute(username.getText().toString(), pwd.getText().toString(), imgPhoto.getText().toString());
            }
        });

    }

    public class SingUpAsyncTacks extends AsyncTask<String, Void, HttpResult> {
        @Override
        protected HttpResult doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(SignUpActivity.this)) {
                return null;
            }
            Map<String, String> params = new HashMap<>();
            params.put("username", strings[0]);
            params.put("pwd", strings[1]);
            params.put("urlPhoto", strings[2]);
            HttpResult r = NetworkHelper.doPost("http://cesi.cleverapps.io/signup", params , null);
            return r;
        }

        @Override
        protected void onPostExecute(HttpResult s) {
            if (s == null || s.status != 200){
                Toast.makeText(SignUpActivity.this, "Erreur Création de compte", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(SignUpActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                SignUpActivity.this.finish();
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }
    }
}
