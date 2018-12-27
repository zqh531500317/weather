package com.zqh.android.weather.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.litepal.crud.DataSupport;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class County extends DataSupport {
    private String county_id;
    private String name;
    private String en;
    private String cityId;

}
