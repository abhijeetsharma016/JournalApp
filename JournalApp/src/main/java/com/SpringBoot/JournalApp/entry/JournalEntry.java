package com.SpringBoot.JournalApp.entry;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JournalEntry {

  public long id;
  public String title;
  public String content;

  public long get_id(){
      return id;
  }

  public void setId(long id){
      this.id = id;
  }

  public String getTitle(){
      return title;
  }
}
