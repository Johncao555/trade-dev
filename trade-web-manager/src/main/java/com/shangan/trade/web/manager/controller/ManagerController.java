package com.shangan.trade.web.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {



    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 跳转商品添加页面
     * @return
     */
    @RequestMapping("/add_goods")
    public String addGoods() {
        return "add_goods";
    }

}
