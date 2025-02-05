package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAll();
    }

    @PostMapping
    public void add(@RequestBody JournalEntry journalEntry )
    {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getElementById(@PathVariable ObjectId myId)
    {
        return journalEntryService.getById(myId).orElse(null);
    }
    @PutMapping("/id/{id}")
    public boolean updateById(@PathVariable ObjectId id,@RequestBody JournalEntry myEntry)
    {
        JournalEntry oldEntry=journalEntryService.getById(id).orElse(null);
        if(oldEntry!=null)
        {
            oldEntry.setTitle(myEntry.getTitle()!= oldEntry.getTitle() && !myEntry.getTitle().equals("")? myEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent()!= oldEntry.getContent()&& !myEntry.getContent().equals("")? myEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.update(id,oldEntry);
        return true;
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId)
    {
        journalEntryService.delete(myId);
        return true;
    }
}
