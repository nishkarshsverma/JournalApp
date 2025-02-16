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
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveEntry(User user) { userRepo.save(user); }

    public List<User> getAll() { return userRepo.findAll();}

    public Optional<User> getById(ObjectId id) { return userRepo.findById(id); }

    public void delete(ObjectId myId) { userRepo.deleteById(myId); }

    public User findByUsername(String username){
        return userRepo.findByUserName(username);
    }

}
