package com.shangan.trade.order;

import com.shangan.trade.common.utils.SnowflakeIdWorker;
import com.shangan.trade.order.db.dao.OrderDao;
import com.shangan.trade.order.db.model.Order;
import com.shangan.trade.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OrderTest {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    private SnowflakeIdWorker snowFlake = new SnowflakeIdWorker(6, 8);

    @Test
    public void insertGoodsTest() {
        for (int i = 0; i < 48; i++) {
            System.out.println("Hello from OrderTest");
            Order order = new Order();

            long userId = new Random().nextLong();
            if (userId < 0) {
                userId = -userId;
            }
            order.setUserId(userId);
            System.out.println(userId);

            long orderId = snowFlake.nextId() + i + 1;
            order.setId(orderId);
            System.out.println(orderId);

            order.setGoodsId(12378L);
            order.setPayTime(new Date());
            order.setPayPrice(1999);
            order.setStatus(1);
            order.setActivityType(1);
            order.setCreateTime(new Date());
            boolean insertresult = orderDao.insertOrder(order);
            System.out.println(insertresult);
        }

    }
//
//    @Test
//    public void insertGoodsTest2() {
//        orderService.createOrder(123L, 2L);
//    }
//
//
//    @Test
//    public void updateTest2() {
//        Order order = orderService.queryOrder(121675526674866176L);
//        order.setStatus(98);
//        orderService.updateOrder(order);
//    }

}

