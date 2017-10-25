package com.example.clement.cesiactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.clement.cesiactivity.adapter.MessagesAdapter;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;
import com.example.clement.cesiactivity.model.Message;
import com.example.clement.cesiactivity.model.Session;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
    }
}
