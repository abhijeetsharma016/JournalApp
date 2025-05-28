package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.entry.JournalEntry;
import com.SpringBoot.JournalApp.repositor.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//controller -->service ---> repository

@Component
public class JournalEntryService {

    @Autowired //when we run spring generate an interface specially for us
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

}



