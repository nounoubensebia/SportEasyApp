package com.infra.infra;

import java.sql.Time;
import java.time.LocalDateTime;

public class Utils {


    public static String getTimeString(Time time)
    {
        int hours = time.getHours();
        int minutes = time.getMinutes();

        String hString = (hours<10)? ("0"+hours):hours+"";
        String mString = (minutes<10)? ("0"+minutes):minutes+"";

        return hString+":"+mString;

    }

}
