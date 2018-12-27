package com.zqh.android.weather.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
/**
 * 天气情况的接收类
 */
public class WeatherInfo {
    private String cityId;
    private String cityName;
    private String sj;
    private List<Weather> list;
}
