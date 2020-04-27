/*
 * projectName: zoina-search
 * fileName: BaseException.java
 * packageName: com.zoina.search.exception
 * date: 2020-04-17 9:53
 */
package com.zoina.search.exception;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: BaseException
 * @packageName: com.zoina.search.exception
 * @description:
 * @data: 2020-04-17 9:53
 **/
public class BaseException extends RuntimeException {
    private Integer code;
    private String status;

    public BaseException(String message) {
        super(message);
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }


    public BaseException(String status, String message) {
        super(message);
        this.status = status;
    }
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

}