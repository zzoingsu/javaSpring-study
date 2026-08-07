package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    public Article create(ArticleForm dto) {
        Article created = dto.toEntity();
        if(created.getId()!=null) {
            return null;
        }
        return articleRepository.save(created);
    }
    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id:{}, article:{}", id, article.toString());
        Article target = articleRepository.findById(id).orElse(null);
        if(target==null || id != article.getId()) {
            log.info("잘못된 요청 id:{}, article:{}", id, article.toString());
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target==null) {
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Article> createdList(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        articleRepository.findById(-1l)
                .orElseThrow(() -> new IllegalArgumentException("결재 실패"));
        return articleList;
    }

}
