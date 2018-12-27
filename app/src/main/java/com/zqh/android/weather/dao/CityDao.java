package com.zqh.android.weather.dao;

import com.zqh.android.weather.db.City;
import org.litepal.crud.DataSupport;

import java.util.List;

public class CityDao {
    public List<City> list(){
        return DataSupport.findAll(City.class);
    }
}
