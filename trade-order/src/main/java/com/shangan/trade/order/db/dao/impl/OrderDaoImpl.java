package com.shangan.trade.order.db.dao.impl;

import com.shangan.trade.order.db.dao.OrderDao;
import com.shangan.trade.order.db.mappers.OrderMapper;
import com.shangan.trade.order.db.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean insertOrder(Order order) {
        //log.info("In OrderDaoImpl.insertOrder(): before");
        int result = orderMapper.insert(order);
        log.info("In OrderDaoImpl.insertOrder(): after");
        //大于0 表示插入成功
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        int result = orderMapper.deleteByPrimaryKey(id);
        return result > 0;
    }


    @Override
    public Order queryOrderById(long id) {
        Order goods = orderMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Override
    public boolean updateOrder(Order order) {
        int result = orderMapper.updateByPrimaryKey(order);
        return result > 0;
    }
}
