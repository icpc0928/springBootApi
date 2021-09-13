package com.leo.springbootapi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log")
    public String log(){

        String name = "Leo";
        String email = "icpc@yahoo.com";


        logger.info("------- log ---------");
//        logger.warn("warn --- log");
//        logger.error("error --- log");
//        logger.debug("debug --- log");
//        logger.trace("trace --- log");
//        logger.info("name: {}, email: {}", name, email);
        return "logtest";
    }
}
