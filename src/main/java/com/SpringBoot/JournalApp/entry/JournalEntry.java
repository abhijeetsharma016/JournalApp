package com.SpringBoot.JournalApp.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private String id;
    private String title;
    private String content;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


  public String get_id(){
      return id;
  }

  public void setId(String id){
      this.id = id;
  }

  public String getTitle(){
      return title;
  }
}
