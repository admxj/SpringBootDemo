package com.admxj.spring.boot.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<Object>> map = new HashMap<String, DeferredResult<Object>>();

    public Map<String, DeferredResult<Object>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<Object>> map) {
        this.map = map;
    }
}
