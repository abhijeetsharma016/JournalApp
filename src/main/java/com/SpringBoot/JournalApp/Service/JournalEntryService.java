package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.entry.JournalEntry;
import com.SpringBoot.JournalApp.entry.User;
import com.SpringBoot.JournalApp.repositor.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//controller -->service ---> repository

@Component
public class JournalEntryService {

    @Autowired //when we run spring generate an interface specially for us
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteByid(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}



