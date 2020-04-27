/*
 * projectName: zoina-search
 * fileName: ExcelUtils.java
 * packageName: com.zoina.search.utils
 * date: 2020-04-14 9:24
 */
package com.zoina.search.utils;

import com.zoina.search.entity.ExcelEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ExcelUtils
 * @packageName: com.zoina.search.utils
 * @description: excel工具类
 * @data: 2020-04-14 9:24
 **/
public class ExcelUtils {

    private static Object getCellValue(Cell cell) {
        Object cellValue;
        if (Objects.nonNull(cell) && cell.getCellTypeEnum() == CellType.NUMERIC) {
            // 数值型
            // poi读取整数会自动转成小数，这里对整数进行还原，小数不会做处理
            long longValue = Math.round(cell.getNumericCellValue());
            if (Double.parseDouble(longValue + ".0") == cell.getNumericCellValue()) {
                cellValue = longValue;
            } else {
                cellValue = cell.getNumericCellValue();
            }
        } else if (Objects.nonNull(cell) && cell.getCellTypeEnum() == CellType.FORMULA) {
            // 公式型
            // 公式计算的值不会转成小数，这里数值获取失败后会获取字符
            try {
                cellValue = cell.getNumericCellValue();
            } catch (Exception e) {
                cellValue = cell.getStringCellValue();
            }
        } else {
            // 其他类型不作处理
            cellValue = cell;
        }
        return cellValue;
    }

    public static List<List<ExcelEntity>> excelToShopIdList(InputStream inputStream) throws IOException {
        List<ExcelEntity> list = new ArrayList<>();
        List<List<ExcelEntity>> resultList = new ArrayList<>();
        Workbook workbook = null;
        //工作表对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        try {

            //workbook = WorkbookFactory.create(inputStream);
            //inputStream.close();

            for (int i = 1; i < 6; i++) {

                xssfWorkbook.getSheetAt(i);
                // 获取第i页数据
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                // 存放一页的结果
                List<ExcelEntity> sheetList = new ArrayList<>();

                ExcelEntity jiFenExcel = null;
                int num =0;
                //总记录数
                System.out.println(xssfSheet.getPhysicalNumberOfRows());
                for (int j = 1; j <= xssfSheet.getPhysicalNumberOfRows()-1; j++) {

                    // 获取第j行数据
                    XSSFRow xssfRow = xssfSheet.getRow(j);
                    int colLength = xssfRow.getLastCellNum();
                    jiFenExcel = new ExcelEntity();
                    for (int k = 0; k < colLength; k++) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        // 只记录有表头的数据
                        if (xssfRow.getCell(k) != null) {
                            String data = null;

                            CellType typeEnum = xssfRow.getCell(k).getCellTypeEnum();
                            if (CellType.NUMERIC == xssfRow.getCell(k).getCellTypeEnum()) {
                                data = sdf.format(xssfRow.getCell(k).getDateCellValue());
                            } else {
                                data = xssfRow.getCell(k).getStringCellValue().trim();
                            }
                            if (k == 2) {
                                jiFenExcel.setSortName(data);
                            } else if (k == 3) {
                                jiFenExcel.setDescription(data);
                            } else if (k == 4) {
                                jiFenExcel.setQuestioner(data);
                            } else if (k == 5) {
                                jiFenExcel.setSourceArea(data);
                            } else if (k == 6) {
                                System.out.println(num);
                                Date d = xssfRow.getCell(k).getDateCellValue();
                                String format = sdf.format(d);
                                jiFenExcel.setQuestionTime(format);
                            } else if (k == 7) {
                                jiFenExcel.setCauses(data);
                            } else if (k == 8) {
                                jiFenExcel.setAnswer(data);
                            } else if (k == 9) {
                                jiFenExcel.setSolution(data);
                            } else if (k == 10) {
                                jiFenExcel.setSolutionStatus(data);
                            } else if (k == 11) {
                                jiFenExcel.setFollowUpQuestion(data);
                            }
                        }

                    }

                    sheetList.add(jiFenExcel);
                    num++;

                }
                resultList.add(sheetList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            // 关闭资源
//            try {
//                //xssfWorkbook.close();
//                //inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println(resultList);
        return resultList;

    }


//            Sheet sheet = workbook.getSheetAt(index);
//            System.out.println(workbook.getNumberOfSheets());
//            //总行数
//            int rowLength = sheet.getLastRowNum();
//            //            System.out.println("总行数有多少行" + rowLength);
//            //工作表的列
//            Row row = sheet.getRow(0);
//
//            //总列数
//            int colLength = row.getLastCellNum();
//            //            System.out.println("总列数有多少列" + colLength);
//            //得到指定的单元格
//            Cell cell = row.getCell(0);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            for (int i = 1; i <= rowLength; i++) {
//                ExcelEntity jiFenExcel = new ExcelEntity();
//                row = sheet.getRow(i);
//                for (int j = 0; j < colLength; j++) {
//                    //列： 0姓名	1人员编号	2餐补 3部门
//                    cell = row.getCell(j);
//                    //                    System.out.print(cell + ",");
//                    if (cell != null) {
//                        String data = null;
//
//                        CellType typeEnum = cell.getCellTypeEnum();
//                        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
//                            data = sdf.format(cell.getDateCellValue());
//                        } else {
//                            data = cell.getStringCellValue().trim();
//                        }
//
//                        //                        System.out.print(data);
//                        //                        if (StringUtils.isNumeric(data)) {
//                        if (j == 2) {
//                            jiFenExcel.setSortName(data);
//                        }
////                        else if (j == 1) {
//////                            jiFenExcel.setId(Integer.parseInt(data));
//////                        }
//                        else if (j == 3) {
//                            jiFenExcel.setDescription(data);
//                        } else if (j == 4) {
//                            jiFenExcel.setQuestioner(data);
//                        } else if (j == 5) {
//                            jiFenExcel.setSourceArea(data);
//                        } else if (j == 6) {
//                            Date d = cell.getDateCellValue();
//                            String format = sdf.format(d);
//                            jiFenExcel.setQuestionTime(format);
//                        } else if (j == 7) {
//                            jiFenExcel.setCauses(data);
//                        } else if (j == 8) {
//                            jiFenExcel.setAnswer(data);
//                        } else if (j == 9) {
//                            jiFenExcel.setSolution(data);
//                        } else if (j == 10) {
//                            jiFenExcel.setSolutionStatus(data);
//                        } else if (j == 11) {
//                            jiFenExcel.setFollowUpQuestion(data);
//                        }
//                        //                        }
//                    }
//                }
//                list.add(jiFenExcel);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;


}