/*
 * projectName: zoina-search
 * fileName: ElasticApplication.java
 * packageName: com.zoina.search
 * date: 2020-04-13 19:05
 */
package com.zoina.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ElasticApplication
 * @packageName: com.zoina.search
 * @description:
 * @data: 2020-04-13 19:05
 **/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.zoina.search.dao.*")
public class ElasticApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class,args);
    }

}
