/*
 * projectName: zoina-search
 * fileName: QueryVo.java
 * packageName: com.zoina.search.vo
 * date: 2020-04-13 19:04
 */
package com.zoina.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: QueryVo
 * @packageName: com.zoina.search.vo
 * @description: 查询Vo对象
 * @data: 2020-04-13 19:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {
    /**
     * 索引名
     */
    private String idxName;
    /**
     * 需要反射的实体类型，用于对查询结果的封装
     */
    private String className;
    /**
     * 具体条件
     */
    private Map<String, Map<String,Object>> query;
}