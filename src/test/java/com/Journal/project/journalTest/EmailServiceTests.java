package com.Journal.project.journalTest;

import com.Journal.project.Scheduler.UserScheduler;
import com.Journal.project.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void regulatoryEmailServiceTest(){
        userScheduler.fetchUserAndSendMail();
    }

    @Disabled
    @Test
    public void emailSendTest(){
        emailService.sendEmail("prathamsoni3504@gmail.com", "first Email from SpringBoot",
                "hello pratham Soni.. hope you are doing great in your life");

    }
}
