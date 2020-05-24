package com.mzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzy.entity.Train1;
import com.mzy.entity.Trainlist;
import com.mzy.entity.Travel;
import com.mzy.mapper.Train1Mapper;
import com.mzy.mapper.TrainlistMapper;
import com.mzy.mapper.TravelMapper;
import com.mzy.myUitls.StringAndTime;
import com.mzy.service.Train1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.vo.AddStationInTrain;
import com.mzy.vo.DeleteStationFromTrainVO;
import com.mzy.vo.ScheduleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Service
public class Train1ServiceImpl extends ServiceImpl<Train1Mapper, Train1> implements Train1Service {
    @Autowired(required = false)
    private Train1Mapper train1Mapper;
    @Autowired(required = false)
    TravelMapper travelMapper;
    @Autowired(required = false)
    TrainlistMapper trainlistMapper;

    public List<ScheduleVO> getScheduleVObyId(String train_id) {
        List<ScheduleVO> scheduleVOS = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("train_id", train_id);
        List<Train1> train1s = train1Mapper.selectByMap(hashMap);
        for (Train1 t : train1s
        ) {
            ScheduleVO scheduleVO = new ScheduleVO();
            scheduleVO.setStation_order(t.getStation_order());
            scheduleVO.setStation(t.getStation());
            scheduleVO.setTrain_id(t.getTrain_id());
            scheduleVO.setDepart_time(t.getDepart_Time().toString());
            scheduleVO.setArriveTime(t.getArrive_time().toString());
            scheduleVO.setDuration(t.getDuration());
            scheduleVO.setDay(t.getDay());
            scheduleVOS.add(scheduleVO);
        }
        return scheduleVOS;
    }

    public boolean addStationIntrain(AddStationInTrain addStationInTrain) {
        try {
            QueryWrapper<Train1> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("train_id", addStationInTrain.getTrain_id()).orderByAsc("s_no");
            List<Train1> schedule = train1Mapper.selectList(queryWrapper);
            int size = schedule.size();
            if (size > 0) {
                Train1 startSchedule = schedule.get(0);

                Train1 newSchedule = new Train1();
                newSchedule.setTrain_id(addStationInTrain.getTrain_id());
                newSchedule.setType("测试豪华车");//TODO
                newSchedule.setStation_order(size + 1);
                newSchedule.setStation(addStationInTrain.getStation());
                newSchedule.setDay(1);//TODO
                newSchedule.setArrive_time(LocalTime.parse(addStationInTrain.getArrive_time(), DateTimeFormatter.ofPattern("HH:mm")));
                newSchedule.setDepart_Time(LocalTime.parse(addStationInTrain.getDepart_time(), DateTimeFormatter.ofPattern("HH:mm")));
                newSchedule.setDistance(addStationInTrain.getDistance());
                int minDiffer = (newSchedule.getArrive_time().toSecondOfDay() - startSchedule.getDepart_Time().toSecondOfDay()) / 60;
                newSchedule.setDuration(StringAndTime.minuteToChineseTime(minDiffer));

                train1Mapper.insert(newSchedule);

                Travel travel = new Travel();
                travel.setTrain_id(addStationInTrain.getTrain_id());
                travel.setArrive_station_order(newSchedule.getStation_order());//最后一站
                travel.setArrive_station(newSchedule.getStation());
                travel.setArrive_time(newSchedule.getArrive_time());
                travel.setDuration(newSchedule.getDuration());
                travel.setDay(1);
                for (int i = 0; i < schedule.size(); i++) {
                    Train1 train1 = schedule.get(i);
                    travel.setDepart_station(train1.getStation());
                    travel.setDepart_station_order(train1.getStation_order());
                    travel.setDepart_time(train1.getDepart_Time());
                    travelMapper.insert(travel);
                }
            } else {//插入第一站
                Train1 newSchedule = new Train1();
                newSchedule.setTrain_id(addStationInTrain.getTrain_id());
                newSchedule.setType("测试豪华车型");//TODO
                newSchedule.setStation_order(size + 1);
                newSchedule.setStation(addStationInTrain.getStation());
                newSchedule.setDay(1);//TODO
                newSchedule.setArrive_time(LocalTime.parse(addStationInTrain.getArrive_time(), DateTimeFormatter.ofPattern("HH:mm")));
                newSchedule.setDepart_Time(LocalTime.parse(addStationInTrain.getDepart_time(), DateTimeFormatter.ofPattern("HH:mm")));
                newSchedule.setDistance(addStationInTrain.getDistance());
                newSchedule.setDuration(null);
                train1Mapper.insert(newSchedule);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStationFromtrain(DeleteStationFromTrainVO stationTodelete) {


        QueryWrapper<Train1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_id", stationTodelete.getTrain_id());
        queryWrapper.eq("station", stationTodelete.getStation());
        List<Train1> list = train1Mapper.selectList(queryWrapper);
        if (list == null || list.size() == 0) {
            return false;
        }
        train1Mapper.delete(queryWrapper);
        List<Train1> train1s = train1Mapper.selectList(queryWrapper);
//        for (int i = 0; i <train1s.size(); i++) {
//            if(train1s.get(i).getStation().equals(stationTodelete.getStation())){
//                train1s.remove(i);
//                break;
//            }
//        }

        for (int i = 0; i < train1s.size(); i++) {
            Train1 train1 = train1s.get(i);
            train1.setStation_order(i + 1);
            QueryWrapper<Train1> orderwrapper = new QueryWrapper<>();
            orderwrapper.eq("station", train1.getStation());
            train1Mapper.update(train1, orderwrapper);
        }
        QueryWrapper<Travel> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("train_id", stationTodelete.getStation());
        queryWrapper1.eq("depart_station", stationTodelete.getStation()).or().eq("arrive_station", stationTodelete.getStation());
        travelMapper.delete(queryWrapper1);
        return true;

    }


}
