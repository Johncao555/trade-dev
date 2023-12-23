package com.shangan.trade.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.shangan"})
@SpringBootApplication
public class TradeGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeGoodsApplication.class, args);
    }

}
