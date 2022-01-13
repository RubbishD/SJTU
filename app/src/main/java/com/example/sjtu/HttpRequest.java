package com.example.sjtu;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    HttpURLConnection Connection = null;

    public String post(String url1, String param) {
        try {
            URL url = new URL(url1);
            Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("POST");
            Connection.setConnectTimeout(3000);
            Connection.setReadTimeout(3000);
            Connection.setDoInput(true);
            Connection.setDoOutput(true);
            Connection.setUseCaches(false);
            Connection.connect();
            DataOutputStream dos = new DataOutputStream(Connection.getOutputStream());
            dos.write(param.getBytes());
            dos.flush();
            dos.close();
            int responseCode = Connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = Connection.getInputStream();
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    arrayOutputStream.write(bytes, 0, length);
                    arrayOutputStream.flush();
                }
                return arrayOutputStream.toString();
            } else {
                return "-1";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String get(String url1) {
        try {
            URL url = new URL(url1);
            Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("GET");
            Connection.setConnectTimeout(3000);
            Connection.setReadTimeout(3000);
            int responseCode = Connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = Connection.getInputStream();
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    arrayOutputStream.write(bytes, 0, length);
                    arrayOutputStream.flush();
                }
                return arrayOutputStream.toString();
            } else {
                return "-1";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }



}

