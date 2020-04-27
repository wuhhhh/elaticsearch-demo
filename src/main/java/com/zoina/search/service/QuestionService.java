/*
 * projectName: zoina-search
 * fileName: QuestionService.java
 * packageName: com.zoina.search.service
 * date: 2020-04-21 14:45
 */
package com.zoina.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoina.search.dao.QuestionMapper;
import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.vo.QuestionVo;
import com.zoina.search.vo.ResultVO;

import javax.annotation.Resource;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: QuestionService
 * @packageName: com.zoina.search.service
 * @description: 问题服务层
 * @data: 2020-04-21 14:45
 **/
public interface QuestionService extends IService<ExcelEntity> {

    /**
     *@title:
     *@description: 返回分页结果
     *@author: 吴洪阳
     *@date: 2020/4/21 14:51
     *@param:  * @param null
     *@return:
     *@throws:
     */
    ResultVO<?> queryList(Integer pageSize, Integer page, QuestionVo questionVo);
}