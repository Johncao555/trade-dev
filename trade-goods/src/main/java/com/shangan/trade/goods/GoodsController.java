package com.shangan.trade.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {
    @RequestMapping("/goods/test")
    @ResponseBody
    public String test() {
        return "hello word from Emily!";
    }

    @RequestMapping("/goods/hi")
    @ResponseBody
    public String hi() {
        return "Say hi!";
    }

    @RequestMapping("/goods/hiname")
    @ResponseBody
    public String hiname(@RequestParam("name") String name) {
        return "Say hi! "+name;
    }

}
