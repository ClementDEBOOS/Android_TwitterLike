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
import com.example.clement.cesiactivity.model.Session;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText username = (EditText) findViewById(R.id.txtUsername);
        final EditText pwd = (EditText) findViewById(R.id.txtPwd);

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SingInAsyncTacks().execute(username.getText().toString(), pwd.getText().toString());
            }
        });
    }

    public class SingInAsyncTacks extends AsyncTask<String, Void, HttpResult> {
        @Override
        protected HttpResult doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(SignInActivity.this)) {
                return null;
            }
            Map<String, String> params = new HashMap<>();
            params.put("username", strings[0]);
            params.put("pwd", strings[1]);
            HttpResult r = NetworkHelper.doPost("http://cesi.cleverapps.io/signin", params , null);
            return r;
        }

        @Override
        protected void onPostExecute(HttpResult s) {
            if (s == null || s.status != 200){
                Toast.makeText(SignInActivity.this, "Erreur Création de compte", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(SignInActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                SignInActivity.this.finish();
                try {
                    Session.token = JsonParse.getToken(s.json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SignInActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        }

    }
}
