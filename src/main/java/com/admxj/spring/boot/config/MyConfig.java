package com.admxj.spring.boot.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

//    @Bean
//    public TransportClient transportClient() throws UnknownHostException {
//        InetAddress address = InetAddress.getByName("localhost");
//        InetSocketTransportAddress node = new InetSocketTransportAddress(address,9300);
//
//        Settings settings = Settings.builder().put("cluster.name","adm_elastic").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(node);
//
//        return client;
//    }
}
