package com.mzy.service.impl;

import com.mzy.vo.CreateStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StationServiceImplTest {
    @Autowired
    StationServiceImpl stationService;
    @Test
    void add() {
        CreateStation createStation=new CreateStation();
        createStation.setProvince("瓦罗兰");
        createStation.setCity("祖安");
        createStation.setName("祖安");
        boolean flag=stationService.addStation(createStation);
        System.out.println(flag);
        int i=1;
    }
    @Test
    void delete(){
        String cannotDelete="广州南";
        boolean flag= stationService.deleteStation(cannotDelete);
        System.out.println(flag);
    }
}