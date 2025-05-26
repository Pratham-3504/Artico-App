package com.Journal.project.service;

import com.Journal.project.entity.JournalEntry;
import com.Journal.project.entity.User;
import com.Journal.project.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntryOfUser(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId my_id){
        return journalEntryRepository.findById(my_id);
    }

    public void deleteById(ObjectId my_id){
        journalEntryRepository.deleteById(my_id);
    }

    public boolean deleteJournalFromUserByID(ObjectId my_id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(my_id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(my_id);
            }
            return removed;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
