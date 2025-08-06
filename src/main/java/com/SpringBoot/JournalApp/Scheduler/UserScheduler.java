
package com.SpringBoot.JournalApp.Scheduler;


import com.SpringBoot.JournalApp.Service.EmailService;
import com.SpringBoot.JournalApp.cache.AppCache;
import com.SpringBoot.JournalApp.entry.JournalEntry;
import com.SpringBoot.JournalApp.entry.User;
import com.SpringBoot.JournalApp.enums.Sentiment;
import com.SpringBoot.JournalApp.repositor.UserRepository;
import com.SpringBoot.JournalApp.repositor.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;


    // @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();

        for (User user : users) {
            // Filter journal entries from the last 7 days
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment())
                    .collect(Collectors.toList());


            // Count frequency of each sentiment
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            // Find the most frequent sentiment
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            // Send email if sentiment found
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(),
                        "Sentiment for last 7 days",
                        mostFrequentSentiment.toString());
            }
        }
    }
}