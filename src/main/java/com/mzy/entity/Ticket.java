package com.mzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Ticket implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "ticket_id", type = IdType.AUTO)
      private Integer ticket_id;
      private Integer passenger_id;//TODO
      private Integer travel_id;//TODO
      private String date;
      private Double ticket_price;
      private String ticket_entrance;
      private Integer seat_id;
      private Long order_id;
      @TableLogic
      private Integer is_valid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(Integer travel_id) {
        this.travel_id = travel_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getTicket_entrance() {
        return ticket_entrance;
    }

    public void setTicket_entrance(String ticket_entrance) {
        this.ticket_entrance = ticket_entrance;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}
