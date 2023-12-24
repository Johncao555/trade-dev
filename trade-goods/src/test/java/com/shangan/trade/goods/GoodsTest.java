package com.shangan.trade.goods;

import com.shangan.trade.goods.db.dao.GoodsDao;
import com.shangan.trade.goods.db.model.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {
    @Autowired
    private GoodsDao goodsDao;
    @Test
    public void insertGoodsTest() {
        System.out.println("Hello");
        Goods goods = new Goods();
        goods.setTitle("iphone 15 pro max");
        goods.setBrand("苹果 Apple");
        goods.setCategory("手机");
        goods.setNumber("NO123");
        goods.setImage("test");
        goods.setDescription("iphone 11 pro max is very good");
        goods.setKeywords("苹果 手机 apple");
        goods.setSaleNum(0);
        goods.setAvailableStock(10);
        goods.setPrice(400);
        goods.setStatus(1);
        goods.setCreateTime(new Date());
        boolean insertresult = goodsDao.insertGoods(goods);
        System.out.println(insertresult);
    }

    @Test
    public void deleteGoodsTest() {
        boolean deleteresult = goodsDao.deleteGoods(2);
        System.out.println(deleteresult);
    }


}
