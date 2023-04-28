package com.joes.portfolio.book_info.book_rest_api.util;


import com.joes.portfolio.book_info.book_rest_api.model.Book;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class BookSearcher {

    public List<Book> booksToSearch(String columnName, String searchArtifact, List<Book> books) {
        List<Book> booksFound = new ArrayList<>();
        switch (columnName) {
            case "id" -> booksFound = findMatchingBooksById(books, searchArtifact);
            case "isbn" -> booksFound = findMatchingBookByIsbn(books, searchArtifact);
            case "title" -> booksFound = findMatchingBooksByTitle(books, searchArtifact);
            case "summary" -> booksFound = findMatchingBooksBySummary(books, searchArtifact);
            case "publish_date" -> booksFound = findMatchingBooksByPublishDate(books, searchArtifact);
            case "page_count" -> booksFound = findMatchingBooksByPageCount(books, searchArtifact);
            case "author_code" -> booksFound = findMatchingBooksByAuthorCode(books, searchArtifact);
            case "rating_code" -> booksFound = findMatchingBooksByRatingCode(books, searchArtifact);
            case "age_code" -> booksFound = findMatchingBooksByAgeCode(books, searchArtifact);
            case "publisher_code" -> booksFound = findMatchingBooksByPublisherCode(books, searchArtifact);
            case "genre_code" -> booksFound = findMatchingBooksByGenreCode(books, searchArtifact);

        }
        return booksFound;
    }

    public List<Book> findMatchingBooksById(List<Book> books, String searchArtifact) {

        List<Book> found = new ArrayList<>();

        for (Book book : books ) {
            if ( book.getId().longValue() == parseLong(searchArtifact) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByTitle(List<Book> books, String searchArtifact) {

        List<Book> found = new ArrayList<>();

        for (Book book : books ) {
            if ( book.getTitle().contains(searchArtifact) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksBySummary(List<Book> books, String searchArtifact) {

        List<Book> found = new ArrayList<>();

        for (Book book : books ) {
            if ( book.getSummary().contains(searchArtifact) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByPublishDate(List<Book> books, String searchArtifact) {

        List<Book> found = new ArrayList<>();

        for (Book book : books ) {
            if ( book.getPublish_date().contains(searchArtifact)) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByPageCount(List<Book> books, String searchArtifact) {

        List<Book> found = new ArrayList<>();

        for (Book book : books ) {
            String numberString = Integer.toString(book.getPage_count());
            if ( numberString.contains(searchArtifact) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBookByIsbn(List<Book> books, String isbn) {

        List<Book> found = new ArrayList<>();

        for ( Book book : books ) {
            if ( book.getIsbn().contains(isbn) ) {
                found.add(book);
                break;
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByAuthorCode(List<Book> books, String author_code) {

        List<Book> found = new ArrayList<>();
        for ( Book book : books ) {
            if ( book.getAuthor_code().contains(author_code) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByAgeCode(List<Book> books, String age_code) {

        List<Book> found = new ArrayList<>();
        for ( Book book : books ) {
            if ( book.getAge_code().contains(age_code) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByGenreCode(List<Book> books, String genre_code) {

        List<Book> found = new ArrayList<>();
        for ( Book book : books ) {
            if ( book.getGenre_code().contains(genre_code) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByPublisherCode(List<Book> books, String publisher_code) {

        List<Book> found = new ArrayList<>();
        for ( Book book : books ) {
            if ( book.getPublisher_code().contains(publisher_code) ) {
                found.add(book);
            }
        }
        return found;
    }

    public List<Book> findMatchingBooksByRatingCode(List<Book> books, String rating_code) {

        List<Book> found = new ArrayList<>();
        for ( Book book : books ) {
            if ( book.getRating_code().contains(rating_code) ) {
                found.add(book);
            }
        }
        return found;
    }

    public Long getIdFromBookByIsbn(Book book, String isbn) {

        Long id = 0L;
        if ( book.getIsbn().contains(isbn) ) {
            id = book.getId();
        }
        return id;
    }
}
