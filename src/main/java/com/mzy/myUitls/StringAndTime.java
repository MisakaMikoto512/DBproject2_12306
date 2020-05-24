package com.mzy.myUitls;


import java.time.LocalDate;

public class StringAndTime {
    public static String dateToString(LocalDate localDate){
        return null;
    }
    public static String minuteToChineseTime(int minute){
        int hour=minute/60;
        int minut=minute%60;
        StringBuilder sb=new StringBuilder();
        sb.append(hour).append("时").append(minut).append("分");
        return sb.toString();
    }

}
