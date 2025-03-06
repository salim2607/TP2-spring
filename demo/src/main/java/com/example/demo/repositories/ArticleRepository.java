package com.example.demo.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, String> {
}