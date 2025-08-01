package com.SpringBoot.JournalApp.repositor;

import com.SpringBoot.JournalApp.entry.ConfigJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, String> {
    // You can add custom query methods here if needed
}