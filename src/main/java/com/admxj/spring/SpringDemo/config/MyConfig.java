package com.admxj.spring.SpringDemo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MyConfig {

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("localhost");
        InetSocketTransportAddress node = new InetSocketTransportAddress(address,9300);

        Settings settings = Settings.builder().put("cluster.name","adm_elastic").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);

        return client;
    }
}
