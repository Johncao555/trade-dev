package com.shangan.trade.order;

import com.shangan.trade.order.db.dao.OrderDao;
import com.shangan.trade.order.db.model.Order;
import com.shangan.trade.order.service.LimitBuyService;
import com.shangan.trade.order.service.OrderService;
import com.shangan.trade.order.service.RiskBlackListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private LimitBuyService limitBuyService;

    @Autowired
    private RiskBlackListService riskBlackListService;

    @Test
    public void insertGoodsTest() {
        System.out.println("Hello");
        Order order = new Order();
        //order.setId(678L);

        order.setActivityId(0L);
        order.setActivityType(0);

        order.setUserId(123456L);
        order.setGoodsId(452L);

        order.setStatus(1);

        order.setPayTime(new Date());
        order.setPayPrice(3599);
        boolean insertresult = orderDao.insertOrder(order);
        System.out.println(insertresult);
    }

    @Test
    public void insertGoodsTest2() {
        orderService.createOrder(123L,36L);
    }

    @Test
    public void addRedisLimitTest() {
        limitBuyService.addLimitMember(3L, 123);
    }

    @Test
    public void inRedisLimitTest() {
        limitBuyService.removeLimitMember(3L, 123);
        limitBuyService.isInLimitMember(3L, 123);
    }

    @Test
    public void addRiskBlackListTest() {
       // riskBlackListService.addRiskBlackListMember(123456);
        riskBlackListService.removeRiskBlackListMember(123456);
    }

}

