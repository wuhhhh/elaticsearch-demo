/*
 * projectName: zoina-search
 * fileName: ExcelService.java
 * packageName: com.zoina.search.service
 * date: 2020-04-17 13:50
 */
package com.zoina.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoina.search.entity.ExcelEntity;

import java.io.InputStream;
import java.util.List;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ExcelService
 * @packageName: com.zoina.search.service
 * @description:
 * @data: 2020-04-17 13:50
 **/
public interface ExcelService extends IService<ExcelEntity> {

    /**
     *@title:
     *@description: 导入excel
     *@author: 吴洪阳
     *@date: 2020/4/17 14:03
     *@param:  * @param null
     *@return:
     *@throws:
     */
    Boolean importExcelRecord(InputStream inputStream);

    List<ExcelEntity>queryList();
}