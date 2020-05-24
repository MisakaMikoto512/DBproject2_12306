package com.mzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.mzy.entity.*;
import com.mzy.mapper.*;
import com.mzy.enumpackege.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzy.service.OrderService;
import com.mzy.service.PassengerService;
import com.mzy.service.TicketService;
import com.mzy.vo.OrderVO;
import com.mzy.vo.PassengerVO;
import com.mzy.vo.ReservationReturnVO;
import com.mzy.vo.ReserveVO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Wrapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    public static final int REFUND=2;
    @Autowired
    private Travel_dynamicMapper travel_dynamicMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    PassengerMapper passengerMapper;
    @Autowired
    SeatMapper seatMapper;

    @Override
    public List<OrderVO> findOrderVOByUser(String userId) {
        HashMap<Integer, String> stateMap = new HashMap<>();
        stateMap.put(0, "订票成功");
        stateMap.put(1, "信息有误");
        stateMap.put(2, "退票成功");

        List<OrderVO> orderVOs = new ArrayList<>();
        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("user_id", userId);

        List<Order> orderData = orderMapper.selectByMap(hashMap1);
        for (Order oriorder : orderData) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(oriorder, orderVO);
            orderVO.setStatus(stateMap.get(oriorder.getStatus()));
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("order_id", oriorder.getOrder_id());
            List<Ticket> tickets = ticketMapper.selectByMap(hashMap2);
            orderVO.setTickets(ticketService.dataToVO(tickets));
            orderVOs.add(orderVO);
        }
        return orderVOs;
    }

    public OrderVO orderToVO(Order oriorder){
        HashMap<Integer, String> stateMap = new HashMap<>();
        stateMap.put(0, "订票成功");
        stateMap.put(1, "信息有误");
        stateMap.put(2, "退票成功");
        OrderVO orderVO=new OrderVO();
        BeanUtils.copyProperties(oriorder, orderVO);
        orderVO.setStatus(stateMap.get(oriorder.getStatus()));
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("order_id", oriorder.getOrder_id());
        List<Ticket> tickets = ticketMapper.selectByMap(hashMap2);
        orderVO.setTickets(ticketService.dataToVO(tickets));
       return orderVO;
    }

    public ReservationReturnVO createOrder(ReserveVO reserveVO) {
        Order order = new Order();//创建订单
        Long order_id = order.getOrder_id();
        List<PassengerVO> passengerVOS = reserveVO.getPassengers();
        String date = reserveVO.getDate();
        int travel_id = reserveVO.getTravel_id();
        HashMap<String,Object> hashMap=new ManagedMap();
        hashMap.put("travel_id",travel_id);
        Travel_dynamic travel_dynamic = travel_dynamicMapper.selectByMap(hashMap).get(0);//TODO
        Double totalPrice=0.0;

        if (checkRestTicket(travel_dynamic, passengerVOS)) {//检查余票是否足够
            for (PassengerVO p : passengerVOS) {
                Ticket ticket = reserveSingleTicket(p, travel_id, date, order_id);//生成一张新票并更新余票
                totalPrice+=ticket.getTicket_price();
                ticketMapper.insert(ticket);
            }
            order.setCreate_time(LocalDateTime.now());
            order.setStatus(orderMessage.RESERVE_SUCCESS.getCode());
            order.setUser_id(reserveVO.getUser_id());
            order.setOrder_price(totalPrice);
            order.setUpdate_time(LocalDateTime.now());
            orderMapper.insert(order);
            ReservationReturnVO reservationReturnVO = new ReservationReturnVO(orderMessage.RESERVE_SUCCESS.getCode(), orderMessage.RESERVE_SUCCESS.getMessage(), order_id);
            return reservationReturnVO;
        }
        return new ReservationReturnVO(orderMessage.RESERVE_ERROR.getCode(), orderMessage.RESERVE_ERROR.getMessage(), order_id);


    }


    //生成一张新票并更新余票
    public Ticket reserveSingleTicket(PassengerVO passengerVO, int travel_id, String date, Long orderid) {

        String seatType = passengerVO.getSeat_type();
        updateRestTicket(seatType,travel_id);
        Ticket ticket = new Ticket();
        ticket.setTravel_id(travel_id);
        ticket.setDate(date);
        ticket.setTicket_price(40.0);//TODO
        ticket.setTicket_entrance("检票口1");
        int seat_id=seatMapper.findAvailableSeat(travel_id,seatType).get(0).getSeat_id();//TODO
        ticket.setSeat_id(seat_id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id_card_number", passengerVO.getId_card_number());
        List<Passenger> p=passengerMapper.selectByMap(hashMap);
        ticket.setOrder_id(orderid);
        if(p!=null){
        int passengerId = passengerMapper.selectByMap(hashMap).get(0).getPassenger_id();
        ticket.setPassenger_id(passengerId);
        return ticket;
        }
        else {
            return ticket;
        }
    }
    public boolean checkRestTicket(Travel_dynamic travel_dynamic, List<PassengerVO> passengerVOs) {
        int hard_seat = travel_dynamic.getHard_seat_rest_ticket();
        int hard_bed = travel_dynamic.getHard_bed_rest_ticket();
        int soft_seat = travel_dynamic.getSoft_seat_rest_ticket();
        int soft_bed = travel_dynamic.getSoft_bed_rest_ticket();
        for (PassengerVO p : passengerVOs
        ) {
            switch (p.getSeat_type()) {
                case "硬座":
                    hard_seat--;
                    break;
                case "硬卧":
                    hard_bed--;
                    break;
                case "软座":
                    soft_seat--;
                    break;
                case "软卧":
                    soft_bed--;
                    break;
                default:
                    return false;
            }
        }
        if (hard_bed < 0 || hard_seat < 0 || soft_seat < 0 || soft_bed < 0) {
            return false;
        }
        else return true;
    }
    void updateRestTicket(String seattype,int travel_id){
        switch (seattype){
            case("硬座"):
                travel_dynamicMapper.updateticket("hard_seat_rest_ticket",travel_id);
                break;
            case("软座"):
                travel_dynamicMapper.updateticket("soft_seat_rest_ticket",travel_id);
                break;
            case("硬卧"):
                travel_dynamicMapper.updateticket("hard_bed_rest_ticket",travel_id);
                break;
            case("软卧"):
                travel_dynamicMapper.updateticket("soft_bed_rest_ticket",travel_id);
                break;
            default:
        }
    }
    void resetRestTicket(String seattype,int travel_id){
        switch (seattype){
            case("硬座"):
                travel_dynamicMapper.resetRestTicket("hard_seat_rest_ticket",travel_id);
                break;
            case("软座"):
                travel_dynamicMapper.resetRestTicket("soft_seat_rest_ticket",travel_id);
                break;
            case("硬卧"):
                travel_dynamicMapper.resetRestTicket("hard_bed_rest_ticket",travel_id);
                break;
            case("软卧"):
                travel_dynamicMapper.resetRestTicket("soft_bed_rest_ticket",travel_id);
                break;
            default:
        }
    }
    public boolean refund(Long order_id){
       Order currentOrder=orderMapper.selectById(order_id);
       if(currentOrder!=null){
           currentOrder.setStatus(REFUND);
           orderMapper.updateById(currentOrder);
       }
       QueryWrapper<Ticket> queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("order_id",order_id);
       List<Ticket> tickets=ticketMapper.selectList(queryWrapper);
       if(tickets==null||tickets.size()==0){
           return false;
       }
       else {
           for (Ticket t:tickets
                ) {
                Seat seat=seatMapper.selectById(t.getSeat_id());//找到座位类型
               resetRestTicket(seat.getSeat_type(),t.getTravel_id());//让余票加1
               ticketMapper.deleteById(t.getTicket_id());

           }
       }
        return true;
    }


}
