package com.zqh.android.weather.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.litepal.crud.DataSupport;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class City extends DataSupport {
    private String city_id;
    private String name;
    private String en;
    private String provinceId;

}
