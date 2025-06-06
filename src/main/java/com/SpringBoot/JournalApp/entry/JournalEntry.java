package com.SpringBoot.JournalApp.entry;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String title;
    private String content;
    private LocalDateTime date;


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


  public ObjectId get_id(){
      return id;
  }

  public void setId(ObjectId id){
      this.id = id;
  }

  public String getTitle(){
      return title;
  }
}
