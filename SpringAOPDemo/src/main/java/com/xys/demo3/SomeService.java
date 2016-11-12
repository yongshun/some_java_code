package com.xys.demo3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 2016/11/12 19:08
 */
@Service
public class SomeService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Random random = new Random(System.currentTimeMillis());

    public void someMethod() {
        logger.info("---SomeService: someMethod invoked---");
        try {
            // 模拟耗时任务
            Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
