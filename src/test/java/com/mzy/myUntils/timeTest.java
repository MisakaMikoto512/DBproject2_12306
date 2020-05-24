package com.mzy.myUntils;


import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class timeTest {

    //字符串转时间
    @Test
    void testTime(){
        String time="15:15";
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH/mm");
        LocalTime localTime=LocalTime.parse(time,dateTimeFormatter);
        System.out.println(localTime.toString());
    }
    //时间转字符串
    @Test
    void testTime2(){
        LocalTime localTime=LocalTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm");
        String time=localTime.format(dateTimeFormatter);
        System.out.println(time);
    }
}
