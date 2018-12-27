package com.zqh.android.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.zqh.android.weather.query.WeatherApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    private static final String WeatherURL = "http://api.yytianqi.com/forecast7d?city=%s&key=hr6q876tnj79b8gb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

    }

    private void showWeather(WeatherApi weatherApi) {

    }

    /**
     * 获取天气情况
     *
     * @param county_id id
     */
    private void getWeather(String county_id) {
        //用province_id替换%s
        String url = String.format(WeatherURL, county_id);
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(url)
                .build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String data = response.body().string();
                        Log.i("获取天气信息。。。", "获取到信息为：" + data);
                        //json转换
                        Gson gson = new Gson();
                        WeatherApi weatherApi = gson.fromJson(data, WeatherApi.class);
                        showWeather(weatherApi);
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
