package com.example.kakoang4create.repository;

import com.example.kakoang4create.model.UserE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserERepository extends MongoRepository<UserE,String > {


    List<UserE> findByEmail(String email);

}
