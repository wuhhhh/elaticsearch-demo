/*
 * projectName: zoina-search
 * fileName: ExcelEntity.java
 * packageName: com.zoina.search.entity
 * date: 2020-04-14 9:25
 */
package com.zoina.search.entity;

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
@Accessors(chain = true)
@TableName("zs_question_info")
public class ExcelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Long id;
    @TableField("sortName")
    private String sortName;
    @TableField("description")
    private String description;
    @TableField("questioner")
    private String questioner;
    @TableField("sourceArea")
    private String sourceArea;
    @TableField("questionTime")
    private String questionTime;
    @TableField("causes")
    private String causes;
    @TableField("answer")
    private String answer;
    @TableField("solution")
    private String solution;
    @TableField("solutionStatus")
    private String solutionStatus;
    @TableField("followUpQuestion")
    private String followUpQuestion;
}