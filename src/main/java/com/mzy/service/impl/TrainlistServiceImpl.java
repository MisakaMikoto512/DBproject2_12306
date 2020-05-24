package com.mzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzy.entity.Train1;
import com.mzy.entity.Trainlist;
import com.mzy.entity.Travel;
import com.mzy.mapper.Train1Mapper;
import com.mzy.mapper.TrainlistMapper;
import com.mzy.mapper.TravelMapper;
import com.mzy.myUitls.StringAndTime;
import com.mzy.service.TrainlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.vo.CreateTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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
public class TrainlistServiceImpl extends ServiceImpl<TrainlistMapper, Trainlist> implements TrainlistService {
    @Autowired(required = false)
    TrainlistMapper trainlistMapper;
    @Autowired(required = false)
    TravelMapper travelMapper;
    @Autowired(required = false)
    Train1Mapper train1Mapper;
    @Autowired
    Train1ServiceImpl train1Service;
    public boolean addTrain(CreateTrain createTrain){
        try{
        Trainlist train=new Trainlist();
        train.setStart(createTrain.getDepart_station());
        train.setEnd(createTrain.getArrive_station());
        train.setTrain_id(createTrain.getTrain_id());
        train.setDistance(createTrain.getDistance());
        train.setDuration(createTrain.getDuration());
        train.setType("测试型号");
        Travel travel=new Travel();
        travel.setTrain_id(createTrain.getTrain_id());
        travel.setDepart_station(createTrain.getDepart_station());
        travel.setArrive_station(createTrain.getArrive_station());
        travel.setDepart_time(createTrain.getDepart_time());
        travel.setArrive_time(createTrain.getArrive_time());
        int differ=(travel.getArrive_time().toSecondOfDay()-travel.getDepart_time().toSecondOfDay())/60;
            System.out.println(differ);
        if (differ<=0){
            throw new Exception("时间小于0");
        }
        String duration= StringAndTime.minuteToChineseTime(differ);
        travel.setDuration(duration);
        trainlistMapper.insert(train);
        travelMapper.insert(travel);


        Train1 start=new Train1();
        start.setStation_order(1);
        start.setStation(createTrain.getDepart_station());
        start.setTrain_id(createTrain.getTrain_id());
        start.setDistance(0);
        start.setDepart_Time(createTrain.getDepart_time());
        start.setArrive_time(null);
        start.setDay(1);
        train1Mapper.insert(start);
        start.setStation_order(2);
        start.setStation(createTrain.getArrive_station());
        start.setDistance(createTrain.getDistance());
        start.setArrive_time(createTrain.getArrive_time());
        start.setDepart_Time(createTrain.getArrive_time().plusMinutes(10));
        train1Mapper.insert(start);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteTrain(String train_id){
        Trainlist trainlist=trainlistMapper.selectById(train_id);
        if(trainlist==null){
            return false;
        }
        trainlistMapper.deleteById(train_id);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("train_id",train_id);
        travelMapper.delete(queryWrapper);
        train1Mapper.delete(queryWrapper);
        return true;
    }

}
