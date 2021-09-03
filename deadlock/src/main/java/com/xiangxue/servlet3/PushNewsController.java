package com.xiangxue.servlet3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：
 */
@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
// TODO 记得要在WebInitializer中增加servlet.setAsyncSupported(true);
public class PushNewsController {

    //private DeferredResult<String> deferredResult;

    @RequestMapping("/pushnews")
    public String news() {
        return "pushNews";
        
    }

    //线程池处理用户实际业务
    ExecutorService executorService = Executors.newFixedThreadPool(2);


    @RequestMapping(value = "/realTimeNews")
    @ResponseBody
    public DeferredResult<String> realtimeNews() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int index = new Random().nextInt(Const.NEWS.length);
                deferredResult.setResult(Const.NEWS[index]);
            }
        });

        return deferredResult;
    }

}
