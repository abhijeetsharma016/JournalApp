package com.SpringBoot.JournalApp.cache;

import com.SpringBoot.JournalApp.entry.ConfigJournalAppEntity;
import com.SpringBoot.JournalApp.repositor.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    // Initialize the static map
    public static Map<String, String> APP_Cache = new HashMap<>();

    @PostConstruct
    public void init(){
        try {
            List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
            for(ConfigJournalAppEntity configJournalAppEntity: all){
                APP_Cache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
            }
            System.out.println("AppCache initialized with " + APP_Cache.size() + " entries");
            System.out.println("Weather API URL: " + APP_Cache.get("weather_api"));
        } catch (Exception e) {
            System.err.println("Error initializing AppCache: " + e.getMessage());
            e.printStackTrace();
        }
    }
}