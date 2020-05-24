package com.mzy.test1;

import com.mzy.mapper.SearchMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootTest
class SearchMapperTest {
    @Autowired
    private SearchMapper mapper;
    @Test
    void stationSearch() {
        mapper.stationSearch("北京","包头").forEach(System.out::println);
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
//    @Test
//    void testConnection(){
//        jdbcTemplate.query("select * from seat_type",new RowMapper[]);
//    }
}