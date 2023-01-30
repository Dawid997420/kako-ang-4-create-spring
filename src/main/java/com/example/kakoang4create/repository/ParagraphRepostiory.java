package com.example.kakoang4create.repository;

import com.example.kakoang4create.model.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepostiory extends MongoRepository<Paragraph, String> {




}
