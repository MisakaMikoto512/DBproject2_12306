package com.mzy.entity;

import java.time.LocalTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName(value = "train_schedule")
public class Train1 implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("train_id")
    private String train_id;

    @TableField("train_type")
    private String Type;

    @TableField("station")
    private String station;

    @TableField("s_no")
    private Integer station_order;

    @TableField("day")
    private Integer day;

    @TableField("a_time")
    private LocalTime arrive_time;

    @TableField("d_time")
    private LocalTime depart_Time;

    @TableField("r_date")
    private String duration;

    @TableField("distance")
    private Integer Distance;

    @TableField("p1")
    private String P1;

    @TableField("p2")
    private String P2;

    @TableField("p3")
    private String P3;

    @TableField("p4")
    private String P4;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getStation_order() {
        return station_order;
    }

    public void setStation_order(Integer station_order) {
        this.station_order = station_order;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public LocalTime getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(LocalTime arrive_time) {
        this.arrive_time = arrive_time;
    }

    public LocalTime getDepart_Time() {
        return depart_Time;
    }

    public void setDepart_Time(LocalTime depart_Time) {
        this.depart_Time = depart_Time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }

    public String getP1() {
        return P1;
    }

    public void setP1(String p1) {
        P1 = p1;
    }

    public String getP2() {
        return P2;
    }

    public void setP2(String p2) {
        P2 = p2;
    }

    public String getP3() {
        return P3;
    }

    public void setP3(String p3) {
        P3 = p3;
    }

    public String getP4() {
        return P4;
    }

    public void setP4(String p4) {
        P4 = p4;
    }

    public Train1(String train_id, String type, String station, Integer station_order, Integer day, LocalTime arrive_time,LocalTime depart_Time, String duration, Integer distance, String p1, String p2, String p3, String p4) {
        this.train_id = train_id;
        Type = type;
        this.station = station;
        this.station_order = station_order;
        this.day = day;
        this.arrive_time = arrive_time;
        this.depart_Time = depart_Time;
        this.duration = duration;
        Distance = distance;
        P1 = p1;
        P2 = p2;
        P3 = p3;
        P4 = p4;
    }

    public Train1() {
        P1="10";
        P2="20";
        P3="30";
        P4="40";
        depart_Time=LocalTime.now();
        arrive_time=LocalTime.now();
        Type="测试豪华车";
        day=1;
    }
}
