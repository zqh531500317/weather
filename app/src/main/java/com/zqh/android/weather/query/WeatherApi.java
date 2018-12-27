package com.zqh.android.weather.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

/**
 * 获取天气的接收类
 */
public class WeatherApi {
    private int code;
    private String msg;
    private WeatherInfo data;
}
