package com.example.firstProject.service;

import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import com.example.firstProject.repository.ArticleRepository;
import com.example.firstProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 2. 엔티티 -> DTO변환
//        List<CommentDto> dtos = new ArrayList<>();
//        for(int i=0; i<comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + "해당 게시물이 존재하지않습니다"));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(article, dto);
        // 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변환해 저장
        return CommentDto.createCommentDto(created);
    }
}
