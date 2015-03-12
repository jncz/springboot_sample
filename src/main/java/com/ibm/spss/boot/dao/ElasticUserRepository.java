package com.ibm.spss.boot.dao;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ibm.spss.boot.domain.DocUser;

public interface ElasticUserRepository extends ElasticsearchRepository<DocUser, Long> {

}
