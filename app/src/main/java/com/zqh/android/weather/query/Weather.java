package com.zqh.android.weather.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
/**
 * 某次天气的接收类
 */
public class Weather {
    private String tq1;//白天天气
    private String tq2;//夜间天气，当与白天天气相同时，两者可合并为一个
    private String numtq1;//白天天气编码
    private String numtq2;//夜间天气编码
    private String qw1;//白天气温
    private String qw2;//夜间气温
    private String fl1;//白天风力
    private String numfl1;//白天风力编码
    private String fl2;//夜间风力
    private String numfl2;//夜间风力编码
    private String fx1;//白天风向
    private String numfx1;//白天风向编码
    private String fx2;//夜间风向，如白天风力风向与夜间风力风向一致，可合并为一行
    private String numfx2;//夜间风向编码
    private String date;//预报日期
}
