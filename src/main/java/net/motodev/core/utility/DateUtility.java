package net.motodev.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by oksuz on 19/05/2017.
 */
public class DateUtility {

    public static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static Date fromISODateFormat(String date) {
        try {
            return new SimpleDateFormat(ISO8601_DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toISODateFormat(Date d) {
        return new SimpleDateFormat(ISO8601_DATE_FORMAT).format(d);
    }

    public static Date getBeginningOfDay(Date d) {
        return getDateWithTime(d, 0, 0, 0);
    }

    public static Date getEndOfDay(Date d) {
        return getDateWithTime(d, 23, 59, 59);
    }

    private static Date getDateWithTime(Date d, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }
}
