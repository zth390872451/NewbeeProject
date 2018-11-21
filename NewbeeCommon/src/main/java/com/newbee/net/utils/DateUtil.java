package com.newbee.net.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zheng.th
 * @Date: 2018/11/21 16:03
 */
public class DateUtil {

    private static Logger LOGGER =  LoggerFactory.getLogger(DateUtil.class);


    /**
     * 判断是否是对应的格式的日期字符串
     * @param dateStr
     * @param partten
     * @return
     */
    public static boolean isRightDateStr(String dateStr,String partten){
        DateFormat dateFormat  = new SimpleDateFormat(partten);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date)dateFormat.parse(dateStr);
            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if(dateStr.equals(newDateStr)){
                return true;
            }else {
                LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",dateStr,partten);
                return false;
            }
        } catch (ParseException e) {
            LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",dateStr,partten);
            return false;
        }
    }
}
