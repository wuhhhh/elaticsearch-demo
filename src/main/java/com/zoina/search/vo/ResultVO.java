/*
 * projectName: zoina-search
 * fileName: ResultVO.java
 * packageName: com.zoina.search.vo
 * date: 2020-04-17 9:25
 */
package com.zoina.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ResultVO
 * @packageName: com.zoina.search.vo
 * @description: 结果输出对象
 * @data: 2020-04-17 9:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 错误码.
     */
    private Integer errcode;

    /**
     * 提示信息.
     */
    private String errmsg;

    /**
     * 具体内容.
     */
    private T data;

    private Long timestamp = System.currentTimeMillis();


    public ResultVO(Integer errcode, String errmsg, T data) {
        super();
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }


    public ResultVO(Integer errcode, String errmsg) {
        super();
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

}