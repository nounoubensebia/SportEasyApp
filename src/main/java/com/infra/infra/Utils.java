package com.infra.infra;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    public static String getTimeString(Time time)
    {
        int hours = time.getHours();
        int minutes = time.getMinutes();

        String hString = (hours<10)? ("0"+hours):hours+"";
        String mString = (minutes<10)? ("0"+minutes):minutes+"";

        return hString+":"+mString;

    }

    public static boolean checkName(String name)
    {
        String expression = "[a-zA-Z]+";
        return name.matches(expression);
    }

    public static boolean checkEmail (String email)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
