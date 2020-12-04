/*
 * projectName: zoina-search
 * fileName: ExcelServiceImpl.java
 * packageName: com.zoina.search.service.impl
 * date: 2020-04-17 13:49
 */
package com.zoina.search.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoina.search.dao.ExcelMapper;
import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.entity.ImportExcelListener;
import com.zoina.search.service.ExcelService;
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
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, ExcelEntity> implements ExcelService {

    @Resource
    private ExcelMapper excelMapper;

    @Override
    public Boolean importExcelRecord(InputStream inputStream) {
        int res = 0;

        ImportExcelListener<ExcelEntity> listener = new ImportExcelListener<>();
//        EasyExcel.read(inputStream, ExcelEntity.class, listener).doReadAll();
//        Map<String, List<ExcelEntity>> map = listener.getMap();
        //Map<String, List<Map<Object, Object>>> listMap = listener.getMap();
        ExcelReader excelReader = null;

        try {
            excelReader = EasyExcel.read(inputStream).build();
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(ExcelEntity.class).registerReadListener(listener).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            ExcelReader read = excelReader.read(readSheet1);
            Map<String, List<ExcelEntity>> map = listener.getMap();
            for (List<ExcelEntity> excelEntities : map.values()) {

                for (ExcelEntity excelEntity : excelEntities) {
                    System.out.println("\n" + excelEntity + "\n");
                    res = excelMapper.insert(excelEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
            return res != 0;
        }


    }

    @Override
    public List<ExcelEntity> queryList() {
        QueryWrapper<ExcelEntity> wrapper = new QueryWrapper<>();
        List<ExcelEntity> list = excelMapper.selectList(wrapper);
        return list;
    }
}


