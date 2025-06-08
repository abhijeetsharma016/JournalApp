package com.SpringBoot.JournalApp.entry;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "journal_entries")
@Data //getters setters and everything
public class JournalEntry {

    @Id
    private ObjectId id;


    private String title;
    private String content;
    private LocalDateTime date;



}
