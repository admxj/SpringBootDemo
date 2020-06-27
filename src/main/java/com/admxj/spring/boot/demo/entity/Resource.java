package com.admxj.spring.boot.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class Resource {

    private String id;

    private String name = "admxj";

    private String website = "www.admxj.com";

    private String language = "zh_CN";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
