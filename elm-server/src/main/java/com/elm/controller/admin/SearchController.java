package com.elm.controller.admin;

import com.elm.service.impl.ArticleSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ArticleSearchServiceImpl articleSearchServiceImpl;

    @GetMapping("/import")
    public String importData() {
        articleSearchServiceImpl.importDataToElasticsearch();
        return "Data imported successfully";
    }

    @GetMapping("/search")
    public List<Object> searchArticles(@RequestParam String keyword) {
        return articleSearchServiceImpl.searchArticles(keyword);
    }
}
