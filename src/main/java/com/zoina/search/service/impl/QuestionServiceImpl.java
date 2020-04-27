/*
 * projectName: zoina-search
 * fileName: QuestionServiceImpl.java
 * packageName: com.zoina.search.service.impl
 * date: 2020-04-21 14:45
 */
package com.zoina.search.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoina.search.dao.QuestionMapper;
import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.service.QuestionService;
import com.zoina.search.utils.ResultVOUtil;
import com.zoina.search.vo.QuestionVo;
import com.zoina.search.vo.ResultVO;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: QuestionServiceImpl
 * @packageName: com.zoina.search.service.impl
 * @description: 问题实现层
 * @data: 2020-04-21 14:45
 **/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, ExcelEntity> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;


    @Override
    public ResultVO<?> queryList(Integer pageSize, Integer page, QuestionVo questionVo) {
        IPage<QuestionVo> ipg = new Page<>(page,pageSize);
        List<QuestionVo> qlist = questionMapper.queryList(page,pageSize,questionVo);
        Integer count = questionMapper.queryAllCount();
        if(null == qlist){
            return ResultVOUtil.noData();
        }else{
            ipg.setTotal(count)
                    .setRecords(qlist)
                    .setCurrent(page)
                    .setSize(pageSize)
                    .setPages(count % pageSize == 0 ? count % pageSize : (count % pageSize) + 1);
        }
        return ResultVOUtil.success(ipg);

    }
}