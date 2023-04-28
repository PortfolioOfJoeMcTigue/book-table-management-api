package com.joes.portfolio.book_info.genre_rest_api.controller;

import com.joes.portfolio.book_info.genre_rest_api.model.Genre;
import com.joes.portfolio.book_info.genre_rest_api.service.GenreService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @ResponseBody
    @GetMapping("/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setContentType("text/plain");
        return "Genre Service is up and running.";
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Genre>> listGenres() {
        return genreService.listAllGenres();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Genre>> searchGenres(@RequestParam("columnName") String columnName,
                                                    @RequestParam("searchArtifact") String searchArtifact) {
        return genreService.listAllMatchingGenres(columnName, searchArtifact);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/updatebygenrecode/{genre_code}")
    public ResponseEntity<Genre> updateGenreByGenreCode(@RequestBody Genre genre,
                                                        @PathVariable String genre_code) {
        return genreService.updateGenreByGenreCode(genre, genre_code);
    }

    @DeleteMapping("/deletebygenrecode/{genre_code}")
    public ResponseEntity<String> deleteGenreByGenreCode(HttpServletResponse response,
                                                         @PathVariable String genre_code) {
        response.setContentType("text/plain");
        return genreService.deleteGenreByGenreCode(genre_code);
    }
}
