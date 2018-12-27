package com.zqh.android.weather.query;

import com.zqh.android.weather.db.City;
import com.zqh.android.weather.db.County;
import com.zqh.android.weather.db.Province;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class AreaApiHandle {
    /**
     * 用于处理获取到的城市信息,并保存至数据库
     */
    public static void handle(AreaApi areaApi) {
        List<AreaApi> list = areaApi.getList();
        List<Province> provinceList = new ArrayList<>();
        List<City> cityList = new ArrayList<>();
        List<County> countyList = new ArrayList<>();
        //遍历province
        for (AreaApi tempProvince : list) {
            String province_id = tempProvince.getCity_id();
            String province_en = tempProvince.getEn();
            String province_name = tempProvince.getName();
            //存储省级信息
            Province province = new Province()
                    .setProvince_id(province_id)
                    .setEn(province_en)
                    .setName(province_name);
            provinceList.add(province);

            List<AreaApi> list1 = tempProvince.getList();
            for (int i = 0; list1 != null && i < list1.size(); i++) {
                AreaApi tempCity = list1.get(i);
                String city_id = tempCity.getCity_id();
                String city_en = tempCity.getEn();
                String city_name = tempCity.getName();
                //存储城市信息
                City city = new City().setProvinceId(province_id)
                        .setCity_id(city_id)
                        .setEn(city_en)
                        .setName(city_name);
                cityList.add(city);

                List<AreaApi> list2 = tempCity.getList();
                for (int i1 = 0; list2 != null && i1 < list2.size(); i1++) {
                    AreaApi tempCounty = list2.get(i1);
                    String county_id = tempCounty.getCity_id();
                    String county_en = tempCounty.getEn();
                    String county_name = tempCounty.getName();
                    //存储县信息
                    County county = new County().setCityId(city_id)
                            .setCounty_id(county_id)
                            .setEn(county_en)
                            .setName(county_name);
                    countyList.add(county);
                }
            }
        }
        //存储到数据库
        DataSupport.saveAll(cityList);
        DataSupport.saveAll(provinceList);
        DataSupport.saveAll(countyList);
    }
}
