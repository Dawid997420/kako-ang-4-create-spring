package com.example.kakoang4create.repository;

import com.example.kakoang4create.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends MongoRepository<Quote,String> {



}
