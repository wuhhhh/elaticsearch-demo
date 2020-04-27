/*
 * projectName: zoina-search
 * fileName: ElasticEntity.java
 * packageName: com.zoina.search.entity
 * date: 2020-04-13 14:17
 */
package com.zoina.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ElasticEntity
 * @packageName: com.zoina.search.entity
 * @description: 数据存储对象
 * @data: 2020-04-13 14:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity {
    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;
}