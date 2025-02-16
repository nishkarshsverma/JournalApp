package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user=userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch (Exception e) {
            log.info("Exception ",e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }
    public boolean update(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
        return true;
    }

    public void delete(ObjectId myId, String username) {
        journalEntryRepo.deleteById(myId);
        User user=userService.findByUsername(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(myId));
        userService.saveEntry(user);
    }
}
