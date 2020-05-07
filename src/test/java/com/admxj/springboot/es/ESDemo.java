package com.admxj.springboot.es;

import com.admxj.spring.boot.model.WhiteConfigVO;
import com.admxj.spring.boot.model.WhiteImportListener;
import com.alibaba.excel.EasyExcel;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.InputStream;
import java.util.List;

/**
 * @author admxj
 * @version Id: ESDemo, v 0.1 2020/5/7 11:45 下午 admxj Exp $
 */
public class ESDemo {

    private static WhiteImportListener whiteImportListener = new WhiteImportListener();

    private static void loadData() {

        InputStream resourceAsStream = ESDemo.class.getResourceAsStream("/mock/白名单配置导出.xlsx");

        List<WhiteConfigVO> whiteConfigs = EasyExcel.read(resourceAsStream, WhiteConfigVO.class, whiteImportListener).sheet().doReadSync();

        System.out.println(whiteConfigs.size());
    }


    public static void main(String[] args) {

        loadData();

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));


    }

}
