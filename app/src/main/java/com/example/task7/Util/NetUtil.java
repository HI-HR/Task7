package com.example.task7.Util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetUtil {


    public static void doPost(String urlstr, Map<String, String> params, Handler handler) {

        new Thread(new Runnable() {
            String result = "";

            @Override
            public void run() {
                try {
                    //建立连接
                    URL url = new URL(urlstr);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(5 * 1000);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();


                    //写入数据
                    StringBuilder sb = new StringBuilder();
                    for (String key : params.keySet()) {
                        sb.append(key).append("=").append(params.get(key)).append("&");
                    }
                    sb.deleteCharAt(sb.length() - 1);//删除最后一个 &

                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(sb.toString().getBytes());

                    //获取返回信息
                    InputStream inputStream = urlConnection.getInputStream();
                    result = StreamToString(inputStream);

                    Log.d("ld", result);

                    Message message = new Message();
                    message.what = 0;
                    message.obj = result;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }


    public static void doGet(String urlstr, Handler handler) {
        String result = "";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5 * 1000);
            connection.setRequestMethod("GET");
            connection.connect();
            //建立连接

            InputStream inputStream = connection.getInputStream();
            result = StreamToString(inputStream);

            Log.d("ld", result);

            Message message = new Message();
            message.what = 0;
            message.obj = result;
            handler.sendMessage(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static String StreamToString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

}
