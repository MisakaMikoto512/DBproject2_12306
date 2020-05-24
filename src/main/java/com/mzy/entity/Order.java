package com.mzy.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "ticket_order")
public class Order implements Serializable {
    static Random random=new Random();
    private static final long serialVersionUID=1L;
    @TableId(value = "order_id",type = IdType.ASSIGN_ID)
      private Long order_id;
    private String user_id;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Integer status;
    private Double order_price;

    public Order() {
        Integer key=random.nextInt(900000)+100000;
        Long time=System.currentTimeMillis()%99874273;
        this.order_id=key+time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }
}
