package com.shangan.trade.lightning.deal;

import com.shangan.trade.lightning.deal.utils.RedisWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    public RedisWorker redisWorker;

    @Test
    public void setValue(){
        redisWorker.setValue("name","tom");
    }

    @Test
    public void getValue(){
        String res = redisWorker.getValueByKey("name");
        System.out.println(res);
    }


    @Test
    public void setStockTest() {
        //stock:秒杀活动ID    库存数
        redisWorker.setValue("stock:668899", 29L);
    }

    @Test
    public void stockCheckTest() {
        //redisWorker.stockDeductCheck("stock:3");
        System.out.println(redisWorker.getValueByKey("stock:3"));
    }

    @Test
    public void setActivityStockTest() {
        //stock:秒杀活动ID    库存数
        redisWorker.setValue("stock:3", 10L);
    }

}
