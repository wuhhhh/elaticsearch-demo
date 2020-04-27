/*
 * projectName: zoina-search
 * fileName: ExcelServiceImpl.java
 * packageName: com.zoina.search.service.impl
 * date: 2020-04-17 13:49
 */
package com.zoina.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoina.search.dao.ExcelMapper;
import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.service.ExcelService;
import com.zoina.search.utils.ExcelUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ExcelServiceImpl
 * @packageName: com.zoina.search.service.impl
 * @description:
 * @data: 2020-04-17 13:49
 **/
@Service
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper,ExcelEntity> implements ExcelService {

    @Resource
    private ExcelMapper excelMapper;

    @Override
    public Boolean importExcelRecord(InputStream inputStream) {
        int res = 0;
        try {
                List<List<ExcelEntity>> list = ExcelUtils.excelToShopIdList(inputStream);
                if(0 == list.size()){
                    return null;
                }
                //和单条插入的执行对比了一下，在1000条数据级别内，差别不大，批量操作的优势可能大数据环境下才能显现
                for (List<ExcelEntity> elist : list) {
                    for(ExcelEntity entity:elist){
                        System.out.println(entity);
                        res = excelMapper.insert(entity);
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }

        return res != 0;
    }

    @Override
    public List<ExcelEntity> queryList() {
        QueryWrapper<ExcelEntity> wrapper = new QueryWrapper<>();
        List<ExcelEntity> list = excelMapper.selectList(wrapper);
        return list;
    }
}