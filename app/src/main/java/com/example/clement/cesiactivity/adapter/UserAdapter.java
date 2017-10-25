package com.example.clement.cesiactivity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.clement.cesiactivity.R;
import com.example.clement.cesiactivity.model.Message;
import com.example.clement.cesiactivity.model.User;

import java.util.List;

/**
 * Created by clement on 25/10/17.
 */
public class UserAdapter extends BaseAdapter{

    List<User> user;
    Activity c;

    public UserAdapter(Activity c){
        this.c = c;
    }

    @Override
    public int getCount() {
        if(user == null){
            return 0;
        }
        return user.size();
    }

    @Override
    public User getItem(int i) {
        return user.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserAdapter.ViewHolder vh;
        if(view == null){
            LayoutInflater inflater = c.getLayoutInflater();
            view = inflater.inflate(R.layout.item_messages, viewGroup, false);
            vh = new UserAdapter.ViewHolder();
            vh.username = view.findViewById(R.id.itemUsername);
            view.setTag(vh);
        }else {
            vh = (UserAdapter.ViewHolder) view.getTag();
        }
        vh.username.setText(getItem(i).getUser());

        return view;
    }

    public void setUser(List<User> user){
        this.user = user;
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView username;
    }
}
