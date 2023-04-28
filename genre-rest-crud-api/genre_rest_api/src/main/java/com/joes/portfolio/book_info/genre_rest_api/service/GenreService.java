package com.joes.portfolio.book_info.genre_rest_api.service;

import com.joes.portfolio.book_info.genre_rest_api.exception.ResourceNotFoundException;
import com.joes.portfolio.book_info.genre_rest_api.model.Genre;
import com.joes.portfolio.book_info.genre_rest_api.repository.GenreRepository;
import com.joes.portfolio.book_info.genre_rest_api.util.GenreSearcher;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService {

    static final Logger logger = Logger.getLogger(GenreService.class);
    @Autowired
    private GenreRepository genreRepository;

    public ResponseEntity<List<Genre>> listAllGenres() {
        logger.info("Gathering list of all Genre records.");
        List<Genre> genres;
        String message;
        try {
            genres = (List<Genre>) genreRepository.findAll();
        } catch (ResourceNotFoundException rnfe) {
            message = "Resources not found in listAllGenres.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genres);
    }
    public ResponseEntity<String> createGenre(Genre genre) {
        String message;
        try {
            genreRepository.save(genre);
            message = "Successfully saved Genre: { "+ genre.getGenre() + " }.";
        } catch(Exception e) {
            message = "Could not save Genre: { "+ genre.getGenre() + " }.";
            logger.error(message);
        }
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<List<Genre>> listAllMatchingGenres(String columnName, String searchArtifact) {
        List<Genre> allGenres = (List<Genre>) genreRepository.findAll();
        String message;
        if ( allGenres.isEmpty() ) {
            message = "No Rating data was returned from searching by column: { "+columnName+" } and searchArtifact: { "+searchArtifact+" } request.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        GenreSearcher genreSearcher = new GenreSearcher();
        return ResponseEntity.ok(genreSearcher.genresToSearch(columnName, searchArtifact, allGenres));
    }
    public ResponseEntity<Genre> updateGenreByGenreCode( Genre genre, String genreCode) {
        GenreSearcher genreSearcher = new GenreSearcher();
        String message;
        try {
            Long id = genreSearcher.getIdFromGenreByGenreCode(genre, genreCode);
            Optional<Genre> genreOptional = genreRepository.findById(id);
            if ( genreOptional.isEmpty() ) {
                message = "No Genre records could be found by genre_code { "+ genreCode +" } requested.";
                logger.info(message);
                return ResponseEntity.notFound().build();
            }
            genre.setId(id);
            genreRepository.save(genre);
        } catch (ResourceNotFoundException rnfe) {
            message = "Suggested Genre record could not be updated by genreCode { "+ genreCode +" } requested.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }
    public ResponseEntity<String> deleteGenreByGenreCode(String genreCode) {
        Long id;
        String message;
        List<Genre> allGenres = (List<Genre>) genreRepository.findAll();
        if ( allGenres.isEmpty() ) {
            message = "Resources not found in listAllGenres.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        GenreSearcher genreSearcher = new GenreSearcher();
        id = genreSearcher.getIdFromGenreByGenreCode(allGenres, genreCode);
        if ( id == 0L || id == null ) {
            message = "Genre id was not returned in search by genre_code";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if ( genreOptional.isEmpty() ) {
            message = "No Genre records could be found by genre_code { "+ genreCode +" } requested.";
            logger.info(message);
            return ResponseEntity.notFound().build();
        }
        try {
            genreRepository.deleteById(id);
            message = "Genre by genre_code { "+genreCode+" } has been deleted successfully.";
        } catch (ResourceNotFoundException rnfe) {
            message = "No Genre by genre_code {"+genreCode+"} exist.";
            logger.error(message);
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.ok(message);
    }
}
