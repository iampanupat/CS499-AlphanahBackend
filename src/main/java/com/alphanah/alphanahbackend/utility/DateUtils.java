package com.alphanah.alphanahbackend.utility;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

public class DateUtils {

    public static String timeZoneConverter(Date date, DateTimeZone timeZone) {
        if (date == null)
            date = new Date();
        if (timeZone == null)
            timeZone = DateTimeZone.forID("Asia/Bangkok");
        DateTime dateTime = DateTime.parse(String.valueOf(date.toInstant())).withZone(timeZone);
        return String.valueOf(dateTime);
    }

}
