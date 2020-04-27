/*
 * projectName: zoina-search
 * fileName: QuestionMapper.java
 * packageName: com.zoina.search.dao
 * date: 2020-04-21 14:46
 */
package com.zoina.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.vo.QuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: QuestionMapper
 * @packageName: com.zoina.search.dao
 * @description: mapper
 * @data: 2020-04-21 14:46
 **/
@Mapper
public interface QuestionMapper extends BaseMapper<ExcelEntity> {

    /**
     *@title:
     *@description: 查询分页
     *@author: 吴洪阳
     *@date: 2020/4/21 15:15
     *@param:  * @param null
     *@return:
     *@throws:
     */
    List<QuestionVo> queryList(@Param("page")Integer page,
                                @Param("pageSize")Integer pageSize,
                                @Param("questionVo")QuestionVo questionVo);
    /**
     *@title:
     *@description: 总条数
     *@author: 吴洪阳
     *@date: 2020/4/21 15:18
     *@param:  * @param null
     *@return:
     *@throws:
     */
    Integer queryAllCount();
}