package com.SpringBoot.JournalApp.Controller;

import com.SpringBoot.JournalApp.Service.JournalEntryService;
import com.SpringBoot.JournalApp.entry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> entries = journalEntryService.getAll();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<Void> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> existing = journalEntryService.findById(myId);
        if (existing.isPresent()) {
            journalEntryService.deleteByid(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful delete
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        Optional<JournalEntry> existing = journalEntryService.findById(id);
        if (existing.isPresent()) {
            JournalEntry old = existing.get();

            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                    ? newEntry.getTitle() : old.getTitle());

            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                    ? newEntry.getContent() : old.getContent());

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
