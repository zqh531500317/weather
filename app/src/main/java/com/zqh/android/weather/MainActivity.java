package com.zqh.android.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.zqh.android.weather.dao.CityDao;
import com.zqh.android.weather.dao.CountyDao;
import com.zqh.android.weather.dao.ProvinceDao;
import com.zqh.android.weather.db.City;
import com.zqh.android.weather.db.County;
import com.zqh.android.weather.db.Province;
import com.zqh.android.weather.query.AreaApi;
import com.zqh.android.weather.query.AreaApiHandle;
import com.zqh.android.weather.query.WeatherApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    private static final String URL = "http://api.yytianqi.com/citylist/id/2";
    private static final String WeatherURL = "http://api.yytianqi.com/forecast7d?city=%s&key=hr6q876tnj79b8gb";
    private ProvinceDao provinceDao = new ProvinceDao();
    private CityDao cityDao = new CityDao();
    private CountyDao countyDao = new CountyDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过litepal连接数据库
        Connector.getDatabase();
        /*DataSupport.deleteAll(Province.class);
        DataSupport.deleteAll(City.class);
        DataSupport.deleteAll(County.class);*/
        //在数据库中查找
        boolean b = provinceDao.isEmpty();
        //找不到则调用api
        if (b) {
            getRequest();
        } else {
            showProvinceData();
        }
    }

    private void showProvinceData() {
        List<Province> list = provinceDao.list();
    }

    private void showCityData() {
        List<City> list = cityDao.list();
    }

    private void showCountyData() {
        List<County> list = countyDao.list();
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

    /**
     * 从api获取城市数据
     */
    private void getRequest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(URL)
                .build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String data = response.body().string();
                        Log.i("获取城市信息。。。", "获取到信息大小：" + data.length() + "byte");
                        //json转换
                        Gson gson = new Gson();
                        AreaApi areaApi = gson.fromJson(data, AreaApi.class);
                        //处理+存储
                        AreaApiHandle.handle(areaApi);
                        //展示省份
                        showProvinceData();
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
