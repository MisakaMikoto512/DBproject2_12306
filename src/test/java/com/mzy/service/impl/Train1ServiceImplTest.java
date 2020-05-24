package com.mzy.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Train1ServiceImplTest {
    @Autowired
    Train1ServiceImpl train1Service;
    @Test
    void getScheduleVObyId() {
        train1Service.getScheduleVObyId("1133").forEach(System.out::println);
    }
}