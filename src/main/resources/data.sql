INSERT INTO article(title, content) VALUES ('당신의 인생 영화는?', '댓글 ㄱ');
INSERT INTO article(title, content) VALUES ('당신의 소울 푸드는?', '댓글 ㄱ');
INSERT INTO article(title, content) VALUES ('당신의 취미는?', '댓글 ㄱ');

INSERT INTO comment(article_id, nickname, body) VALUES (1, 'park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES (1, 'kim', '아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES (1, 'choi', '쇼 생크 탈출');

INSERT INTO comment(article_id, nickname, body) VALUES (2, 'park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES (2, 'kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES (2, 'choi', '초밥');

INSERT INTO comment(article_id, nickname, body) VALUES (3, 'park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES (3, 'kim', '유뷰브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES (3, 'choi', '독서');
