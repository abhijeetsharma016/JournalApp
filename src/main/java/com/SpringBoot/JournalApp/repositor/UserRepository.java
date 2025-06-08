package com.SpringBoot.JournalApp.repositor;

import com.SpringBoot.JournalApp.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<JournalEntry, ObjectId> {

}