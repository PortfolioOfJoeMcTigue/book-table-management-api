package com.joes.portfolio.book_info.publisher_rest_api.controller;

import com.joes.portfolio.book_info.publisher_rest_api.model.Publisher;
import com.joes.portfolio.book_info.publisher_rest_api.service.PublisherService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @ResponseBody
    @GetMapping("/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setContentType("text/plain");
        return "Publisher Service is up and running.";
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Publisher>> listPublishers() {
        return publisherService.listAllPublishers();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Publisher>> searchPublishers(@RequestParam("columnName") String columnName,
                                                   @RequestParam("searchArtifact") String searchArtifact) {
        return publisherService.listAllMatchingPublishers(columnName, searchArtifact);
    }

    @PostMapping("/create")
    public ResponseEntity createPublisher(@RequestBody Publisher publisher) {
        return publisherService.createPublisher(publisher);
    }

    @PutMapping("/updatebypublishercode/{publisher_code}")
    public ResponseEntity updatePublisherByPublisherCode(@RequestBody Publisher publisher, @PathVariable String publisher_code) {
        return publisherService.updatePublisherByPublisherCode(publisher, publisher_code);
    }

    @DeleteMapping("/deletebypublishercode/{publisher_code}")
    public ResponseEntity deletePublisherById(HttpServletResponse response, @PathVariable String publisher_code) {
        response.setContentType("text/plain");
        return publisherService.deletePublisherByPublisherCode(publisher_code);
    }
}
