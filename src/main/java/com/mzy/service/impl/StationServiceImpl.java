package com.mzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzy.entity.Station;
import com.mzy.entity.Travel;
import com.mzy.mapper.StationMapper;
import com.mzy.mapper.TravelMapper;
import com.mzy.service.StationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.vo.CreateStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {
    @Autowired(required = false)
    StationMapper stationMapper;
    @Autowired(required = false)
    TravelMapper travelMapper;
    public boolean addStation(CreateStation createStation){
        boolean flag=true;
        try {
            Station station=new Station();
            station.setCity(createStation.getCity());
            station.setProvince_name(createStation.getProvince());
            station.setStation_name(createStation.getName());
            stationMapper.insert(station);
        }catch (Exception e){
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }
    public boolean deleteStation(String station_name){
        QueryWrapper<Travel> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("depart_station",station_name).or().eq("arrive_station",station_name);
        List<Travel> list=travelMapper.selectList(queryWrapper);
        if(list==null||list.size()==0){
            QueryWrapper<Station> queryWrapper1=new QueryWrapper<>();
            queryWrapper.eq("station_name",station_name);
            stationMapper.delete(queryWrapper1);
            return true;
        }
        else return false;
    }
}
