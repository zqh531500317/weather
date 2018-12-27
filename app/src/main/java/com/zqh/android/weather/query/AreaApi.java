package com.zqh.android.weather.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.litepal.crud.DataSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

/**
 * 获取城市的接收类
 */
public class AreaApi extends DataSupport{
    private String city_id;
    private String name;
    private String en;
    private List<AreaApi> list;
}
