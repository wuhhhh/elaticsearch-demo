///*
// * projectName: zoina-search
// * fileName: QuestionItemEntity.java
// * packageName: com.zoina.search.entity
// * date: 2020-04-23 9:42
// */
//package com.zoina.search.entity;
//
//import lombok.extern.slf4j.Slf4j;
//
//
///**
// * @version: V1.0
// * @author: 吴洪阳
// * @className: QuestionItemEntity
// * @packageName: com.zoina.search.entity
// * @description:
// * @data: 2020-04-23 9:42
// **/
//@Document(indexName = "question",type = "docs", shards = 1, replicas = 0)
//public class QuestionItemEntity {
//    @Id
//    private Long id;
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String sortName;
//    @Field(type = FieldType.Keyword)
//    private String description;
//    @Field(type = FieldType.Keyword)
//    private String questioner;
//    @Field(type = FieldType.Keyword)
//    private String sourceArea;
//    @Field(type = FieldType.Keyword)
//    private String questionTime;
//    @Field(type = FieldType.Keyword)
//    private String causes;
//    @Field(type = FieldType.Keyword)
//    private String answer;
//    @Field(type = FieldType.Keyword)
//    private String solution;
//    @Field(type = FieldType.Keyword)
//    private String solutionStatus;
//    @Field(type = FieldType.Keyword)
//    private String followUpQuestion;
//}