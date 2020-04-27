/*
 * projectName: zoina-search
 * fileName: ExcelController.java
 * packageName: com.zoina.search.web
 * date: 2020-04-14 9:22
 */
package com.zoina.search.web;

import com.zoina.search.entity.ExcelEntity;
import com.zoina.search.service.ExcelService;
import com.zoina.search.utils.ExcelUtils;
import com.zoina.search.utils.ResultVOUtil;
import com.zoina.search.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: ExcelController
 * @packageName: com.zoina.search.web
 * @description: 导入excel
 * @data: 2020-04-14 9:22
 **/
@RestController
    @RequestMapping("/api/importExcel")
public class ExcelController {

    @Resource
    private ExcelService excelService;

    @GetMapping("/index")
    public ResultVO<?> index() {
        return ResultVOUtil.success(excelService.queryList()) ;

    }


    @PostMapping("/uploadExcel")
    public ResultVO<?> uploadExcel(MultipartFile file,
                                   Map<String, Object> map) {
        String name = file.getOriginalFilename();
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return ResultVOUtil.error("文件格式错误");
        }
        List<ExcelEntity> list = null;
        try {
            //excel的数据保存到数据库
            Boolean res = excelService.importExcelRecord(file.getInputStream());
            if (!res) {
                return ResultVOUtil.noData();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResultVOUtil.error(e.getMessage());
        }
        return ResultVOUtil.success("保存成功");
    }



}