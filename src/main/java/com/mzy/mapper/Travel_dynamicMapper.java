package com.mzy.mapper;

import com.mzy.entity.Travel_dynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
public interface Travel_dynamicMapper extends BaseMapper<Travel_dynamic> {
    @Update("update travel_dynamic\n" +
            "set ${seat_type}=${seat_type}-1\n" +
            "where travel_id in\n" +
            "      (\n" +
            "          with x as (\n" +
            "              select train_id, depart_station_order, arrive_station_order\n" +
            "              from travel\n" +
            "              where travel_id = ${travel_id})\n" +
            "          select ts.travel_id\n" +
            "          from travel ts\n" +
            "                   join x on ts.train_id = x.train_id\n" +
            "              and not ((ts.depart_station_order >= x.arrive_station_order) or\n" +
            "                       (ts.arrive_station_order <= x.depart_station_order)));")
    void updateticket(String seat_type,int travel_id);

    @Update("update travel_dynamic\n" +
            "set ${seat_type}=${seat_type}+1\n" +
            "where travel_id in\n" +
            "      (\n" +
            "          with x as (\n" +
            "              select train_id, depart_station_order, arrive_station_order\n" +
            "              from travel\n" +
            "              where travel_id = ${travel_id})\n" +
            "          select ts.travel_id\n" +
            "          from travel ts\n" +
            "                   join x on ts.train_id = x.train_id\n" +
            "              and not ((ts.depart_station_order >= x.arrive_station_order) or\n" +
            "                       (ts.arrive_station_order <= x.depart_station_order)));")
    void resetRestTicket(String seat_type,int travel_id);

}
