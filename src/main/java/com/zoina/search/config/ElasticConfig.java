/*
 * projectName: zoina-search
 * fileName: ElasticConfig.java
 * packageName: com.zoina.search.config
 * date: 2020-04-13 14:16
 */
package com.zoina.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ElasticConfig
 * @packageName: com.zoina.search.config
 * @description: 配置文件
 * @data: 2020-04-13 14:16
 **/
public class ElasticConfig {
    @Value("${es.host}")
    public String host;
    @Value("${es.port}")
    public int port;
    @Value("${es.scheme}")
    public String scheme;

    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(makeHttpHost());
    }

    @Bean
    public RestClient elasticsearchRestClient(){
        return RestClient.builder(new HttpHost(host, port, scheme)).build();
    }

    private HttpHost makeHttpHost() {
        return new HttpHost(host, port, scheme);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder){
        return new RestHighLevelClient(restClientBuilder);
    }
}