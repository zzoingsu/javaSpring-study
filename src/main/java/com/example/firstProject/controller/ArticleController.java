package com.example.firstProject.controller;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepossitory;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping ("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        // 1. DTO를 엔티티로 전환
        Article article = form.toEntity();
        System.out.println(article.toString());
        // 2. repository로 엔티티를 DB에 저장
        Article saved = articleRepossitory.save(article);
        System.out.println(saved.toString());
        return "";
    }

}
