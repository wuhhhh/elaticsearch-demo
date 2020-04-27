/*
 * projectName: zoina-search
 * fileName: questionVo.java
 * packageName: com.zoina.search.vo
 * date: 2020-04-21 14:42
 */
package com.zoina.search.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: questionVo
 * @packageName: com.zoina.search.vo
 * @description: 问题VO实体
 * @data: 2020-04-21 14:42
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class QuestionVo {
    private Long id;
    private String sortName;
    private String description;
    private String questioner;
    private String sourceArea;
    private String questionTime;
    private String causes;
    private String answer;
    private String solution;
    private String solutionStatus;
    private String followUpQuestion;
}