package com.edumotter.oscar.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.edumotter.oscar.models.User;
import com.edumotter.oscar.activities.DashboardActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTask extends AsyncTask<String, Void, String> {
    protected JSONObject object;
    private Context context;
    private User user;
    private ProgressDialog progressDialog;


    public LoginTask(Context context, ProgressDialog progressDialog, User user) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... strings) {
        String stringURL = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer buffer = null;

        try {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

           //criar json
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(user.toString());
            wr.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode >= 400 && responseCode <= 499) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }

            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append((line));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Carregando..");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        if(progressDialog.isShowing())
            progressDialog.dismiss();
        try {
            object = new JSONObject(s);
            Integer cod = object.getInt("cod");

            if (cod == 200 && object!= null) {
                JSONObject main = object.getJSONObject("main");


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        user = new User();
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.putExtra("user", user.toString());
        context.startActivity(intent);
    }
}
