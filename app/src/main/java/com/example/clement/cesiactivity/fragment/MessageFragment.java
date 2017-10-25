package com.example.clement.cesiactivity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.clement.cesiactivity.JsonParse;
import com.example.clement.cesiactivity.MessagesActivity;
import com.example.clement.cesiactivity.R;
import com.example.clement.cesiactivity.UserActivity;
import com.example.clement.cesiactivity.adapter.MessagesAdapter;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;
import com.example.clement.cesiactivity.model.Message;
import com.example.clement.cesiactivity.model.Session;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clement on 25/10/17.
 */
public class MessageFragment extends Fragment {
    private MessagesAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved){
        super.onCreateView(inflater, container, saved);

        View v = inflater.inflate(R.layout.fragment_message, container, false);


        ListView list = (ListView) v.findViewById(R.id.listMessages);
        adapter = new MessagesAdapter(MessageFragment.this.getActivity());
        list.setAdapter(adapter);


        final EditText txt = (EditText) v.findViewById(R.id.txtMessage);

        new GetMessages().execute();



        v.findViewById(R.id.buttonMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ping
                new SetMessage().execute(txt.getText().toString());
            }
        });

        v.findViewById(R.id.buttonUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageFragment.this.getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }







    public class GetMessages extends AsyncTask<String, Void, HttpResult> {

        @Override
        protected HttpResult doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(MessageFragment.this.getActivity())) {
                return null;
            }
            HttpResult r = NetworkHelper.doGet("http://cesi.cleverapps.io/messages", null, Session.token);
            return r;
        }

        @Override
        protected void onPostExecute(HttpResult s) {
            if (s.status == 200){
                List<Message> messages= null;
                try {
                    messages = JsonParse.getMessages(s.json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.setMessages(messages);
            }else {
                Toast.makeText(MessageFragment.this.getActivity(), s.json, Toast.LENGTH_SHORT).show();

            }
        }
    }

    public class SetMessage extends AsyncTask<String, Void, HttpResult>{

        @Override
        protected HttpResult doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(MessageFragment.this.getActivity())) {
                return null;
            }
            Map<String, String> params = new HashMap<>();
            params.put("message", strings[0]);
            HttpResult r = NetworkHelper.doPost("http://cesi.cleverapps.io/messages", params , Session.token);
            return r;
        }

        @Override
        protected void onPostExecute(HttpResult s) {
            if (s == null || s.status != 200) {
                Toast.makeText(MessageFragment.this.getActivity(), "Erreur de l'envoie du message", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MessageFragment.this.getActivity(), s.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
