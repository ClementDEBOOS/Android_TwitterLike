package com.example.clement.cesiactivity.model;

/**
 * Created by clement on 25/10/17.
 */
public class HttpResult {

    public final int status;
    public final String json;

    public HttpResult(int status, String json) {
       this.json = json;
       this.status = status;

    }
}
