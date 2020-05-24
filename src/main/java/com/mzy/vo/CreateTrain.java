package com.mzy.vo;

import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class CreateTrain {
    String train_id;
    String depart_station;
    String arrive_station;
    String duration;
    int distance;
    LocalTime depart_time;
    LocalTime arrive_time;

    public LocalTime getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(LocalTime depart_time) {
        this.depart_time = depart_time;
    }

    public LocalTime getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(LocalTime arrive_time) {
        this.arrive_time = arrive_time;
    }

    public CreateTrain(String train_id, String depart_station, String arrive_station, String duration, int distance) {
        this.train_id = train_id;
        this.depart_station = depart_station;
        this.arrive_station = arrive_station;
        this.duration = duration;
        this.distance = distance;
    }

    public CreateTrain() {
        this.depart_time=LocalTime.parse("08:40", DateTimeFormatter.ofPattern("HH:mm"));
        this.arrive_time=LocalTime.parse("10:30", DateTimeFormatter.ofPattern("HH:mm"));
        this.train_id = "G9999";
        this.depart_station="南极";
        this.arrive_station="广州南";
        this.distance=1000;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getDepart_station() {
        return depart_station;
    }

    public void setDepart_station(String depart_station) {
        this.depart_station = depart_station;
    }

    public String getArrive_station() {
        return arrive_station;
    }

    public void setArrive_station(String arrive_station) {
        this.arrive_station = arrive_station;
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
