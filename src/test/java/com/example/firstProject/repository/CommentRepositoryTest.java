package com.example.firstProject.repository;

import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시물의 모든 댓글 조회")
    void findByArticleId() {
        /* case 1: 4번 게시물의 모든 댓글 조회 */
        {
            // 1. 입력데이터 준비
            Long articleId = 2L;
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L,"당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "choi", "쇼 생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* case 2: 1번 게시글의 모든 댓글 조회 */
//        {
//            // 1. 입력데이터 준비
//            Long articleId = 1L;
//            // 2. 실제 데이터 준비
//            List<Comment> comments = commentRepository.findByArticleId(articleId);
//            // 3. 예상 데이터
//            Article article = new Article(1L,"당신의 인생 영화는?", "댓글 ㄱ");
//            Comment a = new Comment(1L, article, "park", "굿 윌 헌팅");
//            Comment b = new Comment(2L, article, "kim", "아이 엠 샘");
//            Comment c = new Comment(3L, article, "choi", "쇼 생크 탈출");
//            List<Comment> expected = Arrays.asList(a, b, c);
//            // 4. 비교 및 검증
//            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
//        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* case 1: 특정 닉네임의 모든 댓글 조회*/
        {
            // 1. 입력데이터 준비
            String nickname = "park";
            // 2. 실제 데이터 준비
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(1L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(2L, "당신의 소울 푸드는?", "댓글 ㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(3L, "당신의 취미는?", "댓글 ㄱ"), nickname, "조깅");
            List<Comment> expect = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expect.toString(), comments.toString(), "park의 모든 댓글을 출력");
        }
    }
}