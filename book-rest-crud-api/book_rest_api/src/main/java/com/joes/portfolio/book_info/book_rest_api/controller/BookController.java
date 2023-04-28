package com.joes.portfolio.book_info.book_rest_api.controller;

import com.joes.portfolio.book_info.book_rest_api.model.Book;
import com.joes.portfolio.book_info.book_rest_api.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @GetMapping("/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setContentType("text/plain");
        return "Book Service is up and running.";
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Book>> listBooks() {
        return ResponseEntity.ok(bookService.listAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam("columnName") String columnName,
                                                  @RequestParam("searchArtifact") String searchArtifact) {
        return ResponseEntity.ok(bookService.listAllMatchingBooks(columnName, searchArtifact));
    }

    @PostMapping("/create")
    public ResponseEntity createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/updatebyisbn/{isbn}")
    public ResponseEntity updateBookByIsbn(@RequestBody Book book, @PathVariable String isbn) {
        return ResponseEntity.ok(bookService.updateBookByIsbn(book, isbn));
    }

    @DeleteMapping("/deletebyisbn/{isbn}")
    public ResponseEntity deleteBookByIsbn(HttpServletResponse response, @PathVariable String isbn) {
        response.setContentType("text/plain");
        return ResponseEntity.ok(bookService.deleteBookByIsbn(isbn));
    }
}
