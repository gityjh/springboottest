package com.example.springboot.demo;

import com.example.springboot.demo.test.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    Student student;
    @Autowired
    ApplicationContext applicationContext;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    void contextLoads() {
//        System.out.println(student);
        try {
            logger.trace("trace...");
            logger.debug("debug...");
            logger.info("info...");
            logger.warn("warn...");
            logger.error("error...");
        } catch (Exception e) {
            logger.error("error",e);
        }
    }
    @Test
    public void helloServiceTest(){
       boolean flag = applicationContext.containsBean("helloService");
       System.out.println(flag);
    }
}
