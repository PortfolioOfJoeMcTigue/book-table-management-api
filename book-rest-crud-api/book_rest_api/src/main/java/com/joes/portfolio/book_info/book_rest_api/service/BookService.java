package com.joes.portfolio.book_info.book_rest_api.service;


import com.joes.portfolio.book_info.book_rest_api.exception.ResourceNotFoundException;
import com.joes.portfolio.book_info.book_rest_api.model.Book;
import com.joes.portfolio.book_info.book_rest_api.repository.BookRepository;
import com.joes.portfolio.book_info.book_rest_api.util.BookSearcher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> listAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books;
    }
    public String createBook(Book book) {
        try {
            bookRepository.save(book);
            return "New Book was saved.";
        } catch(Exception e) {
            return "Book could not be saved.";
        }
    }
    public List<Book> listAllMatchingBooks(String columnName, String searchArtifact) {
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        BookSearcher bookSearcher = new BookSearcher();
        return bookSearcher.booksToSearch(columnName, searchArtifact, allBooks);
    }

    public ResponseEntity<Book> updateBookByIsbn(Book book, String isbn) {

        BookSearcher bookSearcher = new BookSearcher();
        try {
            Long id = bookSearcher.getIdFromBookByIsbn(book, isbn);
            Optional<Book> bookOptional = bookRepository.findById(id);
            if ( bookOptional.isEmpty() ) {
                return ResponseEntity.notFound().build();
            }
            book.setId(id);
            bookRepository.save(book);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    public String deleteBookByIsbn(String isbn) {
        Long id = null;
        String messageStatus = "";
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        BookSearcher bookSearcher = new BookSearcher();
        List<Book> bookMatchingIsbn = bookSearcher.booksToSearch("isbn", isbn, allBooks);
        if ( bookMatchingIsbn.isEmpty() ) {
            return "No books match ISBN {"+ isbn +"} requested.";
        }
        System.out.println(bookMatchingIsbn.size());
        for ( Book book : bookMatchingIsbn ) {
            id = book.getId();
        }
        try {
            bookRepository.deleteById(id);
            messageStatus = "Author by isbn{"+isbn+"} has been deleted successfully.";
        } catch (ResourceNotFoundException rnfe) {
            messageStatus = "No Author by isbn {"+isbn+"} exist.";
        }
        return messageStatus;
    }
}