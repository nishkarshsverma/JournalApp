//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController2 {
//    public HashMap<Long, JournalEntry> journalEntryHashMap= new HashMap<>();
//    @GetMapping
//    public List<JournalEntry> getAll()
//    {
//        return new ArrayList<>(journalEntryHashMap.values());
//    }
//
//    @PostMapping
//    public void add(@RequestBody JournalEntry journalEntry )
//    {
//        journalEntryHashMap.put(journalEntry.getId(),journalEntry);
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getElementById(@PathVariable Long myId)
//    {
//        return journalEntryHashMap.get(myId);
//    }
//    @PutMapping("/id/{id}")
//    public boolean updateById(@PathVariable long id,@RequestBody JournalEntry myEntry)
//    {
//        journalEntryHashMap.put(id,myEntry);
//        return true;
//    }
//
//    @DeleteMapping("/id/{myId")
//    public boolean deleteById(@PathVariable Long myId)
//    {
//        journalEntryHashMap.remove(myId);
//        return true;
//    }
//}
