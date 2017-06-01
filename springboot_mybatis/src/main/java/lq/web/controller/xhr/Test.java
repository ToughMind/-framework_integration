/**
 * @(#)Test.JAVA, 2017年05月31日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package lq.web.controller.xhr;

import com.alibaba.fastjson.JSON;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Test。
 *
 * @author 刘泉 2017年05月31日 20:27
 */
@RestController
@RequestMapping("/test")
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public List getSession(HttpSession httpSession) {
        Enumeration<String> sessionkeys = httpSession.getAttributeNames();

        List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
        while (sessionkeys.hasMoreElements()) {
            String key = (String) sessionkeys.nextElement();
            list.add(new Pair<String, String>(key,
                httpSession.getAttribute(key).toString()));
        }
        return list;
    }

    @RequestMapping(value = "/addSesstion", method = RequestMethod.GET)
    public void addEntity(HttpSession httpSession) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random random = new Random();
                httpSession.setAttribute("name", "liuquan" + random.nextInt());
            }
        }).run();
    }

    @Scheduled(cron = "0/10 * *  * * ? ")
    public void getTaskLog4j() {
        logger.info(" ===> [op: getTaskLog4j] 当前时间: " + new Date().toString());
    }

    @Async
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public String doTaskOne() throws Exception {
        logger.warn(" ===> [op: doTaskOne] 开始执行任务。");
        System.out.println("开始做任务");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
        return "返回";
    }

}
