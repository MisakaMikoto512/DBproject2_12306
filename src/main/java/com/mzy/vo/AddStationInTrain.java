package com.mzy.vo;

import lombok.Data;

@Data
public class AddStationInTrain {
    String date;
    String train_id;
    String station;
    int station_order;
    int day;
    String depart_time;
    String arrive_time;
    String duration;
    int distance;

    public AddStationInTrain(String train_id, String station, int station_order, int day, String depart_time, String arrive_time, String duration, int distance) {
        this.train_id = train_id;
        this.station = station;
        this.station_order = station_order;
        this.day = day;
        this.depart_time = depart_time;
        this.arrive_time = arrive_time;
        this.duration = duration;
        this.distance = distance;
    }

    public AddStationInTrain() {
        this.train_id = "G133";
        this.station = "新增的车站名";
        this.station_order = 3;
        this.day = 1;
        this.depart_time = "07:00";
        this.arrive_time = "08:01";
        this.duration = "1小时1分";
        this.distance = 1000;
    }

    public AddStationInTrain(String date, String train_id, String station, int station_order, int day, String depart_time, String arrive_time, String duration, int distance) {
        this.date = date;
        this.train_id = train_id;
        this.station = station;
        this.station_order = station_order;
        this.day = day;
        this.depart_time = depart_time;
        this.arrive_time = arrive_time;
        this.duration = duration;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getStation_order() {
        return station_order;
    }

    public void setStation_order(int station_order) {
        this.station_order = station_order;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
