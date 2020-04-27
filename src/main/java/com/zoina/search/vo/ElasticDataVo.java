/*
 * projectName: zoina-search
 * fileName: ElasticDataVo.java
 * packageName: com.zoina.search.vo
 * date: 2020-04-13 19:01
 */
package com.zoina.search.vo;

import com.zoina.search.entity.ElasticEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ElasticDataVo
 * @packageName: com.zoina.search.vo
 * @description: http交互Vo对象
 * @data: 2020-04-13 19:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticDataVo<T> {
    /**
     * 索引名
     */
    private String idxName;
    /**
     * 数据存储对象
     */
    private ElasticEntity elasticEntity;

}