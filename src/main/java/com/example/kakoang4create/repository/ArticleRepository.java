package com.example.kakoang4create.repository;

import com.example.kakoang4create.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {



}
