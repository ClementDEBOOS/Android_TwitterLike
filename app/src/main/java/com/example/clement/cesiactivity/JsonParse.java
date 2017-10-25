package com.example.clement.cesiactivity;


import com.example.clement.cesiactivity.model.Message;
import com.example.clement.cesiactivity.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by clement on 25/10/17.
 */
public class JsonParse {

    public static List<Message> getMessages(String json) throws JSONException {
        List<Message> messages = new LinkedList<>();
        JSONArray array = new JSONArray(json);
        JSONObject obj;
        Message msg;
        for(int i=0; i < array.length(); i++){
            obj = array.getJSONObject(i);
            msg = new Message(obj.optString("username"), obj.optString("message"), obj.optLong("date"));
            messages.add(msg);
        }

        return messages;
    }

    public static String getToken(String response) throws JSONException {
        return new JSONObject(response).optString("token");
    }

    public static List<User> getUsers(String response) throws JSONException {
        JSONArray array = new JSONArray(response);
        List<User> users = new LinkedList<>();
        JSONObject obj;
        User usr;
        for(int i=0; i<array.length(); i++){
            obj = array.getJSONObject(i);
            usr = new User(obj.optString("username"));
            users.add(usr);
        }
        return users;
    }
}
