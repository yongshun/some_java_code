package com.xys.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 2016/11/12 19:08
 */
@Service
public class NormalService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void someMethod() {
        logger.info("---NormalService: someMethod invoked---");
    }
}
