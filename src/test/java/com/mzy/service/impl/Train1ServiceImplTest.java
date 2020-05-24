package com.mzy.service.impl;

import com.mzy.mapper.Train1Mapper;
import com.mzy.vo.AddStationInTrain;
import com.mzy.vo.DeleteStationFromTrainVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Train1ServiceImplTest {
    @Autowired
    Train1ServiceImpl train1Service;
    @Autowired
    TravelServiceImpl travelService;
    @Autowired(required = false)
    Train1Mapper train1Mapper;

    @Test
    void getScheduleVObyId() {
        train1Service.getScheduleVObyId("1133").forEach(System.out::println);
    }
    @Test
    void addstationintrain(){
        AddStationInTrain addStationInTrain=new AddStationInTrain();
        addStationInTrain.setTrain_id("G9999");
        addStationInTrain.setStation("祖安");
        train1Service.addStationIntrain(addStationInTrain);
    }
    @Test
    void de(){
        DeleteStationFromTrainVO deleteStationFromTrainVO=new DeleteStationFromTrainVO("祖安","G9999");
        train1Service.deleteStationFromtrain(deleteStationFromTrainVO);
    }
}