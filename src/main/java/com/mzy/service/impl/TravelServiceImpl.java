package com.mzy.service.impl;

import com.mzy.entity.Travel;
import com.mzy.mapper.TravelMapper;
import com.mzy.service.TravelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.vo.ScheduleVO;
import org.springframework.stereotype.Service;

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
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements TravelService {
        List<ScheduleVO> scheduleVOS(){
            return null;
        }
}
