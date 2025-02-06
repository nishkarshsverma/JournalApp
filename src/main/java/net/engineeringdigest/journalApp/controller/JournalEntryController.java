package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    public JournalEntryService journalEntryService;
    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<JournalEntry> journalEntries=journalEntryService.getAll();
        if(journalEntries!=null && !journalEntries.isEmpty())
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> add(@RequestBody JournalEntry journalEntry )
    {
        try{
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getElementById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntry> journalEntry= journalEntryService.getById(myId);
        if(journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id,@RequestBody JournalEntry myEntry)
    {
        JournalEntry oldEntry=journalEntryService.getById(id).orElse(null);
        if(oldEntry!=null)
        {
            oldEntry.setTitle(myEntry.getTitle()!= oldEntry.getTitle() && !myEntry.getTitle().equals("")? myEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent()!= oldEntry.getContent()&& !myEntry.getContent().equals("")? myEntry.getContent() : oldEntry.getContent());
            journalEntryService.update(id,oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId)
    {
        journalEntryService.delete(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
