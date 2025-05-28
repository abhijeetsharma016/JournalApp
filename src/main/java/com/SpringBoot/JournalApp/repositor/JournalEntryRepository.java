package com.SpringBoot.JournalApp.repositor;

import com.SpringBoot.JournalApp.entry.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}