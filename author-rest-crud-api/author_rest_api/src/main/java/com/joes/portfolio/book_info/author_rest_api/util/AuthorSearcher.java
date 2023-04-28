package com.joes.portfolio.book_info.author_rest_api.util;

import com.joes.portfolio.book_info.author_rest_api.model.Author;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class AuthorSearcher {

    public List<Author> authorsToSearch(String columnName, String searchArtifact, List<Author> authors) {
        List<Author> authorsFound = new ArrayList<>();
        switch (columnName) {
                case "id" -> authorsFound = findMatchingAuthorsById(authors, searchArtifact);
                case "author_code"-> authorsFound = findMatchingAuthorsByAuthorCode(authors, searchArtifact);
                case "first_name" -> authorsFound = findMatchingAuthorsByFirstName(authors, searchArtifact);
                case "middle_name" -> authorsFound = findMatchingAuthorsByMiddleName(authors, searchArtifact);
                case "last_name" -> authorsFound = findMatchingAuthorsByLastName(authors, searchArtifact);
                case "birth_date" -> authorsFound = findMatchingAuthorsByBirthDate(authors, searchArtifact);
                case "birth_place" -> authorsFound = findMatchingAuthorsByBirthPlace(authors, searchArtifact);
        }
        return authorsFound;
    }

    public List<Author> findMatchingAuthorsById(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getId().longValue() == parseLong(searchArtifact) ) {
                found.add(author);
                break;
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByAuthorCode(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getAuthor_code().contains(searchArtifact) ) {
                found.add(author);
                break;
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByFirstName(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getFirst_name().contains(searchArtifact) ) {
                found.add(author);
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByMiddleName(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getMiddle_name().contains(searchArtifact) ) {
                found.add(author);
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByLastName(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getLast_name().contains(searchArtifact) ) {
                found.add(author);
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByBirthDate(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getBirth_date().contains(searchArtifact)) {
                found.add(author);
            }
        }
        return found;
    }

    public List<Author> findMatchingAuthorsByBirthPlace(List<Author> authors, String searchArtifact) {

        List<Author> found = new ArrayList<>();

        for (Author author : authors ) {
            if ( author.getBirth_place().contains(searchArtifact) ) {
                found.add(author);
            }
        }
        return found;
    }

    public Author findMatchingAuthorByAuthorCode(List<Author> authors, String searchArtifact) {

        Author found = new Author();

        for (Author author : authors ) {
            if ( author.getAuthor_code().contains(searchArtifact) ) {
                found = author;
                break;
            }
        }
        return found;
    }
}
