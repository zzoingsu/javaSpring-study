package com.example.firstProject.controller;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info(form.toString());
        // System.out.println(form.toString());
        // 1. DTO를 엔티티로 전환
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(article.toString());
        // 2. repository로 엔티티를 DB에 저장
        Article saved = articleRepossitory.save(article);
        System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id=" + id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepossitory.findById(id).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //3. 뷰 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepossitory.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepossitory.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }
    @PostMapping ("articles/update")
    public String update(ArticleForm form) {
    Article articleEntity = form.toEntity();
    Article target = articleRepossitory.findById(articleEntity.getId()).orElse(null);
    if(target != null) {
        articleRepossitory.save(articleEntity);
    }return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        //1.삭제할 대상 가져오기
        Article target = articleRepossitory.findById(id).orElse(null);
        //2. 대상 엔티티 삭제하기
        if(target!=null) {
            articleRepossitory.delete(target);
            rttr.addAttribute("msg", "삭제됐습니다");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}