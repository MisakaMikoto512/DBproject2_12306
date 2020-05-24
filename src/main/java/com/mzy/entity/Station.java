package com.mzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Station implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;
    private String station_name;

    private String station_short_pinyin;

    private String station_full_pinyin;

    private String province_name;

    private String province_pinyin;

    private String city;

    public Station(Integer id, String station_name, String station_short_pinyin, String station_full_pinyin, String province_name, String province_pinyin, String city) {
        this.id = id;
        this.station_name = station_name;
        this.station_short_pinyin = station_short_pinyin;
        this.station_full_pinyin = station_full_pinyin;
        this.province_name = province_name;
        this.province_pinyin = province_pinyin;
        this.city = city;
    }

    public Station() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_short_pinyin() {
        return station_short_pinyin;
    }

    public void setStation_short_pinyin(String station_short_pinyin) {
        this.station_short_pinyin = station_short_pinyin;
    }

    public String getStation_full_pinyin() {
        return station_full_pinyin;
    }

    public void setStation_full_pinyin(String station_full_pinyin) {
        this.station_full_pinyin = station_full_pinyin;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_pinyin() {
        return province_pinyin;
    }

    public void setProvince_pinyin(String province_pinyin) {
        this.province_pinyin = province_pinyin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
