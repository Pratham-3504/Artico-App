package com.Journal.project.Scheduler;

import com.Journal.project.cache.AppCache;
import com.Journal.project.entity.JournalEntry;
import com.Journal.project.entity.User;
import com.Journal.project.repository.UserRepositoryImpl;
import com.Journal.project.service.EmailService;
import com.Journal.project.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


    public void fetchUserAndSendMail(){
        List<User> users = userRepository.getAllUserForSA();
        for(User user : users){
            String entry = "hello Pratham Soni dev here";
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "sentiment for 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void clearAppCache(){
        appCache.init();
    }
}
