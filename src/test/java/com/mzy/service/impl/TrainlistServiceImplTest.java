package com.mzy.service.impl;

import com.mzy.entity.Trainlist;
import com.mzy.vo.CreateStation;
import com.mzy.vo.CreateTrain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrainlistServiceImplTest {
    @Autowired(required = false)
    TrainlistServiceImpl trainlistService;

    @Test
    void addTrain() {
        CreateTrain createTrain=new CreateTrain();
        createTrain.setDepart_station("艾欧尼亚");
        createTrain.setArrive_station("德玛西亚");
        boolean flag=trainlistService.addTrain(createTrain);
        System.out.println(flag);
    }

    @Test
    void deleteTrain() {
        System.out.println(trainlistService.deleteTrain("G9999"));


    }
}