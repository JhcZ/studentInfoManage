package util;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ParseUtil {
    public static Date parseDateLong(String sDate) {
        try {
            return new Date(new SimpleDateFormat("yyyy-M-d", Locale.CHINA).parse(sDate).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    // 获取指定格式的当前日期字符串
    public static String today(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new java.util.Date());
    }
    // 获取指定格式的当前日期时间字符串
    public static String now(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.CHINA).format(new java.util.Date());
    }
}
