package com.joes.portfolio.book_info.author_rest_api.service;

import com.joes.portfolio.book_info.author_rest_api.exception.ResourceNotFoundException;
import com.joes.portfolio.book_info.author_rest_api.model.Author;
import com.joes.portfolio.book_info.author_rest_api.repository.AuthorRepository;
import com.joes.portfolio.book_info.author_rest_api.util.AuthorSearcher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> listAllAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return authors;
    }
    public String createAuthor(Author author) {
        try {
            authorRepository.save(author);
            return "New Author was saved.";
        } catch(Exception e) {
            return "Author could not be saved.";
        }
    }
    public List<Author> listAllMatchingAuthors(String columnName, String searchArtifact) {
        List<Author> allAuthors = (List<Author>) authorRepository.findAll();
        AuthorSearcher authorSearcher = new AuthorSearcher();
        return authorSearcher.authorsToSearch(columnName, searchArtifact, allAuthors);
    }
    public ResponseEntity<Author> updateAuthorByAuthorCode(Author author, String author_code) {

        List<Author> allAuthors = (List<Author>) authorRepository.findAll();
        AuthorSearcher authorSearcher = new AuthorSearcher();
        try {
            Author authorFound = authorSearcher.findMatchingAuthorByAuthorCode(allAuthors, author_code);
            Long id = authorFound.getId();
            Optional<Author> authorOptional = authorRepository.findById(id);
            if ( authorOptional.isEmpty() ) {
                return ResponseEntity.notFound().build();
            }
            author.setId(id);
            authorRepository.save(author);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    public String deleteAuthorByAuthorCode(String author_code) {

        List<Author> allAuthors = (List<Author>) authorRepository.findAll();
        AuthorSearcher authorSearcher = new AuthorSearcher();
        try {
            Author authorFound = authorSearcher.findMatchingAuthorByAuthorCode(allAuthors, author_code);
            Long id = authorFound.getId();
            authorRepository.deleteById(id);

        } catch (ResourceNotFoundException rnfe) {
            return "Could not delete, No Author found by author_code {"+author_code+"}.";
        }
        return "Author by author_code {"+author_code+"} has been deleted successfully.";
    }
}
