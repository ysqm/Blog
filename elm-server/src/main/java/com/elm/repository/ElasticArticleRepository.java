package com.elm.repository;

import com.elm.vo.ElasticArticleVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticArticleRepository extends ElasticsearchRepository<ElasticArticleVO, String> {
    Object search(String keyword);
}
