package com.joes.portfolio.book_info.author_rest_api.controller;

import com.joes.portfolio.book_info.author_rest_api.model.Author;
import com.joes.portfolio.book_info.author_rest_api.service.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // working :)
    @ResponseBody
    @GetMapping("/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setContentType("text/plain");
        return "Author Service is up and running.";
    }
    // working :)
    @GetMapping("/listall")
    public ResponseEntity<List<Author>> listAuthors() {
        return ResponseEntity.ok(authorService.listAllAuthors());
    }
    // working :)
    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam("columnName") String columnName,
                                                      @RequestParam("searchArtifact") String searchArtifact) {
        return ResponseEntity.ok(authorService.listAllMatchingAuthors(columnName, searchArtifact));
    }
    // working :)
    @PostMapping("/create")
    public ResponseEntity createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @PutMapping("/updatebyauthorcode/{author_code}")
    public ResponseEntity updateAuthorByAuthorCode(@RequestBody Author author, @PathVariable String author_code) {
        return ResponseEntity.ok(authorService.updateAuthorByAuthorCode(author, author_code));
    }

    // working :)
    @DeleteMapping("/deletebyauthorcode/{author_code}")
    public ResponseEntity deleteAuthorByAuthorCode(HttpServletResponse response, @PathVariable String author_code) {
        response.setContentType("text/plain");
        return ResponseEntity.ok(authorService.deleteAuthorByAuthorCode(author_code));
    }
}
