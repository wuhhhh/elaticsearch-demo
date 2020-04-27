/*
 * projectName: zoina-search
 * fileName: ResultVOUtil.java
 * packageName: com.zoina.search.utils
 * date: 2020-04-17 9:48
 */
package com.zoina.search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zoina.search.constants.ErrorCode;
import com.zoina.search.entity.PageBean;
import com.zoina.search.exception.BaseException;
import com.zoina.search.vo.ResultVO;

import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ResultVOUtil
 * @packageName: com.zoina.search.utils
 * @description: 返回类型工具类
 * @data: 2020-04-17 9:48
 **/
public class ResultVOUtil {
    /**
     * 返回对象
     *
     * @param object
     * @return
     */
    public static ResultVO<?> success(Object object) {
        if (CustomerUtil.objIsEmpty(object)) {
            return noData();
        }
        return new ResultVO<>(0, "成功", object);
    }

    public static <T> ResultVO<T> ofSuccess(T t){
        if(t == null || "".equals(t)){
            return new ResultVO<>(404, "未找到数据", null);
        }
        return new ResultVO<>(0,  "成功", t);
    }

    public static ResultVO success(boolean ok, String errmsg){
        return ok ? new ResultVO<>(0,  "操作成功", null)
                : new ResultVO<>(10306, errmsg, null);
    }

    public static ResultVO success(boolean ok, String errmsg, Object data){
        return ok ? new ResultVO<>(0,  "操作成功", null)
                : new ResultVO<>(10306, errmsg, data);
    }
    /**
     * 无数据
     *
     * @return
     */
    public static ResultVO<?> noData() {
        return new ResultVO<>(404, "未找到数据", null);
    }

    public static ResultVO<?> success(PageBean pageBean) {
        String s = JSONObject.toJSONString(pageBean, SerializerFeature.WriteNullStringAsEmpty);
        Map<String, Object> map = JSON.parseObject(s, Map.class);
        map.remove("records");
        map.remove("searchCount");

        return new ResultVO<>(0, "成功", map);
    }

    public static ResultVO error(int i, String msg, Exception e) {
        return new ResultVO<>(i, msg, e);
    }

    public static ResultVO<?> success() {
        return new ResultVO<>(0, "成功", null);
    }

    public static ResultVO<Void> error(Integer code, String msg) {
        return new ResultVO<>(code, msg);
    }

    public static ResultVO<String> error(BaseException e) {
        return new ResultVO<>(e.getCode(), e.getMessage());
    }

    public static ResultVO error() {
        return new ResultVO<>(400, "服务器错误！");
    }

    public static ResultVO error(String msg) {
        return new ResultVO<>(400, msg);
    }

    public static ResultVO<?> error(ErrorCode code, String msg) {
        return new ResultVO<>(code.getValue(), msg);
    }


    /**
     * 参数错误
     *
     * @return
     */
    public static ResultVO<?> paramError() {
        return new ResultVO<>(300, "参数错误！");
    }

    public static ResultVO<?> paramError(String msg) {
        return new ResultVO<>(300, msg + "，参数错误");
    }

    /**
     * 自定义返回消息
     *
     * @param msg
     * @return
     */
    public static ResultVO<?> custom(String msg) {
        return new ResultVO<>(201, msg);
    }

    public static ResultVO noDataAuth() {
        return new ResultVO(403,"缺少对该数据的操作权限");
    }
}