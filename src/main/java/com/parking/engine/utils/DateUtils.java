package com.parking.engine.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtils {
    public static String DD_MM_YYYY = "dd/MM/yyyy";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd hh:mm";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Convert String to date
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date convertStringToDate(String date, String dateFormat) {
        try {
            if (!StringUtils.isNotBlank(date)) return null;
            return new SimpleDateFormat(dateFormat).parse(date);
        } catch (ParseException e) {
            log.error("Error when convert string to date:", e);
        }
        return null;
    }

    /**
     * Convert Date to String
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateToStr(Date date, String pattern) {
        if (null == date) return null;
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

}
