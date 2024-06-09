package com.anonymity.scraping.mutualfundanalysis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {
    public static String getFormatDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
