package com.example;

import android.os.AsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: rajat
 * Date: 8/31/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test extends AsyncTask<String, Void, String> {
    @Override
    public String doInBackground(String... params) {
        ConnectGTalk connect = new ConnectGTalk();
        connect.authenticate(params[0], params[1]);
        return null;
    }
}
