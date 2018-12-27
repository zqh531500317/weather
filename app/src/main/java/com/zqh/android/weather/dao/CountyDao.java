package com.zqh.android.weather.dao;

import com.zqh.android.weather.db.County;
import org.litepal.crud.DataSupport;

import java.util.List;

public class CountyDao {
    public List<County> list(){
        return DataSupport.findAll(County.class);
    }
}
