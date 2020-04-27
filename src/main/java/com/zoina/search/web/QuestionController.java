/*
 * projectName: zoina-search
 * fileName: QuestionController.java
 * packageName: com.zoina.search.web
 * date: 2020-04-20 18:47
 */
package com.zoina.search.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoina.search.service.BaseElaticService;
import com.zoina.search.service.QuestionService;
import com.zoina.search.utils.ElasticUtil;
import com.zoina.search.utils.ResultVOUtil;
import com.zoina.search.vo.QuestionVo;
import com.zoina.search.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @version: V1.0
 * @author: 吴洪阳
 * @className: QuestionController
 * @packageName: com.zoina.search.web
 * @description:
 * @data: 2020-04-20 18:47
 **/
@RestController
@RequestMapping("/api/question")
@Slf4j
public class QuestionController {

    @Resource
    private BaseElaticService baseElaticService;

    @Resource
    private QuestionService questionService;



    @PostMapping("/all")
    public ResultVO<?> getAllInfo(@RequestBody HashMap<String, Object> queryVo,
                                  @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("question");
        JSONObject qvo = JSON.parseObject(queryVo.get("queryVo").toString());
        if (!StringUtils.isNotEmpty(qvo.getString("idxName"))) {
            log.warn("索引为空");
            return ResultVOUtil.noData();
        }
        try {
            Class<?> clazz = ElasticUtil.getClazz(qvo.getString("className"));
            JSONObject query = qvo.getJSONObject("query");

            MatchQueryBuilder queryBuilders = null;

            IPage<Object> ipg = new Page<>(page, pageSize);

                JSONObject match = query.getJSONObject("match");
                Set<String> keys = match.keySet();
                for (String ke : keys) {
                    queryBuilders = QueryBuilders.matchQuery(ke, match.get(ke));
                }
            System.out.println(StringUtils.isBlank(match.get("id").toString()));
                if (null != queryBuilders && StringUtils.isNotBlank(match.get("id").toString())) {
                    SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders).size(pageSize).from(page-1);
                    List<Object> data = (List<Object>) baseElaticService.search(qvo.getString("idxName"), searchSourceBuilder, clazz);
                    ipg.setTotal(data.size())
                            .setRecords(data)
                            .setCurrent(page)
                            .setSize(pageSize)
                            .setPages(data.size() % pageSize == 0 ? data.size() % pageSize : (data.size() % pageSize) + 1);
                }else if(null != queryBuilders && StringUtils.isBlank(match.get("id").toString())){
                    MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
                    SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(matchAllQueryBuilder).size(pageSize).from(page-1);
                    List<Object> list = (List<Object>)baseElaticService.search(qvo.getString("idxName"), searchSourceBuilder, clazz);

                    ipg.setTotal(list.size())
                            .setRecords(list)
                            .setCurrent(page)
                            .setSize(pageSize)
                            .setPages(list.size() % pageSize == 0 ? list.size() % pageSize : (list.size() % pageSize) + 1);
            }
            return ResultVOUtil.success(ipg);
        } catch (Exception e) {
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(), e.getMessage());
            return ResultVOUtil.error("网络异常");
        }
    }


    @GetMapping("/queryPageList")
    public ResultVO<?> getPageInfo(@RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize, @RequestParam(value = "page", defaultValue = "1") Integer page, QuestionVo questionVo) {
        return ResultVOUtil.success(questionService.queryList(pageSize, page, questionVo));
    }

    @Resource
    private RestHighLevelClient highLevelClient;

    @PostMapping("/qqqq")
    public List<Object> fullTextSearch(@RequestBody HashMap<String, Object> queryVo ) {
        //Long organizationId, Long projectId,
        JSONObject qvo = JSON.parseObject(queryVo.get("queryVo").toString());
        JSONObject query = qvo.getJSONObject("query");
        JSONObject match = query.getJSONObject("match");
        List<Object> results = new ArrayList<>();
        String searchStr = match.get("id").toString();
        SearchRequest searchRequest = new SearchRequest("question");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
//        if (organizationId != null) {
//            boolBuilder.filter(new TermQueryBuilder(BaseStage.ES_PAGE_FIELD_ORGANIZATION_ID, String.valueOf(organizationId)));
//        }
//        if (projectId != null) {
//            boolBuilder.filter(new TermQueryBuilder(BaseStage.ES_PAGE_FIELD_PROJECT_ID, String.valueOf(projectId)));
//        } else {
//            boolBuilder.mustNot(QueryBuilders.existsQuery(BaseStage.ES_PAGE_FIELD_PROJECT_ID));
//        }
        boolBuilder.must(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("description", searchStr)));
        //.should(QueryBuilders.matchPhraseQuery(BaseStage.ES_PAGE_FIELD_CONTENT, searchStr)));
        //.should(QueryBuilders.matchPhrasePrefixQuery(BaseStage.ES_PAGE_FIELD_CONTENT, searchStr)));
        sourceBuilder.query(boolBuilder);
        // 设置分页查询
        sourceBuilder.from(0);
        sourceBuilder.size(30);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(true).field("description")
                .preTags("<span style=\"color:#F44336\" >").postTags("</span>")
                .fragmentSize(50)
                .noMatchSize(50);
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        SearchResponse response;
        try {
            response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Arrays.stream(response.getHits().getHits())
                    .forEach(hit -> {
                        Map<String, Object> map = hit.getSourceAsMap();
//                        Object proIdObj = map.get(BaseStage.ES_PAGE_FIELD_PROJECT_ID);
//                        Object orgIdObj = map.get(BaseStage.ES_PAGE_FIELD_ORGANIZATION_ID);
                        Object titleObj = map.get("description");
                        Long pageId = Long.parseLong(hit.getId());
//                        Long esProjectId = proIdObj != null ? Long.parseLong(String.valueOf(proIdObj)) : null;
//                        Long esOrganizationId = orgIdObj != null ? Long.parseLong(String.valueOf(orgIdObj)) : null;
//                        String title = titleObj != null ? String.valueOf(titleObj) : "";
//                        FullTextSearchResultDTO resultDTO = new FullTextSearchResultDTO(pageId, title, null, esProjectId, esOrganizationId);
//                        //设置评分
//                        resultDTO.setScore();
                        System.out.println(hit.getScore());
                        //取高亮结果
                        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                        HighlightField highlight = highlightFields.get("description");
                        if (highlight != null) {
                            Text[] fragments = highlight.fragments();
                            if (fragments != null) {
                                String fragmentString = fragments[0].string();
                                System.out.println(fragmentString);
                            } else {
                                System.out.println("meiyou");
                            }
                        } else {
                            System.out.println("jiade");
                        }
                        results.add(null);
                    });
            log.info("全文搜索结果:命中{},搜索内容:{}", response.getHits().getTotalHits(), searchStr);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return results;
    }
}