package com.mzy.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TicketMapperTest {
    @Autowired
    private TicketMapper ticketMapper;
    @Test
    void deleteTest(){
        ticketMapper.deleteById(1);
    }

}