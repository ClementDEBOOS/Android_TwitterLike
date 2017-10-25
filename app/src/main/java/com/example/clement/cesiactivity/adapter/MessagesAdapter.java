package com.example.clement.cesiactivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.clement.cesiactivity.R;
import com.example.clement.cesiactivity.model.Message;

import java.util.List;

/**
 * Created by clement on 25/10/17.
 */
public class MessagesAdapter extends BaseAdapter {

    List<Message> messages;
    Activity c;

    public MessagesAdapter(Activity c){
        this.c = c;
    }

    @Override
    public int getCount() {
        if(messages == null){
            return 0;
        }
        return messages.size();
    }

    @Override
    public Message getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view == null){
            LayoutInflater inflater = c.getLayoutInflater();
            view = inflater.inflate(R.layout.item_messages, viewGroup, false);
            vh = new ViewHolder();
            vh.username = view.findViewById(R.id.itemUsername);
            vh.messages = view.findViewById(R.id.itemMessages);
            vh.date = view.findViewById(R.id.itemDate);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        vh.username.setText(getItem(i).getUsername());
        vh.messages.setText(getItem(i).getMessages());
        vh.date.setText(String.valueOf( getItem(i).getDate()));

        return view;
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView username, messages, date;
    }
}
