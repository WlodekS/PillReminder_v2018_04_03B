package com.pum2018.pillreminder_v2018_04_03.Utility;

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


}
