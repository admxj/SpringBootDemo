package com.admxj.spring.boot.controller;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private TransportClient client;

    @GetMapping("/get/book/novel/{id}")
    public ResponseEntity json(@PathVariable String id) {

        if (id.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        GetResponse result = client.prepareGet("book", "novel", id).get();

        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }

    @PostMapping("/add/book/novel")
    public ResponseEntity add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "word_count") Integer wordCount,
            @RequestParam(name = "publish_date")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate
    ) {
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime()).endObject();
            IndexResponse indexResponse = this.client.prepareIndex("book", "novle")
                    .setSource(xContentBuilder).get();
            return new ResponseEntity(indexResponse.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
