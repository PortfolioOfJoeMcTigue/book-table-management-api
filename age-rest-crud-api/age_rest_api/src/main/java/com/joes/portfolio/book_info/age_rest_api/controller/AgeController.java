package com.joes.portfolio.book_info.age_rest_api.controller;


import com.joes.portfolio.book_info.age_rest_api.model.Age;
import com.joes.portfolio.book_info.age_rest_api.service.AgeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suggested_ages")
public class AgeController {

    @Autowired
    private AgeService ageService;

    @ResponseBody
    @GetMapping("/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setContentType("text/plain");
        return "Age Service is up and running.";
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Age>> listAges() {
        return ageService.listAllSuggestedAges();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Age>> searchAges(@RequestParam("columnName") String columnName,
                                                @RequestParam("searchArtifact") String searchArtifact) {
        return ageService.listAllMatchingSuggestedAges(columnName, searchArtifact);
    }

    @PostMapping("/create")
    public ResponseEntity createAge(@RequestBody Age age) {
        return ageService.createSuggestedAge(age);
    }

    @PutMapping("/updatebyagecode/{age_code}")
    public ResponseEntity updateAgeByAgeCode(@RequestBody Age age, @PathVariable String age_code) {
        return ageService.updateSuggestedAgeByAgeCode(age, age_code);
    }

    @DeleteMapping("/deletebyagecode/{age_code}")
    public ResponseEntity deleteAgeById(HttpServletResponse response, @PathVariable String age_code) {
        response.setContentType("text/plain");
        return ageService.deleteSuggestedAgeByAgeCode(age_code);
    }
}
