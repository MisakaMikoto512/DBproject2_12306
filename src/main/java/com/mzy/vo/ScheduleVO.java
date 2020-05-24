package com.mzy.vo;

import lombok.Data;

@Data
public class ScheduleVO {
    Integer station_order;
    String station;
    String train_id;//车次
    String depart_time;//出发时间，可以为空
    String arriveTime;//到达时间，可以为空
    String duration;//历时
    Integer day;//1为当日到达，2为次日到达，以此类推

    public ScheduleVO(int station_order, String station, String train_id, String depart_time, String arriveTime, String duration, int day) {
        this.station_order = station_order;
        this.station = station;
        this.train_id = train_id;
        this.depart_time = depart_time;
        this.arriveTime = arriveTime;
        this.duration = duration;
        this.day = day;
    }

    public ScheduleVO(int t) {
        this.station_order = 1;
        this.station = "北京西";
        this.train_id = "1133";
        this.depart_time = depart_time;
        this.arriveTime = arriveTime;
        this.duration = duration;
        this.day = day;
    }
    public ScheduleVO() {

    }

    public Integer getStation_order() {
        return station_order;
    }

    public void setStation_order(Integer station_order) {
        this.station_order = station_order;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
