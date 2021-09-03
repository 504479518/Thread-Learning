package com.xiangxue.web;

import com.xiangxue.normal.ServiceA;
import com.xiangxue.servlet3.ServiceB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类说明：
 */
@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class SecondController {

    private static Logger logger = LoggerFactory.getLogger(SecondController.class);

    @Autowired
    private ServiceB serviceB;
    @Autowired
    private ServiceA serviceA;

    @RequestMapping("/second")
    @ResponseBody
    public String hello() {
        synchronized (serviceB) {
            logger.info("Get serviceB");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (serviceA) {
                logger.info("Get serviceC");
                return "第二个服务";
            }
        }
    }

}
