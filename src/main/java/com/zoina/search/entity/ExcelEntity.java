/*
 * projectName: zoina-search
 * fileName: ExcelEntity.java
 * packageName: com.zoina.search.entity
 * date: 2020-04-14 9:25
 */
package com.zoina.search.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: excelEntity
 * @packageName: com.zoina.search.entity
 * @description: excel字段
 * @data: 2020-04-14 9:25
 **/
@Data
@EqualsAndHashCode
//@Accessors(chain = true)
@TableName("zs_question_info")
//@TableName("zs_question_info")
public class ExcelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    @ExcelIgnore
    private Long id;
    @TableField("messOrigin")
    @ExcelProperty(index = 0)
    private String messOrigin;
    @TableField("sortName")
    @ExcelProperty(index = 3)
    private String sortName;
    @ExcelProperty(index = 2)
    @TableField("specificDescription")
    private String specificDescription;
    @TableField("description")
    @ExcelProperty(index = 4)
    private String description;
    @TableField("questioner")
    @ExcelProperty(index = 5)
    private String questioner;
    @TableField("sourceArea")
    @ExcelProperty(index = 6)
    private String sourceArea;
    @ExcelProperty(index = 7)
    @TableField("questionTime")
    private String questionTime;
    @TableField("causes")
    @ExcelProperty(index = 8)
    private String causes;
    @ExcelProperty(index = 9)
    @TableField("answer")
    private String answer;
    @ExcelProperty(index = 10)
    @TableField("solution")
    private String solution;
    @ExcelProperty(index = 11)
    @TableField("solutionStatus")
    private String solutionStatus;
    @ExcelProperty(index = 12)
    @TableField("followUpQuestion")
    private String followUpQuestion;
}