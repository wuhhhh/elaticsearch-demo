/*
 * projectName: zoina-search
 * fileName: ErrorCode.java
 * packageName: com.zoina.search.constants
 * date: 2020-04-17 9:52
 */
package com.zoina.search.constants;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ErrorCode
 * @packageName: com.zoina.search.constants
 * @description:
 * @data: 2020-04-17 9:52
 **/
public enum  ErrorCode {

    /**
     * 系统错误
     */
    ERROR_EXCEPTION(9999),
    /**
     * JSON格式错误
     */
    JSON_EXCEPTION(40100),
    /**
     * 缺少参数
     */
    LackParameter_EXCEPTION(40000),

    /**
     * 服务器异常
     */
    SERVER_ERROR(10500),

    /**
     * 参数错误
     */
    ARGUMENT_ERROR(10307),
    /**
     * 处理失败
     */
    FAILED(12138),

    /**
     * 成功处理 但有错误
     */
    DEAL_WITH_WARNING(10301),

    /**
     * 已提交 请勿重复提交或修改
     */
    DEAL_NON_SUBMISSION(10302);

    private final Integer value;

    ErrorCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}