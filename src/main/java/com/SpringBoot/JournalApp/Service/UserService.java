package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.entry.JournalEntry;
import com.SpringBoot.JournalApp.repositor.JournalEntryRepository;
import com.SpringBoot.JournalApp.repositor.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//controller -->service ---> repository

@Component
public class UserService {

    @Autowired //when we run spring generate an interface specially for us
    private UserRepository userRepository;

    public void saveEntry(JournalEntry journalEntry){
        userRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return userRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByid(ObjectId id){
        userRepository.deleteById(id);
    }

}



