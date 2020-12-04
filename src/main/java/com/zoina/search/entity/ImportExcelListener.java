/*
 * projectName: zoina-search
 * fileName: ImportExcelListener.java
 * packageName: com.zoina.search.entity
 * date: 2020-12-03 17:00
 */
package com.zoina.search.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ImportExcelListener
 * @packageName: com.zoina.search.entity
 * @description: 数据接受类
 * @data: 2020-12-03 17:00
 **/
public class ImportExcelListener<T> extends AnalysisEventListener<T> {
        private Map<String , List<T>> map = new HashMap<>();
        private List<T> temp = new ArrayList<>();


        @Override
        public void invoke(T data, AnalysisContext context) {
            temp.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            map.put(context.readSheetHolder().getSheetName(), new ArrayList<>(temp));
            temp.clear();
        }

        public List<T> getList(String sheetName) {
            return map.get(sheetName);
        }
        public Map<String,List<T>>getMap(){
            return map;
        }
}