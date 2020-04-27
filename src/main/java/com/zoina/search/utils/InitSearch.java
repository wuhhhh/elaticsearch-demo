/*
 * projectName: zoina-search
 * fileName: InitSearch.java
 * packageName: com.zoina.search.utils
 * date: 2020-04-09 14:12
 */
package com.zoina.search.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: InitSearch
 * @packageName: com.zoina.search.utils
 * @description: demo
 * @data: 2020-04-09 14:12
 **/
public class InitSearch {
    public static RestHighLevelClient getclient(){
        return new RestHighLevelClient(RestClient.builder
                (new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9200,"http")));
    }

}