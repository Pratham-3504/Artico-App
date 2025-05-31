package com.Journal.project.repository;

import com.Journal.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUserForSA() {
        Query query = new Query();
        //normal Query with where clause....
        query.addCriteria(Criteria.where("userName").is("Pratham_soni"));

        //query for checking the email and sentiment Analysis field...
        // this both line combine create a AND operation..
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$\n"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is("true"));

        // query for OR operation..
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator((Criteria.where("email").exists(true)),
//                (Criteria.where("sentimentAnalysis").is("true"))));

        //another way to implement AND operation..
//        query.addCriteria(criteria.andOperator((Criteria.where("email").exists(true)),
//                (Criteria.where("sentimentAnalysis").is("true"))));

        //query for giving multiple values as arguement...
//        query.addCriteria(Criteria.where("userName").in("Pratham","Suhani"));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
