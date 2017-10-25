package com.example.clement.cesiactivity.model;

/**
 * Created by clement on 25/10/17.
 */
public class Message {

    String username;
    String messages;
    long date;

    public Message(String username, String message, long date) {
        this.username = username;
        this.messages = message;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
