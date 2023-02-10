package com.example.kakoang4create.repository;

import com.example.kakoang4create.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
}
