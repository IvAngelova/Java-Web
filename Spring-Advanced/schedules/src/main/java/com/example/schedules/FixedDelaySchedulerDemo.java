package com.example.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedDelaySchedulerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedDelaySchedulerDemo.class);

    // This fixed delay waits N millis after the execution of the previous task ends
    @Scheduled(fixedDelay = 5000, initialDelay = 20000)
    public void showTimeWithFixedDelay(){
        LOGGER.info("Hello, from fixed delay scheduler at {}", LocalDateTime.now());
    }
}
