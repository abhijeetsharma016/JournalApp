package com.SpringBoot.JournalApp.Controller;


import com.SpringBoot.JournalApp.entry.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, com.SpringBoot.JournalApp.entry.JournalEntry> journalEnties = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEnties.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEnties.put(myEntry.get_id(), myEntry);
        return true;
    }


    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEnties.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEnties.remove(myId);
    }


    @PutMapping
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return journalEnties.put(id, myEntry);
    }

}
