package com.shangan.trade.goods;

import com.alibaba.fastjson.JSON;
import com.shangan.trade.goods.db.model.Goods;
import com.shangan.trade.goods.model.Person;
import com.shangan.trade.goods.service.SearchService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private SearchService searchService;

    /**
     * 添加文档
     * @throws Exception
     */
    @Test
    public void addDoc() throws Exception {
        Person person = new Person();
        person.setId("127");
        person.setName("周接伦");
        person.setAddress("台湾台北");
        person.setAge(38);
        //将对象转为json
        String data = JSON.toJSONString(person);
        IndexRequest request = new IndexRequest("person").source(data, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
        System.out.println(response.getId());
    }

    /**
     * 查询所有
     */
    @Test
    public void  matchAll() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();


        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(2);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }

    /**
     * term词条查询
     */
    @Test
    public void  termAll() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        //TermQueryBuilder query = QueryBuilders.termQuery("address","香港铜锣湾");
        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "张学友");

        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }

    /**
     * term词条查询
     */
    @Test
    public void  queryString() throws IOException {
        //构建查询请求，指定查询的索引库
        SearchRequest searchRequest = new SearchRequest("person");

        //创建查询条件构造器 SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
         * 构建查询条件
         * 查询所有文档
         */
        QueryStringQueryBuilder query = QueryBuilders.queryStringQuery("香港 OR 台湾").field("name").field("address").defaultOperator(Operator.OR);


        //指定查询条件
        searchSourceBuilder.query(query);

        /*
         * 指定分页查询信息
         * 从哪里开始查
         */
        searchSourceBuilder.from(0);
        //每次查询的数量
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        //查询获取查询结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse));

        //获取命中对象
        SearchHits searchHits = searchResponse.getHits();
        long totalNum = searchHits.getTotalHits().value;
        System.out.println("总记录数："+totalNum);

        List<Person> personList = new ArrayList<>();
        //获取命中的hits数据,搜索结果数据
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit searchHit : hits){
            //获取json字符串格式的数据
            String sourceAsString = searchHit.getSourceAsString();
            Person person = JSON.parseObject(sourceAsString, Person.class);
            personList.add(person);
        }

        System.out.println(JSON.toJSONString(personList));
    }


    @Test
    public void ESInitTest(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("http://127.0.0.1/", 9200, "http")
        ));
        System.out.println(JSON.toJSONString(client));
    }

    /**
     * 向ES中添加商品数据
     */
    @Test
    public void addGoodsToES() {
        Goods goods = new Goods();
        goods.setTitle("三星 glaxy note2");
        goods.setBrand("三星");
        goods.setCategory("手机");
        goods.setNumber("NO123458");
        goods.setImage("test");
        goods.setDescription("三星 SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计 8GB+128GB 曜夜黑 5G手机");
        goods.setKeywords("三星 SAMSUNG Galaxy");
        goods.setSaleNum(78);
        goods.setAvailableStock(10000);
        goods.setPrice(899999);
        goods.setStatus(1);
        goods.setId(25L);

//        Goods goods = new Goods();
//        goods.setTitle("华为mate50 pro");
//        goods.setBrand("华为");
//        goods.setCategory("手机");
//        goods.setNumber("NO12360");
//        goods.setImage("test");
//        goods.setDescription("华为mate50 新品手机 曜金黑 8G+256G 全网通");
//        goods.setKeywords("华为mate50 新品手机 曜金黑");
//        goods.setSaleNum(58);
//        goods.setAvailableStock(10000);
//        goods.setPrice(899999);
//        goods.setStatus(1);
//        goods.setId(25L);

        searchService.addGoodsToES(goods);
    }
    @Test
    public void goodsSearch(){
        List<Goods> goodsList = searchService.searchGoodsList("曜金黑", 0, 10);
        System.out.println(JSON.toJSONString(goodsList));
    }
}
