package com.pum2018.pillreminder_v2018_04_03.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wlodek on 2018-04-13.
 */

public class MyUtil {

    public static String padR(String s, int n){
        return String.format("%1$-" + n + "s", s)  ;
    }
    public static String padL(String s, int n){
        return String.format("%1$" + n + "s", s)  ;
    }

    public static String padLZero(String s){
        //return String.format("%1$" + n + "s", s)  ;
        String sRet;
        if (s.length()>1){
            sRet = s; }
        else {
            sRet = "0"+s; }
        return sRet;
    }

    public static String get_yyyyMMddFromDate(Date dDate){
        String sDateString;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        sDateString = formater.format(dDate);
        return sDateString;
    }

    public static String get_HH_MM_fromDate(Date dDate){
        String sDateString;
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        sDateString = formater.format(dDate);
        return sDateString;
    }

    public static Integer get_DistanceTime(String time1, String time2){
        Integer iDistance;  //w minutach
        String sHours, sMinutes;
        Integer nHours, nMinutes;
        iDistance = 0;
        Integer nMinuteFromMidnight_1, nMinuteFromMidnight_2;
        Integer posColon;

        //time1 from midnight:
        posColon = time1.indexOf(":");
        sHours = time1.substring(0,posColon) ;
        sMinutes = time1.substring(posColon+1,time1.length());
        //to integer:
        nHours = Integer.parseInt(sHours);
        nMinutes = Integer.parseInt(sMinutes);
        //minutes from midnight:
        nMinuteFromMidnight_1 = (nHours*60)+nMinutes;

        //time2 from midnight:
        posColon = time2.indexOf(":");
        sHours = time2.substring(0,posColon) ;
        sMinutes = time2.substring(posColon+1,time2.length());
        //to integer:
        nHours = Integer.parseInt(sHours);
        nMinutes = Integer.parseInt(sMinutes);
        //minutes from midnight:
        nMinuteFromMidnight_2 = (nHours*60)+nMinutes;

        //Difference:
        iDistance = nMinuteFromMidnight_2 - nMinuteFromMidnight_1;

        return iDistance;
    }

    public static Integer getHoursFromStringTime(String time){
        String sHours, sMinutes;
        Integer nHours, nMinutes;
        Integer posColon;

        posColon = time.indexOf(":");
        sHours = time.substring(0,posColon) ;
        //sMinutes = time.substring(posColon+1,time.length());
        //to integer:
        nHours = Integer.parseInt(sHours);
        //nMinutes = Integer.parseInt(sMinutes);

        return nHours;
    }
    public static Integer getMinutesFromStringTime(String time){
        String sHours, sMinutes;
        Integer nHours, nMinutes;
        Integer posColon;

        posColon = time.indexOf(":");
        //sHours = time.substring(0,posColon) ;
        sMinutes = time.substring(posColon+1,time.length());
        //to integer:
        //nHours = Integer.parseInt(sHours);
        nMinutes = Integer.parseInt(sMinutes);

        return nMinutes;
    }
}
