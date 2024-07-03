package com.elm.service.impl;

import com.elm.entity.Article;
import com.elm.service.ArticleSearchService;
import com.elm.vo.ElasticArticleVO;
import com.elm.entity.User;
import com.elm.repository.ArticleRepository;
import com.elm.repository.ElasticArticleRepository;
import com.elm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElasticArticleRepository elasticArticleRepository;

    public void importDataToElasticsearch() {
        List<Article> articles = articleRepository.findAll();
        List<ElasticArticleVO> elasticArticleVOS = articles.stream().map(article -> {
            ElasticArticleVO elasticArticleVO = new ElasticArticleVO();
            elasticArticleVO.setId(article.getArticleId().toString());
            elasticArticleVO.setTitle(article.getTitle());

            // 读取内容
            String content = "";
            try {
                content = new String(Files.readAllBytes(Paths.get(article.getContentPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            elasticArticleVO.setContent(content);

            User user = userRepository.findByUserId(Math.toIntExact(article.getUserId()));
            if (user != null) {
                elasticArticleVO.setUsername(user.getUsername());
            }
            return elasticArticleVO;
        }).collect(Collectors.toList());

        elasticArticleRepository.saveAll(elasticArticleVOS);
    }

    public List<Object> searchArticles(String keyword) {
        List<ElasticArticleVO> articles = (List<ElasticArticleVO>) elasticArticleRepository.search(keyword);
        return articles.stream().map(ElasticArticleVO::getId).collect(Collectors.toList());
    }
}
