
package com.SpringBoot.JournalApp.Scheduler;


        import com.SpringBoot.JournalApp.Service.EmailService;
        import com.SpringBoot.JournalApp.Service.SentimentAnalysisService;
        import com.SpringBoot.JournalApp.entry.JournalEntry;
        import com.SpringBoot.JournalApp.entry.User;
        import com.SpringBoot.JournalApp.repositor.UserRepository;
        import com.SpringBoot.JournalApp.repositor.UserRepositoryImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Component;

        import java.time.LocalDate;
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


    @Scheduled(cron = "* * * ? * *") // Every 30 seconds for testing
    public void fetchUsersAndSendSaMail() {
        System.out.println("Scheduler triggered!"); // Add this line
        List<User> users = userRepository.getUserForSA();
        System.out.println("Found users: " + users.size()); // Add this line

        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList()).reversed();


            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            //emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }
}
