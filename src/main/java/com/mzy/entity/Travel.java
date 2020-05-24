package com.mzy.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.lang.String;
import java.io.Serializable;
import java.time.LocalTime;

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
public class Travel implements Serializable {

    public Travel() {
        soft_bed_price=new BigDecimal(40);

        hard_bed_price=new BigDecimal(30);

        soft_seat_price=new BigDecimal(20);

       hard_seat_price=new BigDecimal(10);
       day=1;
       depart_station_order=1;
       arrive_station_order=2;
    }

    private static final long serialVersionUID=1L;

      @TableId(value = "travel_id", type = IdType.AUTO)
      private Integer travel_id;

    private String train_id;

    private Integer depart_station_order;

    private String depart_station;

    private Integer arrive_station_order;

    private String arrive_station;

    private LocalTime depart_time;

    private LocalTime arrive_time;

    private BigDecimal soft_bed_price;

    private BigDecimal hard_bed_price;

    private BigDecimal soft_seat_price;

    private BigDecimal hard_seat_price;

    private String duration;

    private Integer day;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(Integer travel_id) {
        this.travel_id = travel_id;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public Integer getDepart_station_order() {
        return depart_station_order;
    }

    public void setDepart_station_order(Integer depart_station_order) {
        this.depart_station_order = depart_station_order;
    }

    public String getDepart_station() {
        return depart_station;
    }

    public void setDepart_station(String depart_station) {
        this.depart_station = depart_station;
    }

    public Integer getArrive_station_order() {
        return arrive_station_order;
    }

    public void setArrive_station_order(Integer arrive_station_order) {
        this.arrive_station_order = arrive_station_order;
    }

    public String getArrive_station() {
        return arrive_station;
    }

    public void setArrive_station(String arrive_station) {
        this.arrive_station = arrive_station;
    }

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

    public BigDecimal getSoft_bed_price() {
        return soft_bed_price;
    }

    public void setSoft_bed_price(BigDecimal soft_bed_price) {
        this.soft_bed_price = soft_bed_price;
    }

    public BigDecimal getHard_bed_price() {
        return hard_bed_price;
    }

    public void setHard_bed_price(BigDecimal hard_bed_price) {
        this.hard_bed_price = hard_bed_price;
    }

    public BigDecimal getSoft_seat_price() {
        return soft_seat_price;
    }

    public void setSoft_seat_price(BigDecimal soft_seat_price) {
        this.soft_seat_price = soft_seat_price;
    }

    public BigDecimal getHard_seat_price() {
        return hard_seat_price;
    }

    public void setHard_seat_price(BigDecimal hard_seat_price) {
        this.hard_seat_price = hard_seat_price;
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
