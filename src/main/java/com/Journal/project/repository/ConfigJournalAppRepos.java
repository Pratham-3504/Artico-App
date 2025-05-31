package com.Journal.project.repository;


import com.Journal.project.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepos extends MongoRepository<ConfigJournalApp, ObjectId> {

}
