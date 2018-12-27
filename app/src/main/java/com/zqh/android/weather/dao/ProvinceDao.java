package com.zqh.android.weather.dao;

import com.zqh.android.weather.db.Province;
import org.litepal.crud.DataSupport;

import java.util.List;

public class ProvinceDao {
    public List<Province> list() {
        return DataSupport.findAll(Province.class);
    }

    public boolean isEmpty() {
        return DataSupport.count(Province.class) == 0;
    }
}
