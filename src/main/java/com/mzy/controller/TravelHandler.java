package com.mzy.controller;


import com.mzy.entity.SearchForTravel;
import com.mzy.entity.Travel;
import com.mzy.mapper.SearchMapper;
import com.mzy.mapper.TravelMapper;
import com.mzy.service.Train1Service;
import com.mzy.service.TravelService;
import com.mzy.service.impl.Train1ServiceImpl;
import com.mzy.service.impl.TravelServiceImpl;
import com.mzy.vo.ReservationReturnVO;
import com.mzy.vo.ReserveVO;
import com.mzy.vo.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")

public class TravelHandler {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    TravelServiceImpl travelService;
    @Autowired
    Train1ServiceImpl train1Service;
//    private TravelRepository travelRepository=new TravelReposityImpl();
//    @GetMapping("/findTravelByStation/{depart}/{arrive}")
//    public List<Travel> findByStation(@PathVariable("depart") String depart, @PathVariable("arrive") String arrive){
//        return travelReposity.findByStation(depart,arrive);
//    }
    //按站查询
    @GetMapping("/station/{depart}/{arrive}/{date}")
    public List<SearchForTravel> findByStation(@PathVariable("depart") String depart, @PathVariable("arrive") String arrive,@PathVariable("date") String date){
        return searchMapper.stationSearch(depart,arrive);
    }
    //按车次查询
    @GetMapping("/train_id/{train_id}")
    public List<ScheduleVO> getSchedule(@PathVariable("train_id") String train_id){
        return train1Service.getScheduleVObyId(train_id);
    }

    }

