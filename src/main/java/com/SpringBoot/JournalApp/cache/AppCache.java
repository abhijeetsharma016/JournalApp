package com.SpringBoot.JournalApp.cache;


import com.SpringBoot.JournalApp.entry.ConfigJournalAppEntity;
import com.SpringBoot.JournalApp.repositor.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    private Map<String, String> appcache;

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        appcache = null;
    }
}
