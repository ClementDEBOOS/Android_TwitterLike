package com.example.clement.cesiactivity.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.example.clement.cesiactivity.JsonParse;
import com.example.clement.cesiactivity.R;
import com.example.clement.cesiactivity.UserActivity;
import com.example.clement.cesiactivity.adapter.UserAdapter;
import com.example.clement.cesiactivity.helper.NetworkHelper;
import com.example.clement.cesiactivity.model.HttpResult;
import com.example.clement.cesiactivity.model.Message;
import com.example.clement.cesiactivity.model.Session;
import com.example.clement.cesiactivity.model.User;
import org.json.JSONException;

import java.util.List;

/**
 * Created by clement on 25/10/17.
 */
public class UserFragment extends Fragment {

    private UserAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved) {
        super.onCreateView(inflater, container, saved);

        View v = inflater.inflate(R.layout.fragment_user, container, false);

        new GetUser().execute();

        ListView list = (ListView) v.findViewById(R.id.listUsers);
        adapter = new UserAdapter(UserFragment.this.getActivity());
        list.setAdapter(adapter);

        return v;
    }

    public class GetUser extends AsyncTask<String, Void, HttpResult> {

        @Override
        protected HttpResult doInBackground(String... strings) {
            if (!NetworkHelper.isInternetAvailable(UserFragment.this.getActivity())) {
                return null;
            }
            HttpResult r = NetworkHelper.doGet("http://cesi.cleverapps.io/users", null, Session.token);
            return r;
        }

        @Override
        protected void onPostExecute(HttpResult s) {
            if (s.status == 200){
                List<User> user = null;
                try {
                    user = JsonParse.getUsers(s.json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.setUser(user);
            }else {
                Toast.makeText(UserFragment.this.getActivity(), s.json, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
