package com.SpringBoot.JournalApp.repositor;

import com.SpringBoot.JournalApp.entry.ConfigJournalAppEntity;
import com.SpringBoot.JournalApp.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}