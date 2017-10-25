package com.example.clement.cesiactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.clement.cesiactivity.adapter.MessagesAdapter;
import com.example.clement.cesiactivity.adapter.UserAdapter;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;
import com.example.clement.cesiactivity.model.Session;
import com.example.clement.cesiactivity.model.User;

public class UserActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



    }


}
