package com.mzy.controller;


import com.mzy.service.impl.StationServiceImpl;
import com.mzy.service.impl.Train1ServiceImpl;
import com.mzy.service.impl.TrainlistServiceImpl;
import com.mzy.vo.AddStationInTrain;
import com.mzy.vo.CreateStation;
import com.mzy.vo.CreateTrain;
import com.mzy.vo.DeleteStationFromTrainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Controller
@RequestMapping()
public class StationController {
    @Autowired
    StationServiceImpl stationService;
    @Autowired
    Train1ServiceImpl train1Service;
    @Autowired
    TrainlistServiceImpl trainlistService;
    @PostMapping("/manage/add_station")
    String addstation(@RequestBody CreateStation createStation){
        stationService.addStation(createStation);
        return "添加成功";
    }
    @PostMapping("/manage/delete_station_from_train")
    int delete_station_from_train(@RequestBody DeleteStationFromTrainVO deleteStationFromTrainVO){
        if( train1Service.deleteStationFromtrain(deleteStationFromTrainVO)){
            return 1;
        }
        else return 0;

    }
    @PostMapping("/manage/add_station_to_train")
    int add_station_to_train(@RequestBody AddStationInTrain addStationInTrain){
        if(train1Service.addStationIntrain(addStationInTrain)){
            return 1;
        }
        else return 0;
    }
    @GetMapping("/manage/delete_train/{train_id}")
    int delete_train(@PathVariable String train_id){
        if(trainlistService.deleteTrain(train_id)){
            return 1;
        }
        else return 0;
    }

    @GetMapping("/manage/delete_station/{station_name}")
    int delete_station(@PathVariable String station_name){
        if(trainlistService.deleteTrain(station_name)){
            return 1;
        }
        else return 0;
    }
    @PostMapping("/manage/add_train")
    int add_train(@RequestBody CreateTrain createTrain ){
        if(trainlistService.addTrain(createTrain)){
            return 1;
        }
        else return 0;
    }
    @PostMapping("/manage/add_station")
    int addStation(@RequestBody CreateStation createStation){
        if(stationService.addStation(createStation)){
            return 1;
        }
        else return 0;
    }


}

