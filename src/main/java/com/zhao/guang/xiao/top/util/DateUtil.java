package com.zhao.guang.xiao.top.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换工具类
 *
 * @author dnkj011
 */
public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfYm = new SimpleDateFormat("yyyy-MM");

    /**
     * 将时间字符串转换为时间戳
     *
     * @param s 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long timeToStamp(String s) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return ts;
    }

    /**
     * 将时间戳转换为时间字符串格式
     *
     * @param s
     * @return 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String stampToTime(String s) {
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @return 格式 ： yyyy-MM-dd HH:mm:ss
     */
    public static String dateToTime(Date date) {
        return simpleDateFormat.format(date);
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @return 格式 ： yyyy-MM-dd
     */
    public static String dateToTime1(Date date) {
        return sdf.format(date);
    }

    /**
     * 字符串转时间
     *
     * @param s 格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date timeToDate(String s) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转时间
     *
     * @param s 格式 yyyy-MM-dd
     * @return
     */
    public static Date timeToDate1(String s) {
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转时间
     *
     * @param s 格式 yyyy-MM-dd
     * @return
     */
    public static Date timeToDate2(String s) throws Exception {
        Date date = null;
        sdf = new SimpleDateFormat("yyyy#MM#dd");
        date = sdf.parse(s);
        return date;
    }

    /**
     * 转化为 yyyyMMdd 格式字符串
     *
     * @param date
     * @return
     */
    public static String timeForDateString(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }


    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author qzm
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    public static Date toYearAndMonths(String date) {
        try {
            Date parse = sdfYm.parse(date);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return null;
    }


    public static void main(String[] args) {
        String dateToTime1 = dateToTime1(new Date());
        System.out.println(dateToTime1);
        long daySub = getDaySub("2019-06-15", "2019-07-15");
        System.out.println(daySub);

    }
}
