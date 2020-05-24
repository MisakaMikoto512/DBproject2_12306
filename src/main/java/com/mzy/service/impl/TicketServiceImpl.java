package com.mzy.service.impl;

import com.mzy.entity.Passenger;
import com.mzy.entity.Seat;
import com.mzy.entity.Ticket;
import com.mzy.entity.Travel;
import com.mzy.mapper.*;
import com.mzy.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
    @Autowired(required = false)
    private PassengerMapper passengerMapper;
    @Autowired(required = false)
    private TravelMapper travelMapper;
    @Autowired(required = false)
    SeatMapper seatMapper;
    @Override
    public List<TicketVO> dataToVO(List<Ticket> tickets) {
        List<TicketVO> ticketVOS=new ArrayList<>();
        for (Ticket oriTicket:tickets
             ) {
            TicketVO ticketVO=new TicketVO();
            Passenger passengerInfo=passengerMapper.selectById(oriTicket.getPassenger_id());
            Travel travelInfo=travelMapper.selectById(oriTicket.getTravel_id());
            ticketVO.setTicket_id(oriTicket.getTicket_id());
            ticketVO.setPassengerName(passengerInfo.getName());
            ticketVO.setId_card_number(passengerInfo.getId_card_number());
            ticketVO.setTrain_id(travelInfo.getTrain_id());
            ticketVO.setDepart_station(travelInfo.getDepart_station());
            ticketVO.setArrive_station(travelInfo.getArrive_station());
            ticketVO.setDate(oriTicket.getDate().toString());
            ticketVO.setDepartTime(travelInfo.getDepart_time().toString());
            ticketVO.setArriveTime(travelInfo.getArrive_time().toString());
            Seat seat=seatMapper.selectById(oriTicket.getSeat_id());
            ticketVO.setSeatType(seat.getSeat_type());
            ticketVO.setSeatNumber(seat.getSeat_number());
            ticketVO.setTicket_price(oriTicket.getTicket_price());
            ticketVO.setIsValid(1);
            ticketVOS.add(ticketVO);
        }
        return ticketVOS;
    }
}
