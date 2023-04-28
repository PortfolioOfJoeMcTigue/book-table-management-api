package com.joes.portfolio.book_info.genre_rest_api.util;

import com.joes.portfolio.book_info.genre_rest_api.model.Genre;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class GenreSearcher {

    public List<Genre> genresToSearch(String columnName, String searchArtifact, List<Genre> genres) {

        List<Genre> genresFound = new ArrayList<>();

        switch (columnName) {
                case "id" -> genresFound = findMatchingGenresById(genres, searchArtifact);
                case "genre_code" -> genresFound = findMatchingGenresByGenreCode(genres, searchArtifact);
                case "genre" -> genresFound = findMatchingGenresByGenre(genres, searchArtifact);
        }
        return genresFound;
    }

    private List<Genre> findMatchingGenresById(List<Genre> genres, String searchArtifact) {

        List<Genre> found = new ArrayList<>();

        for (Genre genre : genres ) {
            if ( genre.getId() == parseLong(searchArtifact) ) {
                found.add(genre);
                break;
            }
        }
        return found;
    }

    private List<Genre> findMatchingGenresByGenreCode(List<Genre> genres, String genreCode) {

        List<Genre> found = new ArrayList<>();
        for ( Genre genre : genres ) {
            if ( genre.getGenre_code().equals(genreCode) ) {
                found.add(genre);
                break;
            }
        }
        return found;
    }

    private List<Genre> findMatchingGenresByGenre(List<Genre> genres, String searchArtifact) {

        List<Genre> found = new ArrayList<>();

        for (Genre genre : genres ) {
            if ( genre.getGenre().contains(searchArtifact) ) {
                found.add(genre);
            }
        }
        return found;
    }

    public Long getIdFromGenreByGenreCode(Genre genre, String genreCode) {
        Long id = 0L;
        if ( genre.getGenre_code().equals(genreCode) ) {
            id = genre.getId();
        }
        return id;
    }

    public Long getIdFromGenreByGenreCode(List<Genre> genres, String genreCode) {
        Long id = 0L;
        for ( Genre genre : genres ) {
            if (genre.getGenre_code().equals(genreCode)) {
                id = genre.getId();
                break;
            }
        }
        return id;
    }
}
